package com.springboot.jpb.service;

import com.springboot.jpb.dto.CreateJobRequestDTO;
import com.springboot.jpb.dto.CreateJobResponseDTO;
import com.springboot.jpb.dto.JobResponseDTO;
import com.springboot.jpb.entity.Job;
import com.springboot.jpb.entity.User;
import com.springboot.jpb.repository.ApplicationRepository;
import com.springboot.jpb.repository.JobRepository;
import com.springboot.jpb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;

    @Override
    @Transactional
    public CreateJobResponseDTO createJob(CreateJobRequestDTO createJobRequestDTO) {
        User user = userRepository.findById(createJobRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Job job = Job.builder()
                    .title(createJobRequestDTO.getTitle())
                    .description(createJobRequestDTO.getDescription())
                    .salary(createJobRequestDTO.getSalary())
                    .location(createJobRequestDTO.getLocation())
                    .createdBy(user)
                    .build();
        Job savedJob = jobRepository.save(job);

        return CreateJobResponseDTO.builder()
                .jobId(savedJob.getId())
                .title(savedJob.getTitle())
                .description(savedJob.getDescription())
                .salary(savedJob.getSalary())
                .location(savedJob.getLocation())
                .createdBy(user.getId())
                .build();
    }

    @Override
    public List<JobResponseDTO> getJobs() {
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream()
                .map(job -> JobResponseDTO.builder()
                        .jobId(job.getId())
                        .title(job.getTitle())
                        .description(job.getDescription())
                        .location(job.getLocation())
                        .salary(job.getSalary())
                        .applicationCount(applicationRepository.countByJob(job))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public JobResponseDTO getJobById(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found"));

        return JobResponseDTO.builder()
                .jobId(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .location(job.getLocation())
                .salary(job.getSalary())
                .applicationCount(applicationRepository.countByJob(job))
                .build();
    }
}
