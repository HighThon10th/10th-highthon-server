package com.highthon.domain.community.application;

import com.highthon.domain.community.application.dto.CreateCommentReqDto;
import com.highthon.domain.community.application.dto.QueryCommentResDto;

import java.util.List;

public interface CommunityService {
    void createComment(CreateCommentReqDto dto, Long fundingId);
    List<QueryCommentResDto> queryComment(Long fundingId);
}
