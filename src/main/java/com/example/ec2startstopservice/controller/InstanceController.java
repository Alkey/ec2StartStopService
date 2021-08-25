package com.example.ec2startstopservice.controller;

import com.example.ec2startstopservice.service.AmazonEC2InstanceService;
import com.example.ec2startstopservice.service.AmazonS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instance")
@RequiredArgsConstructor
public class InstanceController {
    private final AmazonEC2InstanceService service;
    private final AmazonS3Service s3Service;

    @PostMapping
    public ResponseEntity<String> runInstance() {
        return ResponseEntity.ok(service.createInstance());
    }

    @PostMapping("/start/{instanceId}")
    public ResponseEntity<Void> startInstance(@PathVariable String instanceId) {
        service.startInstance(instanceId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/stop/{instanceId}")
    public ResponseEntity<Void> stopInstance(@PathVariable String instanceId) {
        service.stopInstance(instanceId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/upload")
    public ResponseEntity<Void> upload() {
        s3Service.upload();
        return ResponseEntity.ok().build();
    }
}

