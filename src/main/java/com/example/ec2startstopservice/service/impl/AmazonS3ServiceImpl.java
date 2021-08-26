package com.example.ec2startstopservice.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.example.ec2startstopservice.service.AmazonS3Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class AmazonS3ServiceImpl implements AmazonS3Service {
    private static final String FILE_NAME = "devdemo-0.0.1-SNAPSHOT.jar";
    private final AmazonS3 client;
    private final String filePath;
    private final String bucket;

    public AmazonS3ServiceImpl(AmazonS3 client,
                               @Value("${aws.s3.bucket}") String bucket,
                               @Value("#{environment.DEMO_APP_JAR}") String filePath) {
        this.client = client;
        this.bucket = bucket;
        this.filePath = filePath;
    }

    @Override
    public void upload() {
        client.putObject(bucket, FILE_NAME, new File(filePath));
    }
}
