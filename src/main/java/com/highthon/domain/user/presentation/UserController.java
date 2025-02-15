package com.highthon.domain.user.presentation;

import com.highthon.domain.user.application.UserService;
import com.highthon.domain.user.application.dto.CreatorReqDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping("/apply-creator")
    public ResponseEntity<Void> applyCreator(
            @RequestBody @Valid CreatorReqDto dto
    ) {
        userService.applyCreator(dto);
        return ResponseEntity.noContent().build();
    }

}
