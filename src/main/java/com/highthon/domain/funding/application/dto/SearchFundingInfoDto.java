package com.highthon.domain.funding.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchFundingInfoDto {
    private Integer totalPages;
    private Long totalElements;
}
