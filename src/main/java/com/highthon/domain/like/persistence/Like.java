package com.highthon.domain.like.persistence;

import com.highthon.domain.funding.persistence.Funding;
import com.highthon.domain.user.persistence.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_like")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Builder
@AllArgsConstructor
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id", nullable = false)
    private Long id;

    @JoinColumn(name = "funding_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Funding funding;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
