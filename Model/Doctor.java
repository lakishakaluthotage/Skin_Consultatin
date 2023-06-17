package Model;
        import java.time.LocalDate;
public class Doctor extends Person{
    //declaring instance varriables
    private String medicalLicenceNumber;
    private String specialisation;

    public Doctor() {
    }
    //constructor for the Doctor class
    public Doctor(String name, String surName, LocalDate dateOfBirth, String mobileNumber, String medicalLicenceNumber,
                  String specialisation) {
        super(name, surName, dateOfBirth, mobileNumber);
        this.medicalLicenceNumber = medicalLicenceNumber;
        this.specialisation = specialisation;
    }

    //adding getters and setters for the instance variables
    public String getMedicalLicenceNumber() {
        return medicalLicenceNumber;
    }

    public void setMedicalLicenceNumber(String medicalLicenceNumber) {
        this.medicalLicenceNumber = medicalLicenceNumber;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    @Override
    public String toString() {
        return super.toString() +
                "medicalLicenceNumber = " + medicalLicenceNumber + "\n" +
                "specialisation = " + specialisation+"\n";
    }

}

