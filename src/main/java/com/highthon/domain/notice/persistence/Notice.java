package com.highthon.domain.notice.persistence;

import com.highthon.domain.funding.persistence.Funding;
import com.highthon.domain.user.persistence.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_notice")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Builder
@AllArgsConstructor
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id", nullable = false)
    private Long id;

    private String title;

    private String content;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "funding_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Funding funding;

    private LocalDateTime createTime;

}
