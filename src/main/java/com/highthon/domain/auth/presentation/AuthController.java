package com.highthon.domain.auth.presentation;

import com.highthon.domain.auth.application.AuthService;
import com.highthon.domain.auth.application.dto.LoginReqDto;
import com.highthon.domain.auth.application.dto.LoginResDto;
import com.highthon.domain.auth.application.dto.SignupReqDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResDto> login(
            @RequestBody @Valid LoginReqDto dto
    ) {
        LoginResDto response = authService.login(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(
            @RequestBody @Valid SignupReqDto dto
    ) {
        authService.signup(dto);
        return ResponseEntity.noContent().build();
    }

}
