package com.highthon.domain.like.application;

import com.highthon.domain.funding.persistence.Funding;
import com.highthon.domain.funding.persistence.FundingRepository;
import com.highthon.domain.like.persistence.Like;
import com.highthon.domain.like.persistence.LikeRepository;
import com.highthon.domain.user.persistence.User;
import com.highthon.global.error.GlobalException;
import com.highthon.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final FundingRepository fundingRepository;
    private final UserUtil userUtil;

    @Override
    @Transactional
    public void add(Long fundingId) {
        User currentUser = userUtil.getCurrentUser();
        Funding funding = fundingRepository.findById(fundingId)
                .orElseThrow(() -> new GlobalException("펀딩이 존재하지 않습니다. id = " + fundingId, HttpStatus.NOT_FOUND));
        funding.addLike();
        fundingRepository.save(funding);

        boolean isLiked = likeRepository.existsByUserAndFunding(currentUser, funding);
        if (isLiked) {
            throw new GlobalException("이미 좋아요를 눌렀습니다.", HttpStatus.CONFLICT);
        }

        Like like = Like.builder()
                .user(currentUser)
                .funding(funding)
                .build();
        likeRepository.save(like);
    }

    @Override
    @Transactional
    public void remove(Long fundingId) {
        User currentUser = userUtil.getCurrentUser();
        Funding funding = fundingRepository.findById(fundingId)
                .orElseThrow(() -> new GlobalException("펀딩이 존재하지 않습니다. id = " + fundingId, HttpStatus.NOT_FOUND));
        funding.removeLike();
        fundingRepository.save(funding);

        boolean isLiked = likeRepository.existsByUserAndFunding(currentUser, funding);
        if (!isLiked) {
            throw new GlobalException("좋아요를 누르지 않은 펀딩의 좋아요를 취소할 수 없습니다.", HttpStatus.CONFLICT);
        }

        likeRepository.deleteByUserAndFunding(currentUser, funding);
    }

}
