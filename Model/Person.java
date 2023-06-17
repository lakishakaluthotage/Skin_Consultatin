package Model;

import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable {
    //declaring variables
    private String name;
    private String surName;
    private LocalDate dateOfBirth;
    private String mobileNumber;

    public Person() {
    }

    //creating the constructor
    public Person(String name, String surName, LocalDate dateOfBirth, String mobileNumber) {
        this.name = name;
        this.surName = surName;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
    }

    //adding getters and setters
    public void setName(String name) {
        this.name = name;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surName;
    }

    public LocalDate getDOB() {
        return dateOfBirth;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    @Override
    public String toString() {
        return "name = " + name + "\n" +
                "surName = " + surName + "\n" +
                "dateOfBirth = " + dateOfBirth + "\n" +
                "mobileNumber = " + mobileNumber + "\n";
    }
}
