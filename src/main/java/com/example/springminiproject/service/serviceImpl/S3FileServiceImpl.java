package com.example.springminiproject.service.serviceImpl;

import com.example.springminiproject.model.entity.FileMetaData;
import com.example.springminiproject.service.S3FileService;
import com.example.springminiproject.util.MultipartFileHelperUtil;
import com.example.springminiproject.util.S3Util;
import com.example.springminiproject.util.UuidGenerator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.InputStream;


@Service
@RequiredArgsConstructor
public class S3FileServiceImpl implements S3FileService {

    private final S3Client s3Client;
    private S3Util s3Util;
    private MultipartFileHelperUtil multipartFileHelperUtil;
    private UuidGenerator uuidGenerator;

    @Value("${rustfs.bucket.name}")
    private String bucketName;

    @SneakyThrows
    @Override
    public FileMetaData uploadFile(MultipartFile file) {

        s3Util.createBucketIfNotExists(bucketName);

        String fileName = uuidGenerator.getFileRandomUUID(multipartFileHelperUtil.getUploadFileName(file));
        String contentType = multipartFileHelperUtil.getContentType(file);


        //put object into the bucket
        s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(fileName)
                        .contentType(contentType)
                        .build(),
                RequestBody.fromInputStream(file.getInputStream(), file.getSize())
        );



        String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/files/preview-file/" +  fileName)
                .toUriString();


        return FileMetaData.builder().
                fileName(fileName)
                .fileUrl(fileUrl)
                .fileType(contentType)
                .fileSize(file.getSize())
                .build();
    }

    @Override
    public Resource getFileByFileName(String fileName) {
        InputStream inputStream = s3Client.getObject(
                GetObjectRequest.builder()
                        .bucket(bucketName)
                        .key(fileName)
                        .build());
        return new InputStreamResource(inputStream);
    }
}
