package com.highthon.domain.funding.persistence;

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

    private Long fundingAmount;

    private LocalDateTime fundingStartDate = LocalDateTime.now();

    private LocalDateTime fundingEndDate;

    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToOne(mappedBy = "funding")
    private Product product;

    private boolean isRequiredCopyrightPermission;

    private boolean isApprovedCopyright;

}
