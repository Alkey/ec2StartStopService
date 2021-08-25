package com.example.ec2startstopservice.service.impl;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.example.ec2startstopservice.service.AmazonEC2InstanceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmazonEC2InstanceServiceImpl implements AmazonEC2InstanceService {
    private final AmazonEC2 amazonEC2;
    private final String imageId;
    private final String keyName;

    public AmazonEC2InstanceServiceImpl(AmazonEC2 amazonEC2,
                                        @Value("#{environment.IMAGE_ID}") String imageId,
                                        @Value("#{environment.KEY_NAME}") String keyName) {
        this.amazonEC2 = amazonEC2;
        this.imageId = imageId;
        this.keyName = keyName;
    }

    @Override
    public String createInstance() {
        RunInstancesRequest request = new RunInstancesRequest()
                .withImageId(imageId)
                .withInstanceType(InstanceType.T3Micro)
                .withKeyName(keyName)
                .withMinCount(1)
                .withMaxCount(1);
        return amazonEC2.runInstances(request)
                .getReservation()
                .getInstances()
                .get(0)
                .getInstanceId();
    }

    @Override
    public void startInstance(String instanceId) {
        amazonEC2.startInstances(new StartInstancesRequest(List.of(instanceId)));
    }

    @Override
    public void stopInstance(String instanceId) {
        amazonEC2.stopInstances(new StopInstancesRequest(List.of(instanceId)));
    }
}
