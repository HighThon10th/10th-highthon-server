package com.highthon.domain.funding.application;

import com.highthon.domain.funding.application.dto.CreateFundingReqDto;
import com.highthon.domain.funding.application.dto.SearchFundingResDto;
import com.highthon.domain.funding.persistence.type.SearchType;

public interface FundingService {
    void createFunding(CreateFundingReqDto dto);
    SearchFundingResDto search(
            String keyword,
            Long categoryId,
            SearchType searchType,
            Integer size,
            Integer page
    );
}
