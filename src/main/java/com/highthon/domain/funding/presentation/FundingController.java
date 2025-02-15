package com.highthon.domain.funding.presentation;

import com.highthon.domain.funding.application.FundingService;
import com.highthon.domain.funding.application.dto.CreateFundingReqDto;
import com.highthon.domain.funding.application.dto.FundingResDto;
import com.highthon.domain.funding.application.dto.SearchFundingResDto;
import com.highthon.domain.funding.persistence.type.SearchType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funding")
@RequiredArgsConstructor
public class FundingController {

    private final FundingService fundingService;

    @PostMapping
    public ResponseEntity<Void> create(
            @RequestBody CreateFundingReqDto dto
    ) {
        fundingService.createFunding(dto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<SearchFundingResDto> search(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @RequestParam(name = "searchType", required = false) SearchType searchType,
            @RequestParam(name = "categoryId", required = false) Long categoryId,
            @RequestParam(name = "keyword", required = false) String keyword
    ) {
        SearchFundingResDto response = fundingService.search(keyword, categoryId, searchType, size, page);
        return ResponseEntity.ok(response);
    }

}
