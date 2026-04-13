package com.springboot.mvc_crud.services;

import com.springboot.mvc_crud.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int id);

    Employee save(Employee employee);

    void deletebyId(int id);

}
