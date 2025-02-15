package com.highthon.domain.funding.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchFundingResDto {
    private SearchFundingInfoDto info;
    private List<FundingResDto> funding;
}
