package com.highthon.domain.category.presentation;

import com.highthon.domain.category.application.CategoryService;
import com.highthon.domain.category.application.dto.CreateCategoryReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Void> craete(
            @RequestBody CreateCategoryReqDto dto
    ) {
        categoryService.createCategory(dto);
        return ResponseEntity.noContent().build();
    }

}
