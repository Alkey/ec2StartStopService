package com.example.ec2startstopservice.service;

public interface AmazonEC2InstanceService {
    String createInstance();

    void startInstance(String instanceId);

    void stopInstance(String instanceId);
}
