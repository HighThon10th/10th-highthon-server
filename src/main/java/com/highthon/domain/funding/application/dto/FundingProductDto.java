package com.highthon.domain.funding.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FundingProductDto {
    private String productImgUrl;
    private String name;
    private String description;
    private Long price;
    private Long quantity;
}
