package com.highthon.domain.funding.persistence;

import com.highthon.domain.funding.application.dto.FundingResDto;
import com.highthon.domain.funding.application.dto.QueryFundingResDto;
import com.highthon.domain.funding.persistence.type.SearchType;
import com.highthon.domain.like.persistence.QLike;
import com.highthon.domain.product.persistence.QProduct;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.highthon.domain.funding.persistence.QFunding.*;
import static com.highthon.domain.product.persistence.QProduct.*;

@Repository
@RequiredArgsConstructor
public class FundingCustomRepositoryImpl implements FundingCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<FundingResDto> search(String keyword, Long categoryId, SearchType searchType, Pageable pageable) {

        BooleanBuilder builder = new BooleanBuilder();
        applyKeyword(builder, keyword);
        applyCategoryId(builder, categoryId);

        List<FundingResDto> fundings = queryFactory
                .select(Projections.constructor(
                        FundingResDto.class,
                        funding.id,
                        funding.title,
                        funding.fundingAmount.castToNum(Double.class)
                        .divide(funding.targetAmount.castToNum(Double.class))
                        .multiply(100)
                        .stringValue(),
                        funding.thumbnailImgUrl
                ))
                .from(funding)
                .where(builder)
                .orderBy(applySearchType(searchType))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return PageableExecutionUtils.getPage(fundings, pageable, () -> getTotalCount(builder));
    }

    @Override
    public QueryFundingResDto query(Long fundingId, Long userId) {
        Long likeCount = queryFactory.select(QLike.like.count())
                .from(QLike.like)
                .where(QLike.like.user.id.eq(userId)
                        .and(QLike.like.funding.id.eq(fundingId)))
                .fetchOne();

        Boolean isLiked = (likeCount != null && likeCount > 0);

        QueryFundingResDto queryFundingResDto = queryFactory.select(
                        Projections.constructor(
                                QueryFundingResDto.class,
                                funding.id,
                                funding.category.name,
                                funding.thumbnailImgUrl,
                                funding.content,
                                funding.title,
                                funding.description,
                                funding.product.supports.size(),
                                funding.targetAmount,
                                funding.fundingAmount,
                                funding.product.price,
                                funding.product.quantity,
                                funding.fundingStartDate,
                                funding.fundingEndDate
                        )
                )
                .from(funding)
                .where(funding.id.eq(fundingId))
                .join(funding.product, product)
                .fetchOne();

        queryFundingResDto.setIsLiked(isLiked);
        return queryFundingResDto;
    }

    private long getTotalCount(BooleanBuilder builder) {
        return queryFactory
                .select(funding.count())
                .from(funding)
                .where(builder)
                .fetchFirst();
    }

    private void applyKeyword(
            BooleanBuilder booleanBuilder,
            String keyword
    ) {
        if (keyword == null) {
            return;
        }

        booleanBuilder.and(
                funding.title.like(keyword + "%")
        );
    }

    private void applyCategoryId(
            BooleanBuilder booleanBuilder,
            Long categoryId
    ) {
        if (categoryId == null) {
            return;
        }

        booleanBuilder.and(
                funding.category.id.eq(categoryId)
        );
    }

    private OrderSpecifier<?> applySearchType(SearchType searchType) {
        QFunding funding = QFunding.funding;

        if (searchType == null) return funding.fundingStartDate.desc();

        return switch (searchType) {
            case LATEST -> funding.fundingStartDate.desc();
            case OLD -> funding.fundingStartDate.asc();
            case POPULAR -> funding.likeCount.desc();
            case ACHIEVEMENT -> funding.fundingAmount.castToNum(Double.class)
                    .divide(funding.targetAmount.castToNum(Double.class))
                    .multiply(100)
                    .desc();
        };
    }

}
