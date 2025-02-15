package com.highthon.domain.funding.persistence;

import com.highthon.domain.funding.application.dto.FundingResDto;
import com.highthon.domain.funding.persistence.type.SearchType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

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
                        QFunding.funding.id,
                        QFunding.funding.title,
                        QFunding.funding.fundingAmount.castToNum(Double.class)
                                .divide(QFunding.funding.targetAmount.castToNum(Double.class))
                                .multiply(100)
                                .stringValue(),
                        QFunding.funding.thumbnailImgUrl
                ))
                .from(QFunding.funding)
                .where(builder)
                .orderBy(applySearchType(searchType))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return PageableExecutionUtils.getPage(fundings, pageable, () -> getTotalCount(builder));
    }

    private long getTotalCount(BooleanBuilder builder) {
        return queryFactory
                .select(QFunding.funding.count())
                .from(QFunding.funding)
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
                QFunding.funding.title.like(keyword + "%")
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
                QFunding.funding.category.id.eq(categoryId)
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
