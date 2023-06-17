package GUI;

import Console.WestminsterSkinConsultationManager;
import Model.Availability;
import Model.Consultation;
import Model.Doctor;
import Model.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

import static Console.WestminsterSkinConsultationManager.*;

public class AddConsultation extends JFrame{
    private JTable table;
    private String[] days=
            {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};


    public AddConsultation() {
        setTitle("Add Consultation");
        setSize(850, 586);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        //JTable
        table = new JTable();
        String[] columNames = {"Licence No", "Doctor Name", "Specialisation", "Patient ID","Patient Name",
                "Session Date","Duration"};
        Object[][] data = new Object[][]{};
        TableModel model = new DefaultTableModel(data, columNames);
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setRowHeight(30);

        JPanel tablePanel = new JPanel();
        scrollPane.add(tablePanel);
        scrollPane.setBounds(50, 215, 750, 259);
        add(scrollPane);

        JLabel patientIdLabel = new JLabel("Patient ID");
        patientIdLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
        patientIdLabel.setBounds(50, 135, 99, 16);
        add(patientIdLabel);

        JComboBox patientIdComboBox = new JComboBox();
        patientIdComboBox.setBounds(141, 131, 150, 27);
        WestminsterSkinConsultationManager.getPatientList().forEach(patient -> {
            patientIdComboBox.addItem(patient.getPatientId());
        });
        add(patientIdComboBox);

        JLabel doctorId = new JLabel("Doctor ID");
        doctorId.setFont(new Font("Georgia", Font.PLAIN, 15));
        doctorId.setBounds(50, 52, 99, 16);
        add(doctorId);

        JComboBox doctorIDComboBox = new JComboBox();
        WestminsterSkinConsultationManager.getConsoleDoctorList().forEach(doctor -> {
            doctorIDComboBox.addItem(doctor.getMedicalLicenceNumber());
        });
        doctorIDComboBox.setBounds(141, 48, 150, 27);
        add(doctorIDComboBox);

        JLabel dateLabel = new JLabel("Date");
        dateLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
        dateLabel.setBounds(321, 52, 45, 16);
        add(dateLabel);

        JComboBox dateComboBox = new JComboBox(days);
        dateComboBox.setBounds(402, 48, 121, 27);
        add(dateComboBox);

        //check availability of the doctor
        Availability selectedAvailability = new Availability();
        JButton checkButton = new JButton("Check");
        checkButton.setBounds(596, 47, 117, 29);
        checkButton.setFocusable(false);
        checkButton.setBackground(new Color(149, 193, 232));
        checkButton.setFont(new Font("Georgia",Font.BOLD,15));
        checkButton.addActionListener(e->{
            getAvailabilityList().forEach(availability -> {
                if (availability.getDoctor().getMedicalLicenceNumber().equals(doctorIDComboBox.getSelectedItem().toString())
                        && availability.getDay().equals(dateComboBox.getSelectedItem().toString())){
                    JOptionPane.showMessageDialog(null,
                            "Doctor name = " + availability.getDoctor().getName() +
                                    "\nDoctor spec = " + availability.getDoctor().getSpecialisation() +
                                    "\nDoctor mobile = " + availability.getDoctor().getMobileNumber());
                }
            });

        });
        add(checkButton);

        JLabel durationLabel = new JLabel("Duration");
        durationLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
        durationLabel.setBounds(321, 135, 69, 16);
        add(durationLabel);

        JComboBox durationComboBox = new JComboBox();
        for(int i = 1;i<6;i++){
            durationComboBox.addItem(i);
        }
        durationComboBox.setBounds(402, 131, 121, 27);
        add(durationComboBox);

        //saving data to the table
        JButton saveButton = new JButton("Save");
        saveButton.setBounds(596, 130, 117, 29);
        saveButton.setFocusable(false);
        saveButton.setBackground(new Color(149, 193, 232));
        saveButton.setFont(new Font("Georgia",Font.BOLD,15));
        saveButton.addActionListener(e->{
            Availability selectAvailability = new Availability();
            for(Availability a : getAvailabilityList()){
                if (a.getDoctor().getMedicalLicenceNumber().equals(doctorIDComboBox.getSelectedItem().toString())
                        && a.getDay().equals(dateComboBox.getSelectedItem().toString())){
                    selectAvailability = a;
                }
            }

            Patient patient = new Patient();
            for(Patient p : getPatientList()){
                if (patientIdComboBox.getSelectedItem().toString().equals(p.getPatientId())){
                    patient = p;
                }
            }
            Consultation consultation = new Consultation(selectAvailability,patient,
                    dateComboBox.getSelectedItem().toString(),
                    durationComboBox.getSelectedItem().toString());
            addConsultation(consultation);
            DefaultTableModel model1=(DefaultTableModel) table.getModel();
            model1.addRow(new Object[]{
                    selectAvailability.getDoctor().getMedicalLicenceNumber(),
                    selectAvailability.getDoctor().getName(),
                    selectAvailability.getDoctor().getSpecialisation(),
                    patient.getPatientId(),
                    patient.getName(),
                    selectAvailability.getDay(),
                    durationComboBox.getSelectedItem().toString()
            });

        });
        add(saveButton);
        setVisible(true);

    }

    public static void main(String[] args) {
        new AddConsultation();
    }

}
