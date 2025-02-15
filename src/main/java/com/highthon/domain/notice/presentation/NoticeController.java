package com.highthon.domain.notice.presentation;

import com.highthon.domain.notice.application.NoticeService;
import com.highthon.domain.notice.application.dto.CreateNoticeReqDto;
import com.highthon.domain.notice.application.dto.QueryAllNoticeResDto;
import com.highthon.domain.notice.application.dto.QueryNoticeResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping("/{funding_id}")
    public ResponseEntity<Void> create(
            @RequestBody CreateNoticeReqDto dto,
            @PathVariable("funding_id") Long fundingId
    ) {
        noticeService.create(dto, fundingId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{funding_id}")
    public ResponseEntity<List<QueryAllNoticeResDto>> queryAll(
            @PathVariable("funding_id") Long fundingId
    ) {
        List<QueryAllNoticeResDto> response = noticeService.queryAll(fundingId);
        return ResponseEntity.ok(response);
    }

}
