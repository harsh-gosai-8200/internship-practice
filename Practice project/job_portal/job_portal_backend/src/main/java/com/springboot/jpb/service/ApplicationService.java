package com.springboot.jpb.service;

import com.springboot.jpb.dto.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ApplicationService {

    void applyJob(ApplyJobRequestDTO applyJobRequestDTO);

    List<UserApplicationResponseDTO> getApplicationsByUser(Long userId);

    List<JobApplicationsResponseDTO> getJobApplications(Long jobId);

    StatusUpdateResponseDTO updateStatus(Long id, StatusUpdateRequestDTO statusUpdateRequestDTO);
}
