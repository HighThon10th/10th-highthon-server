package com.highthon.domain.category.application;

import com.highthon.domain.category.application.dto.CreateCategoryReqDto;
import com.highthon.domain.category.persistence.Category;
import com.highthon.domain.category.persistence.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public void createCategory(CreateCategoryReqDto dto) {
        Category category = Category.builder()
                .name(dto.getName())
                .fundingCount(0)
                .build();
        categoryRepository.save(category);
    }

}
