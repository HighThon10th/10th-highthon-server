package com.highthon.global.s3.controller;

import com.highthon.global.s3.dto.response.UploadImageResDto;
import com.highthon.global.s3.service.UploadImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "ThirdParty API", description = "이미지 저장 API입니다.")
@RestController
@RequiredArgsConstructor
public class S3Controller {

    private final UploadImageService uploadImageService;

    @Operation(summary = "이미지 저장")
    @PostMapping("/image")
    public UploadImageResDto uploadImage(
            @RequestParam(value = "file") MultipartFile multipartFile
    ) {
        String fileUrl = uploadImageService.execute(multipartFile);
        return new UploadImageResDto(fileUrl);
    }
}
