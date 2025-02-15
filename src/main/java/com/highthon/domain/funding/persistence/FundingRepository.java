package com.highthon.domain.funding.persistence;

import com.highthon.domain.user.persistence.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FundingRepository extends JpaRepository<Funding, Long>, FundingCustomRepository {
    Optional<Funding> findByUserAndId(User user, Long fundingId);
}
