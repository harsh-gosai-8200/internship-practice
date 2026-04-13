package com.springboot.jpb.controller;

import com.springboot.jpb.dto.CreateJobRequestDTO;
import com.springboot.jpb.dto.CreateJobResponseDTO;
import com.springboot.jpb.dto.JobResponseDTO;
import com.springboot.jpb.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping
    public ResponseEntity<CreateJobResponseDTO> createJob(@RequestBody CreateJobRequestDTO createJobRequestDTO) {
        return ResponseEntity.ok(jobService.createJob(createJobRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<JobResponseDTO>> getJobById() {
        return ResponseEntity.ok(jobService.getJobs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobResponseDTO> getJobById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }
}
