package com.highthon.domain.notice.application;

import com.highthon.domain.funding.persistence.Funding;
import com.highthon.domain.funding.persistence.FundingRepository;
import com.highthon.domain.notice.application.dto.CreateNoticeReqDto;
import com.highthon.domain.notice.application.dto.QueryAllNoticeResDto;
import com.highthon.domain.notice.application.dto.QueryNoticeResDto;
import com.highthon.domain.notice.persistence.Notice;
import com.highthon.domain.notice.persistence.NoticeRepository;
import com.highthon.domain.user.persistence.User;
import com.highthon.global.error.GlobalException;
import com.highthon.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final FundingRepository fundingRepository;
    private final NoticeRepository noticeRepository;
    private final UserUtil userUtil;

    @Override
    public void create(CreateNoticeReqDto dto, Long fundingId) {
        User currentUser = userUtil.getCurrentUser();

        Funding funding = fundingRepository.findByUserAndId(currentUser, fundingId)
                .orElseThrow(() -> new GlobalException("creator 가 아닙니다.", HttpStatus.BAD_REQUEST));

        Notice notice = Notice.builder()
                .user(currentUser)
                .title(dto.getTitle())
                .content(dto.getContent())
                .createTime(LocalDateTime.now())
                .funding(funding)
                .build();
        noticeRepository.save(notice);
    }

    @Override
    public List<QueryAllNoticeResDto> queryAll(Long fundingId) {
        Funding funding = fundingRepository.findById(fundingId)
                .orElseThrow(() -> new GlobalException("not found funding", HttpStatus.NOT_FOUND));
        return noticeRepository.findAllByFundingOrderByCreateTimeDesc(funding)
                .stream().map(n -> QueryAllNoticeResDto.builder()
                        .title(n.getTitle())
                        .createTime(n.getCreateTime())
                        .creatorName(n.getUser().getName())
                        .noticeId(n.getId())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public QueryNoticeResDto query(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new GlobalException("not found notice", HttpStatus.NOT_FOUND));

        return QueryNoticeResDto.builder()
                .title(notice.getTitle())
                .createTime(notice.getCreateTime())
                .creatorName(notice.getFunding().getUser().getName())
                .content(notice.getContent())
                .build();
    }

}
