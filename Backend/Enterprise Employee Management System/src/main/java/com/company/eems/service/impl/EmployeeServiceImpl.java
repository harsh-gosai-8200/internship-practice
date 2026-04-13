package com.company.eems.service.impl;

import com.company.eems.exception.EmployeeNotFoundException;
import com.company.eems.exception.InvalidEmployeeDataException;
import com.company.eems.model.Employee;
import com.company.eems.repository.impl.FileEmployeeRepositoryImpl;
import com.company.eems.service.EmployeeService;
import com.company.eems.util.LoggerUtil;

import java.util.List;
import java.util.logging.Logger;

/**
 * Implementation of EmployeeService.
 * <p>
 * Contains all business rules and validations.
 * </p>
 */
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER =
            LoggerUtil.getLogger(EmployeeServiceImpl.class);

    private final FileEmployeeRepositoryImpl repository;

    /**
     * Constructs EmployeeServiceImpl.
     * @param repository employee repository
     */
    public EmployeeServiceImpl(FileEmployeeRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public void addEmployee(Employee employee) {
        LOGGER.info("Entering addEmployee()");

        validateEmployee(employee);
        repository.save(employee);

        LOGGER.info("Exiting addEmployee()");
    }

    @Override
    public List<Employee> getAllEmployees() {
        LOGGER.info("Fetching all employees");
        return repository.findAll();
    }

    @Override
    public Employee getEmployeeById(int id) {
        LOGGER.info("Fetching employee with ID: " + id);

        return repository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.severe("Employee not found: " + id);
                    return new EmployeeNotFoundException(
                            "Employee not found with ID: " + id);
                });
    }

    @Override
    public double calculateAnnualSalary(int id) {
        LOGGER.info("Calculating annual salary for ID: " + id);

        Employee employee = getEmployeeById(id);
        double annualSalary = employee.getMonthlySalary() * 12;

        LOGGER.info("Annual salary calculated: " + annualSalary);
        return annualSalary;
    }

    @Override
    public void removeEmployee(int id) {
        LOGGER.info("Removing employee with ID: " + id);

        boolean removed = repository.deleteById(id);
        if (!removed) {
            LOGGER.severe("Failed to remove employee: " + id);
            throw new EmployeeNotFoundException(
                    "Cannot delete. Employee not found with ID: " + id);
        }

        LOGGER.info("Employee removed successfully");
    }

    /**
     * Validates employee data.
     * @param employee employee object
     */
    private void validateEmployee(Employee employee) {
        LOGGER.fine("Validating employee data");

        if (employee == null) {
            throw new InvalidEmployeeDataException("Employee cannot be null");
        }
        if (employee.getId() <= 0) {
            throw new InvalidEmployeeDataException("Invalid employee ID");
        }
        if (employee.getName() == null || employee.getName().trim().isEmpty()) {
            throw new InvalidEmployeeDataException("Employee name is required");
        }
        if (employee.getMonthlySalary() <= 0) {
            throw new InvalidEmployeeDataException("Salary must be positive");
        }
    }
}
