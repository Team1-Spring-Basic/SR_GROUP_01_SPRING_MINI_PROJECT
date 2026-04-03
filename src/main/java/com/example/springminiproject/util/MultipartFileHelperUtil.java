package com.example.springminiproject.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;


public class MultipartFileHelperUtil {


    public String getUploadFileName(MultipartFile file){
        return file.getOriginalFilename();
    }


    public String getContentType(MultipartFile file){
        return file.getContentType();
    }
}
