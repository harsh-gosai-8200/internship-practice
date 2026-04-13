package com.company.eems.model;

import com.company.eems.enums.Role;

/**
 * Represents an employee entity.
 */
public class Employee {

    private int id;
    private String name;
    private Role role;
    private double monthlySalary;

    /**
     * Constructs an Employee object.
     *
     * @param id unique employee id
     * @param name employee full name
     * @param role employee role
     * @param monthlySalary employee monthly salary
     */
    public Employee(int id, String name, Role role, double monthlySalary) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.monthlySalary = monthlySalary;
    }

    /** @return employee id */
    public int getId() {
        return id;
    }

    /** @return employee name */
    public String getName() {
        return name;
    }

    /** @return employee role */
    public Role getRole() {
        return role;
    }

    /** @return monthly salary */
    public double getMonthlySalary() {
        return monthlySalary;
    }
}
