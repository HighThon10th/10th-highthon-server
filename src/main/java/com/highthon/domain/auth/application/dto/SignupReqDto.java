package com.highthon.domain.auth.application.dto;

import com.highthon.domain.user.persistence.type.Sex;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class SignupReqDto {
    @NotBlank
    private String loginId;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @NotNull
    private Integer age;
    @NotNull
    private Sex sex;
}
