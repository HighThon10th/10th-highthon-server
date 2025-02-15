package com.highthon.domain.user.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreatorReqDto {
    @NotBlank
    private String businessRegistrationNumber;
}
