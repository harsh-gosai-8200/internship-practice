package com.springboot.jpb.repository;

import com.springboot.jpb.entity.Application;
import com.springboot.jpb.entity.Job;
import com.springboot.jpb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    long countByJob(Job job);

    boolean existsByUserAndJob(User user, Job job);

    List<Application> findByUser(User user);

    List<Application> findByJob(Job job);
}
