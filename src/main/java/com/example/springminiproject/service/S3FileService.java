package com.example.springminiproject.service;

import com.example.springminiproject.model.entity.FileMetaData;
import org.apache.coyote.BadRequestException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface S3FileService {
    FileMetaData uploadFile(MultipartFile file);
    Resource getFileByFileName(String fileName) throws BadRequestException;
}
