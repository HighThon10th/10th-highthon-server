package com.highthon.domain.funding.persistence;

import com.highthon.domain.category.persistence.Category;
import com.highthon.domain.funding.persistence.type.FundingStatus;
import com.highthon.domain.product.persistence.Product;
import com.highthon.domain.support.persistence.Support;
import com.highthon.domain.user.persistence.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_funding")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Builder
@AllArgsConstructor
public class Funding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funding_id", nullable = false)
    private Long id;

    private String title;

    private String description;

    private String thumbnailImgUrl;

    private Long targetAmount;

    private Long fundingAmount = 0L;

    private Long likeCount = 0L;

    private LocalDateTime fundingStartDate = LocalDateTime.now();

    private LocalDateTime fundingEndDate;

    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToOne(mappedBy = "funding")
    private Product product;

    @JoinColumn(name = "category_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Category category;

    @JoinColumn(name = "user_id")
    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @Enumerated(EnumType.STRING)
    private FundingStatus fundingStatus;

    private boolean isRequiredCopyrightPermission;

    private Boolean isApprovedCopyright = null;

    public void addLike() {
        this.likeCount++;
    }

    public void removeLike() {
        if (this.likeCount <= 0) {
            throw new IllegalStateException("좋아요 수는 음수가 될 수 없습니다.");
        }

        this.likeCount--;
    }

    public void addFundingAmount(Long amount) {
        this.fundingAmount += amount;
    }

}
