package com.company.eems.repository;

import com.company.eems.model.Employee;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Employee data operations.
 * <p>
 * Acts as a contract between service layer and data storage.
 * </p>
 */
public interface EmployeeRepository {

    /**
     * Saves a new employee in the system.
     * @param employee employee object to save
     */
    void save(Employee employee);

    /**
     * Retrieves all employees.
     * @return list of employees
     */
    List<Employee> findAll();

    /**
     * Finds an employee by id.
     * @param id employee id
     * @return Optional containing employee if found
     */
    Optional<Employee> findById(int id);

    /**
     * Deletes an employee by id.
     * @param id employee id
     * @return true if deleted, false otherwise
     */
    boolean deleteById(int id);
}
