package com.highthon.domain.community.presentation;

import com.highthon.domain.community.application.CommunityService;
import com.highthon.domain.community.application.dto.CreateCommentReqDto;
import com.highthon.domain.community.application.dto.QueryCommentResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/community")
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    @PostMapping("/{funding_id}")
    public ResponseEntity<Void> create(
            @RequestBody CreateCommentReqDto dto,
            @PathVariable("funding_id") Long fundingId
    ) {
        communityService.createComment(dto, fundingId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{funding_id}")
    public ResponseEntity<List<QueryCommentResDto>> query(
            @PathVariable("funding_id") Long fundingId
    ) {
        List<QueryCommentResDto> response = communityService.queryComment(fundingId);
        return ResponseEntity.ok(response);
    }

}
