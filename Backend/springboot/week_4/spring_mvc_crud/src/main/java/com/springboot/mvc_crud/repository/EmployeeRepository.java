package com.springboot.mvc_crud.repository;

import com.springboot.mvc_crud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(path="members")
public interface EmployeeRepository extends JpaRepository<Employee, Integer > {

    public List<Employee> findAllByOrderByLastNameAsc();

}
