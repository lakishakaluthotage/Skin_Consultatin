package Model;
public class Availability {
    private Doctor doctor;
    private String day;

    public Availability(){}
    public Availability(Doctor doctor,String day){
        this.doctor = doctor;
        this.day = day;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }


}
