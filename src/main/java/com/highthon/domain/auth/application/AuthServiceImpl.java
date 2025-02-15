package com.highthon.domain.auth.application;

import com.highthon.domain.auth.application.dto.LoginReqDto;
import com.highthon.domain.auth.application.dto.LoginResDto;
import com.highthon.domain.auth.application.dto.SignupReqDto;
import com.highthon.domain.user.persistence.User;
import com.highthon.domain.user.persistence.UserRepository;
import com.highthon.domain.user.persistence.type.Authority;
import com.highthon.global.error.GlobalException;
import com.highthon.global.security.jwt.TokenGenerator;
import com.highthon.global.security.jwt.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final TokenGenerator tokenGenerator;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public LoginResDto login(LoginReqDto dto) {
        User user = userRepository.findByLoginId(dto.getLoginId())
                .orElseThrow(() -> new GlobalException("login id가 존재하지 않습니다. id = " + dto.getLoginId(), HttpStatus.NOT_FOUND));

        String rawPassword = dto.getPassword();
        String encodedPassword = user.getPassword();
        boolean isPasswordOk = passwordEncoder.matches(rawPassword, encodedPassword);
        if (!isPasswordOk) {
            throw new GlobalException("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        TokenDto tokenDto = tokenGenerator.generateToken(user.getId().toString());
        return new LoginResDto(tokenDto.getAccessToken());
    }

    @Override
    @Transactional
    public void signup(SignupReqDto dto) {
        boolean isExists = userRepository.existsByLoginId(dto.getLoginId());
        if (isExists) {
            throw new GlobalException("아이디가 중복입니다.", HttpStatus.CONFLICT);
        }

        User user = User.builder()
                .loginId(dto.getLoginId())
                .password(passwordEncoder.encode(dto.getPassword()))
                .age(dto.getAge())
                .sex(dto.getSex())
                .name(dto.getName())
                .authority(Authority.USER)
                .build();

        userRepository.save(user);
    }

}
