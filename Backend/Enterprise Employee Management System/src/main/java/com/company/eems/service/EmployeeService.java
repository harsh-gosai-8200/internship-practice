package com.company.eems.service;

import com.company.eems.model.Employee;
import java.util.List;

/**
 * Service interface for employee business operations.
 */
public interface EmployeeService {

    /**
     * Adds a new employee after validation.
     * @param employee employee object
     */
    void addEmployee(Employee employee);

    /**
     * Fetches all employees.
     * @return list of employees
     */
    List<Employee> getAllEmployees();

    /**
     * Retrieves employee by id.
     * @param id employee id
     * @return employee object
     */
    Employee getEmployeeById(int id);

    /**
     * Calculates annual salary of an employee.
     * @param id employee id
     * @return annual salary
     */
    double calculateAnnualSalary(int id);

    /**
     * Removes an employee by id.
     * @param id employee id
     */
    void removeEmployee(int id);
}
