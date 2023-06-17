package GUI;
        import javax.swing.*;
        import java.awt.*;
public class MainGUI extends JFrame {

    public MainGUI() {
        setTitle("Westminster Skin Consultant Manager");
        setSize(483, 314);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JButton patientButton = new JButton("Add Patient");
        patientButton.setBounds(59, 59, 152, 47);
        patientButton.setFocusable(false);
        patientButton.setBackground(new Color(149, 193, 232));
        patientButton.addActionListener(e->{
            new AddPatientForm();
        });
        add(patientButton);

        JButton availabilityButton = new JButton("Set Availability");
        availabilityButton.setBounds(59, 136, 152, 47);
        availabilityButton.setBackground(new Color(149, 193, 232));
        availabilityButton.setFocusable(false);
        availabilityButton.addActionListener(e->{
            new SetAvailability ();
        });
        add(availabilityButton);

        JButton viewDoctorButton = new JButton("View Doctors");
        viewDoctorButton.setBounds(274, 59, 152, 47);
        viewDoctorButton.setBackground(new Color(149, 193, 232));
        viewDoctorButton.setFocusable(false);
        viewDoctorButton.addActionListener(e->{
            new ViewDoctors();
        });
        add(viewDoctorButton);

        JButton consultationButton = new JButton("Add Consultation");
        consultationButton.setBounds(274, 136, 152, 47);
        consultationButton.setBackground(new Color(149, 193, 232));
        consultationButton.setFocusable(false);
        consultationButton.addActionListener(e->{
            new AddConsultation();
        });
        add(consultationButton);


        JLabel welcomeLabel = new JLabel("Westminster Skin Care Center");
        welcomeLabel.setFont(new Font("Georgia", Font.BOLD, 15));
        welcomeLabel.setBounds(114, 21, 250, 16);
        add(welcomeLabel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainGUI();
    }

}
