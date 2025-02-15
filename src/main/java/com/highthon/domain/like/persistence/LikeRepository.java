package com.highthon.domain.like.persistence;

import com.highthon.domain.funding.persistence.Funding;
import com.highthon.domain.user.persistence.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByUserAndFunding(User user, Funding funding);
    void deleteByUserAndFunding(User user, Funding funding);
}
