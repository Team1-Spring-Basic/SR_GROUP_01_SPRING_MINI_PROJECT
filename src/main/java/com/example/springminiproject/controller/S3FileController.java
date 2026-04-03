package com.example.springminiproject.controller;


import com.example.springminiproject.model.entity.FileMetaData;
import com.example.springminiproject.model.response.ApiResponse;
import com.example.springminiproject.service.S3FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.time.Instant;

@RestController
@RequestMapping("api/v1/files")
@RequiredArgsConstructor
public class S3FileController {

    private final S3FileService s3FileService;

    @PostMapping(value = "upload-file",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<FileMetaData>> uploadFile(@RequestParam MultipartFile file) {


        FileMetaData fileMetaData = s3FileService.uploadFile(file);

        ApiResponse<FileMetaData> fileMetaResponse = ApiResponse.<FileMetaData>builder().isSuccess(true).status(HttpStatus.CREATED).message("File upload Successfully").payload(fileMetaData).timestamp(Instant.now()).build();




        return ResponseEntity.status(HttpStatus.CREATED).body(fileMetaResponse);
    }

    @GetMapping("/preview-file/{file-name}")
    public ResponseEntity<Resource> getFileName(@PathVariable("file-name") String fileName){
        Resource resource = s3FileService.getFileByFileName(fileName);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG).body(resource);
    }






}
