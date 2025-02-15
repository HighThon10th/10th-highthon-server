package com.highthon.domain.funding.presentation;

import com.highthon.domain.funding.application.FundingService;
import com.highthon.domain.funding.application.dto.CreateFundingReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
