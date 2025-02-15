package com.highthon.global.util;

import com.highthon.domain.user.persistence.User;
import com.highthon.domain.user.persistence.UserRepository;
import com.highthon.global.error.GlobalException;
import com.highthon.global.security.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtil {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof CustomUserDetails) {
            String userId = ((CustomUserDetails) principal).getUsername();
            return userRepository.findById(Long.valueOf(userId))
                    .orElseThrow(() -> new GlobalException("유저를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));
        } else {
            throw new GlobalException("현재 인증되어 있는 유저의 principal이 유효하지 않습니다.", HttpStatus.UNAUTHORIZED);
        }
    }

}
