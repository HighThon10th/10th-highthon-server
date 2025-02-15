package com.highthon.domain.funding.application;

import com.highthon.domain.category.persistence.Category;
import com.highthon.domain.category.persistence.CategoryRepository;
import com.highthon.domain.community.persistence.Community;
import com.highthon.domain.funding.application.dto.CreateFundingReqDto;
import com.highthon.domain.funding.application.dto.FundingResDto;
import com.highthon.domain.funding.application.dto.SearchFundingInfoDto;
import com.highthon.domain.funding.application.dto.SearchFundingResDto;
import com.highthon.domain.funding.persistence.Funding;
import com.highthon.domain.funding.persistence.FundingRepository;
import com.highthon.domain.funding.persistence.type.FundingStatus;
import com.highthon.domain.funding.persistence.type.SearchType;
import com.highthon.domain.product.persistence.Product;
import com.highthon.domain.product.persistence.ProductRepository;
import com.highthon.global.error.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FundingServiceImpl implements FundingService {

    private final FundingRepository fundingRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public void createFunding(CreateFundingReqDto dto) {
        Long categoryId = dto.getCategoryId();
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new GlobalException("카테고리를 찾을 수 없습니다. id = " + categoryId, HttpStatus.NOT_FOUND));
        category.addFunding();
        categoryRepository.save(category);

        Funding funding = Funding.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .likeCount(0L)
                .fundingAmount(0L)
                .targetAmount(dto.getTargetAmount())
                .fundingStartDate(LocalDateTime.now())
                .fundingEndDate(dto.getFundingEndDate())
                .isRequiredCopyrightPermission(dto.isRequiredCopyrightPermission())
                .isApprovedCopyright(dto.isRequiredCopyrightPermission() ? false : null)
                .fundingStatus(dto.isRequiredCopyrightPermission() ? FundingStatus.PENDING : FundingStatus.STARTING)
                .content(dto.getContent())
                .thumbnailImgUrl(dto.getThumbnailImgUrl())
                .category(category)
                .build();
        fundingRepository.save(funding);

        Product product = Product.builder()
                .price(dto.getProduct().getPrice())
                .quantity(dto.getProduct().getQuantity())
                .funding(funding)
                .build();
        productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public SearchFundingResDto search(String keyword, Long categoryId, SearchType searchType, Integer size, Integer page) {
        Page<FundingResDto> fundingPage = fundingRepository.search(
                keyword, categoryId, searchType, PageRequest.of(page, size)
        );

        SearchFundingInfoDto searchFundingInfoDto = SearchFundingInfoDto.builder()
                .totalPages(fundingPage.getTotalPages())
                .totalElements(fundingPage.getTotalElements())
                .build();

        return SearchFundingResDto.builder()
                .info(searchFundingInfoDto)
                .funding(fundingPage.getContent())
                .build();

    }

}
