package com.springboot.rest_crud_api.services;

import com.springboot.rest_crud_api.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int id);

    Employee save(Employee employee);

    void deletebyId(int id);

}
