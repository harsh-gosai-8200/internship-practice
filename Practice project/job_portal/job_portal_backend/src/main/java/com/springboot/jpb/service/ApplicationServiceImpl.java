package com.springboot.jpb.service;

import com.springboot.jpb.dto.*;
import com.springboot.jpb.entity.Application;
import com.springboot.jpb.entity.Job;
import com.springboot.jpb.entity.User;
import com.springboot.jpb.entity.enums.Status;
import com.springboot.jpb.repository.ApplicationRepository;
import com.springboot.jpb.repository.JobRepository;
import com.springboot.jpb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void applyJob(ApplyJobRequestDTO applyJobRequestDTO) {
        User user = userRepository.findById(applyJobRequestDTO.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Job job = jobRepository.findById(applyJobRequestDTO.getJobId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found"));

        if(applicationRepository.existsByUserAndJob(user, job)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already applied");
        }

        Application application = Application.builder()
                .job(job)
                .user(user)
                .status(Status.APPLIED)
                .build();

        applicationRepository.save(application);
    }

    @Override
    public List<UserApplicationResponseDTO> getApplicationsByUser(Long userId) {
        User user =  userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        List<Application> applications = applicationRepository.findByUser(user);

        return applications.stream()
                .map(application -> UserApplicationResponseDTO.builder()
                        .applicationId(application.getId())
                        .jobTitle(application.getJob().getTitle())
                        .jobLocation(application.getJob().getLocation())
                        .salary(application.getJob().getSalary())
                        .status(application.getStatus().name())
                        .build())
                .toList();
    }

    @Override
    public List<JobApplicationsResponseDTO> getJobApplications(Long jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found"));

        List<Application> applications = applicationRepository.findByJob(job);

        return applications.stream()
                .map(application -> JobApplicationsResponseDTO.builder()
                        .applicationId(application.getId())
                        .candidateName(application.getUser().getName())
                        .candidateEmail(application.getUser().getEmail())
                        .status(application.getStatus().name())
                        .build())
                .toList();
    }

    @Override
    @Transactional
    public StatusUpdateResponseDTO updateStatus(Long applicationId, StatusUpdateRequestDTO statusUpdateRequestDTO) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found"));

        try {
            Status status = Status.valueOf(statusUpdateRequestDTO.getStatus().toUpperCase());
            application.setStatus(status);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid status value");
        }

        applicationRepository.save(application);

        return StatusUpdateResponseDTO.builder()
                .applicationId(application.getId())
                .status(application.getStatus().name())
                .build();
    }
}
