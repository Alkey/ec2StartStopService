package com.example.ec2startstopservice.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {
    private static final Regions REGION = Regions.EU_NORTH_1;
    private static final AWSCredentialsProvider CREDENTIALS = new EnvironmentVariableCredentialsProvider();

    @Bean
    public AmazonS3 getAmazonS3() {
        return AmazonS3ClientBuilder.standard()
                .withCredentials(CREDENTIALS)
                .withRegion(REGION)
                .build();
    }

    @Bean
    public AmazonEC2 getAmazonEC2() {
        return AmazonEC2ClientBuilder.standard()
                .withCredentials(CREDENTIALS)
                .withRegion(REGION)
                .build();
    }
}
