package com.highthon.domain.category.application;

import com.highthon.domain.category.application.dto.CreateCategoryReqDto;
import com.highthon.domain.category.application.dto.QueryCategoryResDto;
import com.highthon.domain.category.persistence.Category;
import com.highthon.domain.category.persistence.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public void createCategory(CreateCategoryReqDto dto) {
        Category category = Category.builder()
                .name(dto.getName())
                .url(dto.getUrl())
                .fundingCount(0)
                .build();
        categoryRepository.save(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<QueryCategoryResDto> queryCategory() {
        return categoryRepository.findAllByOrderByFundingCountDesc()
                .stream()
                .map(c -> QueryCategoryResDto.builder()
                        .categoryId(c.getId())
                        .name(c.getName())
                        .categoryImgUrl(c.getUrl())
                        .build())
                .collect(Collectors.toList());
    }

}
