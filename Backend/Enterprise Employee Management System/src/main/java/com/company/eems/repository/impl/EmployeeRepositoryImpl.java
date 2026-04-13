package com.company.eems.repository.impl;

import com.company.eems.model.Employee;
import com.company.eems.repository.EmployeeRepository;
import com.company.eems.util.LoggerUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * In-memory implementation of EmployeeRepository.
 * <p>
 * This implementation uses a List as data storage.
 * Can be replaced with file or database storage later.
 * </p>
 */
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private static final Logger LOGGER =
            LoggerUtil.getLogger(EmployeeRepositoryImpl.class);

    private final List<Employee> employeeStore = new ArrayList<>();

    @Override
    public void save(Employee employee) {
        LOGGER.info("Saving employee with ID: " + employee.getId());
        employeeStore.add(employee);
        LOGGER.info("Employee saved successfully");
    }

    @Override
    public List<Employee> findAll() {
        LOGGER.fine("Fetching all employees");
        return new ArrayList<>(employeeStore); // defensive copy
    }

    @Override
    public Optional<Employee> findById(int id) {
        LOGGER.info("Searching employee with ID: " + id);

        return employeeStore.stream()
                .filter(emp -> emp.getId() == id)
                .findFirst();
    }

    @Override
    public boolean deleteById(int id) {
        LOGGER.warning("Attempting to delete employee with ID: " + id);

        Iterator<Employee> iterator = employeeStore.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
                LOGGER.info("Employee deleted successfully");
                return true;
            }
        }

        LOGGER.warning("Employee not found for deletion");
        return false;
    }
}
