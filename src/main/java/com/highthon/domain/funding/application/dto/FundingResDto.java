package com.highthon.domain.funding.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FundingResDto {
    private Long fundingId;
    private String title;
    private String achievementPercentage;
    private String thumbnailImgUrl;
}
