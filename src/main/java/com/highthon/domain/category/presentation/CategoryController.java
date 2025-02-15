package com.highthon.domain.category.presentation;

import com.highthon.domain.category.application.CategoryService;
import com.highthon.domain.category.application.dto.CreateCategoryReqDto;
import com.highthon.domain.category.application.dto.QueryCategoryResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<QueryCategoryResDto>> queryAl() {
        List<QueryCategoryResDto> response = categoryService.queryCategory();
        return ResponseEntity.ok(response);
    }

}
