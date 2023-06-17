package Model;

public class Consultation {

    private Availability availabilityDoctor;
    private Patient patient;
    private String date;
    private String consultationHours;

    public Availability getAvailabilityDoctor() {
        return availabilityDoctor;
    }

    public void setAvailabilityDoctor(Availability availabilityDoctor) {
        this.availabilityDoctor = availabilityDoctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getConsultationHours() {
        return consultationHours;
    }

    public void setConsultationHours(String consultationHours) {
        this.consultationHours = consultationHours;
    }

    public Consultation(Availability availabilityDoctor, Patient patient, String date, String consultationHours) {
        this.availabilityDoctor = availabilityDoctor;
        this.patient = patient;
        this.date = date;
        this.consultationHours = consultationHours;
    }

    public Consultation() {
    }
}
