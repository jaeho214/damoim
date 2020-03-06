package com.yeongjae.damoim.global.infra.controller;

import com.yeongjae.damoim.global.infra.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/damoim/files")
@RequiredArgsConstructor
public class FileUploadController {

    private final S3Uploader s3Uploader;

    @PostMapping
    public ResponseEntity<String> upload(@RequestParam("file")MultipartFile multipartFile){
        String fileName = UUID.randomUUID().toString();
        return ResponseEntity.ok(s3Uploader.uploadFile(multipartFile, fileName));
    }

    @PostMapping("/list")
    public ResponseEntity<List<String>> uploads(@RequestParam("files") MultipartFile[] multipartFiles){
        return ResponseEntity.ok().body(s3Uploader.uploadFiles(Arrays.asList(multipartFiles)));
    }
}
