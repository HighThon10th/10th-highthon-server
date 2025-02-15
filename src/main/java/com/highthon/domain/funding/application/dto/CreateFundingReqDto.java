package com.highthon.domain.funding.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NotBlank
public class CreateFundingReqDto {
    private String title;
    private String description;
    private String thumbnailImgUrl;
    private Long targetAmount;
    private LocalDateTime fundingEndDate;
    private Long categoryId;
    private String content;
    private FundingProductDto product;
    private boolean isRequiredCopyrightPermission;
}
