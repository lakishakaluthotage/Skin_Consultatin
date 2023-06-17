package Model;
import java.time.LocalDate;
public class Patient extends Person {
    private String patientId; //declaring the unique variable

    //constructor for Patient class
    public Patient(){}

    public Patient(String name, String surName, LocalDate dateOfBirth, String mobileNumber, String patientId) {
        super(name, surName, dateOfBirth, mobileNumber);
        this.patientId = patientId;
    }

    //getter and setter for patientId instance variable
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}