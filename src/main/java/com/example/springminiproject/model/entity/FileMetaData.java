package com.example.springminiproject.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileMetaData {
    private String fileName;
    private String fileUrl;
    private String fileType;
    private Long fileSize;
}
