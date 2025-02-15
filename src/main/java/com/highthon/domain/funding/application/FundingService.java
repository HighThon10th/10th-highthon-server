package com.highthon.domain.funding.application;

import com.highthon.domain.funding.application.dto.CreateFundingReqDto;

public interface FundingService {
    void createFunding(CreateFundingReqDto dto);
}
