package com.highthon.domain.product.presentation;

import com.highthon.domain.product.application.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/buy/{funding_id}")
    public ResponseEntity<Void> buy(
            @PathVariable("funding_id") Long fundingId
    ) {
        productService.buy(fundingId);
        return ResponseEntity.noContent().build();
    }

}
