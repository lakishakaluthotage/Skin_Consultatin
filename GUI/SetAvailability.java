package GUI;

        import Console.WestminsterSkinConsultationManager;
        import Model.Availability;
        import Model.Doctor;

        import javax.swing.*;
        import java.awt.*;
        import javax.swing.table.DefaultTableModel;
        import javax.swing.table.TableModel;
        import static Console.WestminsterSkinConsultationManager.getConsoleDoctorList;

public class SetAvailability extends JFrame {

    private JTextField doctorTxtField;
    private JTable table;
    private JComboBox datePicker;


    private String days[]=
            {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};

    public SetAvailability () {
        setTitle("Add Availability");
        setSize(788, 587);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel setAppointmentLabel = new JLabel("Set Doctor Consultation Time");
        setAppointmentLabel.setFont(new Font("Georgia", Font.BOLD, 18));
        setAppointmentLabel.setBounds(203, 24, 300, 33);
        setAppointmentLabel.setForeground(new Color(14, 173, 128));
        add(setAppointmentLabel);

        JLabel doctorIdLabel = new JLabel("Doctor Licence No:");
        doctorIdLabel.setFont(new Font("Georgia", Font.PLAIN, 18));
        doctorIdLabel.setBounds(34, 85, 180, 35);
        add(doctorIdLabel);

        JLabel dateLabel = new JLabel("Date");
        dateLabel.setFont(new Font("Georgia", Font.PLAIN, 18));
        dateLabel.setBounds(34, 154, 117, 35);
        add(dateLabel);

        doctorTxtField = new JTextField();
        doctorTxtField.setColumns(10);
        doctorTxtField.setBounds(202, 79, 100, 40);
        doctorTxtField.setFont(new Font("Georgia", Font.PLAIN, 18));
        add(doctorTxtField);

        datePicker=new JComboBox(days);
        datePicker.setFont(new Font("Georgia", Font.PLAIN, 18));
        datePicker.setSize(198,40);;
        datePicker.setLocation(202,148);
        add(datePicker);


        JButton addButton = new JButton("Add");
        addButton.setFont(new Font("Georgia", Font.BOLD, 15));
        addButton.setBounds(478, 154, 102, 40);
        addButton.setBackground(new Color(149, 193, 232));
        addButton.setFocusable(false);
        add(addButton);
        addButton.addActionListener(e-> {
            if(doctorTxtField.getText().isEmpty() || datePicker.getItemCount()==0)
            {
                JOptionPane.showMessageDialog(null,"Please enter all the fields");
            }
            else{
                String doctorID= doctorTxtField.getText();
                String  date= datePicker.getSelectedItem().toString();
                Doctor selectedDoctor = new Doctor();
                DefaultTableModel model=(DefaultTableModel) table.getModel();
                for (Doctor doctor : getConsoleDoctorList()) {
                    if(doctor.getMedicalLicenceNumber().equals(doctorID)){
                        selectedDoctor = doctor;
                        model.addRow(new Object[]{
                                doctor.getMedicalLicenceNumber(),
                                doctor.getName(),
                                doctor.getMobileNumber(),
                                doctor.getSpecialisation(),
                                date,
                                ""
                        });
                        break;
                    }
                }
                WestminsterSkinConsultationManager.addAvailability(new Availability(selectedDoctor,date));
            }
        });

        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Georgia", Font.BOLD, 15));
        resetButton.setBounds(602, 154, 102, 40);
        resetButton.setBackground(new Color(149, 193, 232));
        resetButton.setFocusable(false);
        add(resetButton);
        resetButton.addActionListener(e->{
            ((DefaultTableModel)table.getModel()).setRowCount(0);
        });

        //JTable
        table = new JTable();
        String[] columNames = {"Medical Licence No", "Doctor Name", "Specialisation", "Mobile Number", "Session Date"};
        Object[][] data = new Object[][]{};
        TableModel model = new DefaultTableModel(data, columNames);
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setRowHeight(30);

        JPanel tablePanel = new JPanel();
        scrollPane.add(tablePanel);
        scrollPane.setBounds(34, 250, 720, 259);
        add(scrollPane);

        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        new SetAvailability();
    }

}
