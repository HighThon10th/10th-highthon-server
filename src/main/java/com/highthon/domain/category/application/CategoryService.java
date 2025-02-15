package com.highthon.domain.category.application;

import com.highthon.domain.category.application.dto.CreateCategoryReqDto;
import com.highthon.domain.category.application.dto.QueryCategoryResDto;

import java.util.List;

public interface CategoryService {
    void createCategory(CreateCategoryReqDto dto);
    List<QueryCategoryResDto> queryCategory();
}
