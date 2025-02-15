package com.highthon.domain.category.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateCategoryReqDto {
    private String name;
    private String url;
}
