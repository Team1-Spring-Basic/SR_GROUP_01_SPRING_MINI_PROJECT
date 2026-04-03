package com.example.springminiproject.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.UUID;

public class UuidGenerator {


    // Input filename and get uuid with extension
    public String getFileRandomUUID(String fileName){
        return UUID.randomUUID() + "." + StringUtils.getFilenameExtension(fileName);

    }


}
