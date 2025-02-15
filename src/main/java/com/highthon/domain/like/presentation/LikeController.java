package com.highthon.domain.like.presentation;

import com.highthon.domain.like.application.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{funding_id}")
    public ResponseEntity<Void> add(
            @PathVariable("funding_id") Long fundingId
    ) {
        likeService.add(fundingId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{funding_id}")
    public ResponseEntity<Void> remove(
            @PathVariable("funding_id") Long fundingId
    ) {
        likeService.remove(fundingId);
        return ResponseEntity.noContent().build();
    }

}
