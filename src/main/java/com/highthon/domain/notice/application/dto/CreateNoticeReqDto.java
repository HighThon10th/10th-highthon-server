package com.highthon.domain.notice.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class CreateNoticeReqDto {
    private String title;
    private String content;
}
