package com.springboot.spring_mvc_validators.entity;

import com.springboot.spring_mvc_validators.validation.CourseCode;
import jakarta.validation.constraints.*;

public class Customer {

    private String firstName;

    @NotNull(message="is require")
    @Size(min = 3, message = "minimum 1 charactor require")
    private String lastName;

    @NotNull(message="is require")
    @Min(value = 0, message = "value should be greater than 0")
    @Max(value = 10, message = "value should be less than 10")
    private Integer feePases;

    @Pattern(regexp = "^[a-zA-Z0-9]{6}", message = "only 6 digits allow in postal code")
    private String postalCode;

    @CourseCode(value = "HARSH", message = "must starts with HARSH")
    private String courseCode;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getFeePases() {
        return feePases;
    }

    public void setFeePases(Integer feePases) {
        this.feePases = feePases;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
