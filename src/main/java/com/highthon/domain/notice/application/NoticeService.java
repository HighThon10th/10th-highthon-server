package com.highthon.domain.notice.application;

import com.highthon.domain.notice.application.dto.CreateNoticeReqDto;
import com.highthon.domain.notice.application.dto.QueryAllNoticeResDto;
import com.highthon.domain.notice.application.dto.QueryNoticeResDto;

import java.util.List;

public interface NoticeService {
    void create(CreateNoticeReqDto dto, Long fundingId);
    List<QueryAllNoticeResDto> queryAll(Long fundingId);
    QueryNoticeResDto query(Long noticeId);
}
