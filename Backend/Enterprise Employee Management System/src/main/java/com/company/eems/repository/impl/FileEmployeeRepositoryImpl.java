package com.company.eems.repository.impl;

import com.company.eems.enums.Role;
import com.company.eems.model.Employee;
import com.company.eems.repository.EmployeeRepository;
import com.company.eems.util.FileUtil;
import com.company.eems.util.LoggerUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * File-based implementation of EmployeeRepository.
 */
public class FileEmployeeRepositoryImpl implements EmployeeRepository {

    private static final Logger LOGGER =
            LoggerUtil.getLogger(FileEmployeeRepositoryImpl.class);

    private static final String FILE_PATH = "employees.txt";

    private final List<Employee> cache = new ArrayList<>();

    /**
     * Loads employee data from file during initialization.
     */
    public FileEmployeeRepositoryImpl() {
        loadFromFile();
    }

    @Override
    public void save(Employee employee) {
        LOGGER.info("Saving employee to file: " + employee.getId());
        cache.add(employee);
        persist();
    }

    @Override
    public List<Employee> findAll() {
        return new ArrayList<>(cache);
    }

    @Override
    public Optional<Employee> findById(int id) {
        return cache.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
    }

    @Override
    public boolean deleteById(int id) {
        Iterator<Employee> iterator = cache.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
                persist();
                return true;
            }
        }
        return false;
    }

    /**
     * Loads employees from file into memory.
     */
    private void loadFromFile() {
        try {
            LOGGER.info("Loading employees from file");
            List<String> lines = FileUtil.readLines(FILE_PATH);

            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    Employee employee = new Employee(
                            Integer.parseInt(parts[0]),
                            parts[1],
                            Role.valueOf(parts[2]),
                            Double.parseDouble(parts[3])
                    );
                    cache.add(employee);
                }
            }
        } catch (IOException e) {
            LOGGER.severe("Failed to load employees: " + e.getMessage());
        }
    }

    /**
     * Persists in-memory data to file.
     */
    private void persist() {
        try {
            List<String> lines = new ArrayList<>();
            for (Employee e : cache) {
                lines.add(
                        e.getId() + "," +
                                e.getName() + "," +
                                e.getRole() + "," +
                                e.getMonthlySalary()
                );
            }
            FileUtil.writeLines(FILE_PATH, lines);
        } catch (IOException e) {
            LOGGER.severe("Failed to persist employees: " + e.getMessage());
        }
    }
}
