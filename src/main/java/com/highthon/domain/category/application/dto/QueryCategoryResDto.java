package com.highthon.domain.category.application.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QueryCategoryResDto {
    private Long categoryId;
    private String categoryImgUrl;
    private String name;
}
