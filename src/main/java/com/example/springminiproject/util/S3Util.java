package com.example.springminiproject.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.HeadBucketRequest;
import software.amazon.awssdk.services.s3.model.NoSuchBucketException;


@RequiredArgsConstructor
public class S3Util {

    private final S3Client s3Client;

    public void createBucketIfNotExists(String bucketName){
        try{
            s3Client.headBucket(HeadBucketRequest.builder().bucket(bucketName).build());
        }catch (NoSuchBucketException e){
            s3Client.createBucket(CreateBucketRequest.builder().bucket(bucketName).build());
        }
    }





}
