package com.highthon.domain.auth.application;

import com.highthon.domain.auth.application.dto.LoginReqDto;
import com.highthon.domain.auth.application.dto.LoginResDto;
import com.highthon.domain.auth.application.dto.SignupReqDto;

public interface AuthService {
    LoginResDto login(LoginReqDto dto);
    void signup(SignupReqDto dto);
}
