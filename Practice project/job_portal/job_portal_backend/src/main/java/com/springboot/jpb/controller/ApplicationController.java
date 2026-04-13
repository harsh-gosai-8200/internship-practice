package com.springboot.jpb.controller;

import com.springboot.jpb.dto.*;
import com.springboot.jpb.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<?> applyJob(@RequestBody ApplyJobRequestDTO applyJobRequestDTO) {
        applicationService.applyJob(applyJobRequestDTO);
        return ResponseEntity.ok("Applied successfully");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserApplicationResponseDTO>> getUserApplications(@PathVariable Long userId) {
        return ResponseEntity.ok(applicationService.getApplicationsByUser(userId));
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<JobApplicationsResponseDTO>> getJobApplications(@PathVariable Long jobId) {
        return ResponseEntity.ok(applicationService.getJobApplications(jobId));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<StatusUpdateResponseDTO> updateApplicationStatus(@PathVariable Long id, @RequestBody StatusUpdateRequestDTO statusUpdateRequestDTO) {
        return ResponseEntity.ok(applicationService.updateStatus(id, statusUpdateRequestDTO));
    }
}
