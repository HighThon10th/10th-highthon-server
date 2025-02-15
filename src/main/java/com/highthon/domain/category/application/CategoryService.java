package com.highthon.domain.category.application;

import com.highthon.domain.category.application.dto.CreateCategoryReqDto;

public interface CategoryService {
    void createCategory(CreateCategoryReqDto dto);
}
