package com.company.eems.main;

import com.company.eems.enums.Role;
import com.company.eems.model.Employee;
import com.company.eems.repository.impl.EmployeeRepositoryImpl;
import com.company.eems.repository.impl.FileEmployeeRepositoryImpl;
import com.company.eems.service.EmployeeService;
import com.company.eems.service.impl.EmployeeServiceImpl;
import com.company.eems.util.ConsoleUtil;
import com.company.eems.util.LoggerUtil;

import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Entry point of the Enterprise Employee Management System.
 */
public class ApplicationLauncher {


    private static final Logger LOGGER =
            LoggerUtil.getLogger(ApplicationLauncher.class);

    private static EmployeeService employeeService;

    public static void main(String[] args) {

        LOGGER.info("Application started");

        employeeService = new EmployeeServiceImpl(
                new FileEmployeeRepositoryImpl()
        );


        boolean running = true;

        while (running) {
            printMenu();
            int choice = ConsoleUtil.readInt("Choose option: ");

            switch (choice) {
                case 1:
                    addEmployee();
                    break;

                case 2:
                    listEmployees();
                    break;

                case 3:
                    calculateSalary();
                    break;

                case 4:
                    deleteEmployee();
                    break;

                case 5:
                    running = false;
                    LOGGER.info("Application shutting down");
                    System.out.println("Exiting application...");
                    break;

                default:
                    System.out.println("Invalid choice");
                    LOGGER.warning("Invalid menu option selected");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n===== EMPLOYEE MANAGEMENT MENU =====");
        System.out.println("1. Add Employee");
        System.out.println("2. View All Employees");
        System.out.println("3. Calculate Annual Salary");
        System.out.println("4. Delete Employee");
        System.out.println("5. Exit");
    }

    private static void addEmployee() {
        try {
            LOGGER.info("Add employee flow started");

            int id = ConsoleUtil.readInt("Enter ID: ");
            String name = ConsoleUtil.readString("Enter Name: ");
            double salary = ConsoleUtil.readDouble("Enter Monthly Salary: ");

            System.out.println("Roles: INTERN, DEVELOPER, SENIOR_DEVELOPER, MANAGER, HR");
            String roleInput = ConsoleUtil.readString("Enter Role: ");
            Role role = Role.valueOf(roleInput.toUpperCase());

            Employee employee = new Employee(id, name, role, salary);
            employeeService.addEmployee(employee);

            System.out.println("Employee added successfully");

        } catch (Exception e) {
            LOGGER.severe("Error adding employee: " + e.getMessage());
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listEmployees() {
        LOGGER.info("Listing employees");

        List<Employee> employees = employeeService.getAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employees found");
            return;
        }

        employees.forEach(emp ->
                System.out.println(emp.getId() + " | "
                        + emp.getName() + " | "
                        + emp.getRole() + " | "
                        + emp.getMonthlySalary())
        );
    }

    private static void calculateSalary() {
        try {
            int id = ConsoleUtil.readInt("Enter Employee ID: ");
            double annualSalary =
                    employeeService.calculateAnnualSalary(id);

            System.out.println("Annual Salary: " + annualSalary);

        } catch (Exception e) {
            LOGGER.severe("Salary calculation failed: " + e.getMessage());
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deleteEmployee() {
        try {
            int id = ConsoleUtil.readInt("Enter Employee ID: ");
            employeeService.removeEmployee(id);
            System.out.println("Employee deleted successfully");

        } catch (Exception e) {
            LOGGER.severe("Employee deletion failed: " + e.getMessage());
            System.out.println("Error: " + e.getMessage());
        }
    }
}
