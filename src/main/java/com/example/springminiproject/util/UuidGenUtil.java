package com.example.springminiproject.util;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Component
public class UuidGenUtil {

    // Input filename and get uuid with extension
    public String getFileRandomUUID(String fileName) {
        return UUID.randomUUID() + "." + StringUtils.getFilenameExtension(fileName);

    }
}
