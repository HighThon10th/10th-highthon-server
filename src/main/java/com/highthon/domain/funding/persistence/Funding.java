package com.highthon.domain.funding.persistence;

import com.highthon.domain.category.persistence.Category;
import com.highthon.domain.funding.persistence.type.FundingStatus;
import com.highthon.domain.product.persistence.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @JoinColumn(name = "category_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Category category;

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

}
