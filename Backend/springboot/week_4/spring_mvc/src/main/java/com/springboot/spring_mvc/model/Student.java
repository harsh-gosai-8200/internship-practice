package com.springboot.spring_mvc.model;


import java.util.List;

public class Student {

    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private String favoriteLanguage;
    private List<String> currentDevices;

    public Student(){}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastNAme) {
        this.lastName = lastNAme;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFavoriteLanguage() {
        return favoriteLanguage;
    }

    public void setFavoriteLanguage(String favoriteLanguage) {
        this.favoriteLanguage = favoriteLanguage;
    }

    public List<String> getCurrentDevices() {
        return currentDevices;
    }

    public void setCurrentDevices(List<String> currentDevices) {
        this.currentDevices = currentDevices;
    }
}
