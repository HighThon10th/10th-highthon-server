package com.highthon.domain.community.persistence;

import com.highthon.domain.funding.persistence.Funding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityRepository extends JpaRepository<Community, Long> {
    List<Community> findByFundingOrderByCreateTimeDesc(Funding funding);
}
