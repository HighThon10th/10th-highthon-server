package com.highthon.domain.funding.persistence;

import com.highthon.domain.funding.application.dto.FundingResDto;
import com.highthon.domain.funding.persistence.type.SearchType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FundingCustomRepository {
    Page<FundingResDto> search(
            String keyword,
            Long categoryId,
            SearchType searchType,
            Pageable pageable
    );
}
