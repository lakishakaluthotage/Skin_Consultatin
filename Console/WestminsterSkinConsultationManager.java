package Console;

        import GUI.MainGUI;
        import Model.Availability;
        import Model.Consultation;
        import Model.Doctor;
        import Model.Patient;
        import java.io.*;
        import java.time.LocalDate;
        import java.util.Comparator;
        import java.util.InputMismatchException;
        import java.util.LinkedList;
        import java.util.Scanner;

public class WestminsterSkinConsultationManager implements SkinConsultationManager{
    public final static LinkedList<Doctor> DoctorList = new LinkedList<>();
    public final static LinkedList<Consultation> ConsultationList = new LinkedList<>();
    public final static LinkedList<Availability> AvailabilityList = new LinkedList<>();
    public final static LinkedList<Patient> PatientList = new LinkedList<>();
    static int  doctorCount=0;

    //main menu of the program
    public static void mainMenuProgram() {
        Scanner input = new Scanner(System.in);
        int option = 0;

        try{
            do{
                System.out.println("|--------------------------------------------------------------|");
                System.out.println("|                                                              |");
                System.out.println("|                     ~ MAIN MENU ~                            |");
                System.out.println("|                                                              |");
                System.out.println("|--------------------------------------------------------------|");
                System.out.println("|         1) Add doctor                                        |");
                System.out.println("|         2) Delete doctor                                     |");
                System.out.println("|         3) Display doctors                                   |");
                System.out.println("|         4) Save to file                                      |");
                System.out.println("|         5) Load Information                                  |");
                System.out.println("|         6) Switch to GUI                                     |");
                System.out.println("|         7) Exit Menu                                         |");
                System.out.println("|                                                              |");
                System.out.println("|--------------------------------------------------------------|");
                System.out.print("Enter your option: ");

                option = input.nextInt();
                switch (option) {
                    case 1 -> getDataFromDoctor();
                    case 2 -> deleteDataFromDoctor();
                    case 3 -> printDoctorList();
                    case 4 -> saveDoctorData();
                    case 5 -> loadDoctorData();
                    case 6 -> new MainGUI();
                    case 7 -> System.exit(1);
                    default -> {
                        System.out.println("INVALID INPUT ENTERED "+option);
                        System.out.println("~ PLEASE TRY AGAIN ~");
                    }
                }
            }while(true);

        }catch (InputMismatchException e){
            System.out.println("INVALID INPUT! TRY AGAIN");
            mainMenuProgram();
        }
    }
//method that load doctors data
    private static void loadDoctorData() {
        try(FileInputStream saveFile = new FileInputStream("saveList.txt");
            ObjectInputStream os = new ObjectInputStream(saveFile))
        {
            System.out.println("Data loaded successfully!");
            for (;;){
                Doctor doctor;
                try{
                    doctor= (Doctor) os.readObject();
                }catch (EOFException e){
                    break;
                }
                DoctorList.add(doctor);
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//method that save doctor details to a text file
    private static void saveDoctorData() {
        try(FileOutputStream saveFile = new FileOutputStream("saveList.txt");
            ObjectOutputStream os = new ObjectOutputStream(saveFile))
        {
            System.out.println("Data Successfully saved to the file");
            DoctorList.forEach(doctor -> {
                try {
                    os.writeObject(doctor);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //method to take deleting doctors details
    private static void deleteDataFromDoctor() {
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.println("\n--------------Deleting a doctor---------");
        System.out.print("Are you sure you want to delete a doctor?(Y/N) ");
        String selection= input.next();
        if(selection.equalsIgnoreCase("Y")){
            System.out.print("Enter Medical Licence Number : ");
            String medicalLC = input.next();
            deleteDoctor(medicalLC);
        }else{
            System.out.println("REDIRECTING TO THE MAIN MENU");
            mainMenuProgram();
        }
    }
//method that delete doctor's records
    private static void deleteDoctor(String medicalLC) {
        DoctorList.forEach(doctor -> {
            if(doctor.getMedicalLicenceNumber().equals(medicalLC)){
                DoctorList.remove(doctor);
                doctorCount--;
                System.out.println("Doctor deleted successfully!");
                System.out.println();
                System.out.println("Details of the deleted doctor-> ");
                System.out.println(doctor);
                System.out.println("Total count of doctors : "+ doctorCount);
            }
        });
    }
//method to sort the added doctors alphabetically by their surnames
    public static LinkedList<Doctor> sortSurname(){
        LinkedList<Doctor> SortSurnameDoctorList = new LinkedList<>(DoctorList);
        SortSurnameDoctorList.sort(new Comparator<>() {
            @Override
            public int compare(Doctor obj1, Doctor obj2) {
                return obj1.getSurname().compareTo(obj2.getSurname());
            }
        });
        return SortSurnameDoctorList;
    }
//method to print doctor details
    private static void printDoctorList(){
        System.out.println("\n--------------Display Doctors---------"); //changed
        LinkedList<Doctor> sortDoctorSurname = sortSurname();

//            normal
        for(Doctor doctor:sortDoctorSurname){
            System.out.println(doctor);
        }

    }
//method to add doctor to the list
    private static void addDoctorList(Doctor doctor){
        DoctorList.add(doctor);
        doctorCount++;
        System.out.println("No of doctors: "+ doctorCount);
    }
    //method to get doctor details
    private static void getDataFromDoctor(){
        Scanner input = new Scanner(System.in);
        boolean flag = false;

        if(doctorCount<10)
        {
            System.out.println();
            System.out.println("\n--------------Adding a doctor---------");

            Doctor doctor = new Doctor();

            System.out.print("Enter doctor name: ");
            String name = input.next();
            doctor.setName(name);

            System.out.print("Enter doctor surname: ");
            String surname = input.next();
            doctor.setSurName(surname);

            System.out.println("Enter date of birth: ");
            System.out.print("Year  - ");
            int year = input.nextInt();
            System.out.print("Month - ");
            int month = input.nextInt();
            System.out.print("Day   - ");
            int day = input.nextInt();

            //Creating a LocalDate Object
            LocalDate birthDate = LocalDate.of(year, month, day);
            doctor.setDateOfBirth(birthDate);

            System.out.print("Enter mobile number: ");
            String mobileNum = input.next();
            doctor.setMobileNumber(mobileNum);

            System.out.print("Enter medical licence number: ");
            String medicalLC = input.next();
            doctor.setMedicalLicenceNumber(medicalLC);

            System.out.print("Specialisations: (Cosmetic dermatology / Medical dermatology/" +
                    " Paediatric dermatology) ");
            System.out.print("Enter specialisation: ");
            String specialisation = input.next();
            doctor.setSpecialisation(specialisation);

            //Add Doctor to list
            addDoctorList(doctor);
        }else{
                System.out.println("Maximum Count of Doctors added already!");
            System.out.println();
            }

        //Ask ques to add New Doctor or Not?
        System.out.print("Do you want to add New Doctor (Y/N) ? ");
        String option = input.next();
        if(option.equalsIgnoreCase("Y")){
            flag = true;
            getDataFromDoctor();
        }else{
            System.out.println();
            mainMenuProgram();
        }
    }

    public static LinkedList<Doctor> getConsoleDoctorList(){
        return new LinkedList<>(DoctorList);
    }

    public static void addAvailability(Availability availability){
        AvailabilityList.add(availability);
    }
    public static void addPatient(Patient patient){
        PatientList.add(patient);
    }

    public static void addConsultation(Consultation consultation){
        ConsultationList.add(consultation);
    }

    public static LinkedList<Patient> getPatientList() {
        return new LinkedList<>(PatientList);}
    public static LinkedList<Availability> getAvailabilityList(){
        return new LinkedList<>(AvailabilityList);}


    public static void main(String[] args) {
        mainMenuProgram();

    }
}

