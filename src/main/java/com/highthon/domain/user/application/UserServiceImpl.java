package com.highthon.domain.user.application;

import com.highthon.domain.user.application.dto.CreatorReqDto;
import com.highthon.domain.user.persistence.User;
import com.highthon.domain.user.persistence.UserRepository;
import com.highthon.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserUtil userUtil;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void applyCreator(CreatorReqDto dto) {
        String businessRegistrationNumber = dto.getBusinessRegistrationNumber();
        User currentUser = userUtil.getCurrentUser();

        // 사업자 등록 번호 인증 처리

        currentUser.applyCreator(businessRegistrationNumber);
        userRepository.save(currentUser);
    }

}
