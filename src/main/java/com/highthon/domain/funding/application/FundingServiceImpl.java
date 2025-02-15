package com.highthon.domain.funding.application;

import com.highthon.domain.category.persistence.Category;
import com.highthon.domain.category.persistence.CategoryRepository;
import com.highthon.domain.community.persistence.Community;
import com.highthon.domain.funding.application.dto.CreateFundingReqDto;
import com.highthon.domain.funding.persistence.Funding;
import com.highthon.domain.funding.persistence.FundingRepository;
import com.highthon.domain.funding.persistence.type.FundingStatus;
import com.highthon.domain.product.persistence.Product;
import com.highthon.domain.product.persistence.ProductRepository;
import com.highthon.global.error.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .targetAmount(dto.getTargetAmount())
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
                .name(dto.getProduct().getName())
                .description(dto.getProduct().getDescription())
                .price(dto.getProduct().getPrice())
                .quantity(dto.getProduct().getQuantity())
                .thumbnailImgUrl(dto.getThumbnailImgUrl())
                .funding(funding)
                .build();
        productRepository.save(product);
    }

}
