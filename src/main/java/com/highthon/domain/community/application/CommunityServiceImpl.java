package com.highthon.domain.community.application;

import com.highthon.domain.category.application.dto.CreateCategoryReqDto;
import com.highthon.domain.community.application.dto.CommentType;
import com.highthon.domain.community.application.dto.CreateCommentReqDto;
import com.highthon.domain.community.application.dto.QueryCommentResDto;
import com.highthon.domain.community.persistence.Community;
import com.highthon.domain.community.persistence.CommunityRepository;
import com.highthon.domain.funding.persistence.Funding;
import com.highthon.domain.funding.persistence.FundingRepository;
import com.highthon.domain.user.persistence.User;
import com.highthon.global.error.GlobalException;
import com.highthon.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private final UserUtil userUtil;
    private final CommunityRepository communityRepository;
    private final FundingRepository fundingRepository;

    @Override
    @Transactional
    public void createComment(CreateCommentReqDto dto, Long fundingId) {
        Funding funding = fundingRepository.findById(fundingId)
                .orElseThrow(() -> new GlobalException("funding not found", HttpStatus.NOT_FOUND));

        User currentUser = userUtil.getCurrentUser();
        Community community = Community.builder()
                .user(currentUser)
                .content(dto.getContent())
                .createTime(LocalDateTime.now())
                .funding(funding)
                .build();

        communityRepository.save(community);
    }

    @Override
    @Transactional(readOnly = true)
    public List<QueryCommentResDto> queryComment(Long fundingId) {
        Funding funding = fundingRepository.findById(fundingId)
                .orElseThrow(() -> new GlobalException("funding not found", HttpStatus.NOT_FOUND));

        List<Community> communities = communityRepository.findByFundingOrderByCreateTimeDesc(funding);
        return communities.stream().map(
                c -> {
                    User user = c.getUser();
                    CommentType type;

                    if (Objects.equals(user.getId(), funding.getUser().getId())) {
                        type = CommentType.CREATOR;
                    } else if (funding.getProduct().getSupports().stream().anyMatch(s -> Objects.equals(s.getUser().getId(), c.getId()))) {
                        type = CommentType.SUPPORTER;
                    } else {
                        type = CommentType.NORMAL;
                    }


                    return QueryCommentResDto.builder()
                            .authorName(c.getUser().getName())
                            .content(c.getContent())
                            .createTime(c.getCreateTime())
                            .type(type)
                            .build();
                    }
        ).toList();
    }

}
