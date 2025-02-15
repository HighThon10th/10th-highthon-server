package com.highthon.global.security.auth;

import com.highthon.domain.user.persistence.User;
import com.highthon.domain.user.persistence.UserRepository;
import com.highthon.global.error.GlobalException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Getter
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new GlobalException("유저를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));

        return new CustomUserDetails(user);
    }
}
