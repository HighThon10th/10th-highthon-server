package com.highthon.domain.notice.persistence;

import com.highthon.domain.funding.persistence.Funding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findAllByFundingOrderByCreateTimeDesc(Funding funding);
}
