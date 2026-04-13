package com.springboot.jpb.service;

import com.springboot.jpb.dto.CreateJobRequestDTO;
import com.springboot.jpb.dto.CreateJobResponseDTO;
import com.springboot.jpb.dto.JobResponseDTO;

import java.util.List;

public interface JobService {

    CreateJobResponseDTO createJob(CreateJobRequestDTO createJobRequestDTO);

    List<JobResponseDTO> getJobs();

    JobResponseDTO getJobById(Long id);
}
