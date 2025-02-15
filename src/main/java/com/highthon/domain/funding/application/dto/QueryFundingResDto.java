package com.highthon.domain.funding.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QueryFundingResDto {
    private Long fundingId;
    private String categoryName;
    private String thumbnailUrl;
    private String content;
    private String title;
    private String description;
    private Integer participateCount;
    private Long targetAmount;
    private Long fundingAmount;
    private Long productPrice;
    private Long productQuantity;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;
    private Boolean isLiked;

    public QueryFundingResDto(Long fundingId, String categoryName, String thumbnailUrl, String content, String title, String description, Integer participateCount, Long targetAmount, Long fundingAmount, Long productPrice, Long productQuantity, LocalDateTime startDate, LocalDateTime endDate) {
        this.fundingId = fundingId;
        this.categoryName = categoryName;
        this.thumbnailUrl = thumbnailUrl;
        this.content = content;
        this.title = title;
        this.description = description;
        this.participateCount = participateCount;
        this.targetAmount = targetAmount;
        this.fundingAmount = fundingAmount;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
