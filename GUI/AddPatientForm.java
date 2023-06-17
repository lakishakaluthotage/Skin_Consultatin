package GUI;

        import Console.WestminsterSkinConsultationManager;
        import Model.Patient;
        import com.toedter.calendar.JDateChooser;
        import javax.swing.*;
        import java.awt.*;
        import java.time.LocalDate;
public class AddPatientForm extends JFrame {
    private JTextField nameTxt;
    private JTextField surnameText;
    private JTextField contactNoText;
    private JTextField NICtext;
    private JDateChooser dateChooser;

    public AddPatientForm() {
        setTitle("Add Patient");
        setSize(504, 509);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel patientInfoLabel = new JLabel("Patient Information");
        patientInfoLabel.setFont(new Font("Georgia", Font.BOLD, 20));
        patientInfoLabel.setForeground(new Color(14, 173, 128));
        patientInfoLabel.setBounds(188, 18, 250, 35);
        add(patientInfoLabel);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("Georgia", Font.PLAIN, 18));
        nameLabel.setBounds(29, 78, 117, 35);
        add(nameLabel);

        JLabel surnameLabel = new JLabel("Surname");
        surnameLabel.setFont(new Font("Georgia", Font.PLAIN, 18));
        surnameLabel.setBounds(29, 137, 117, 35);
        add(surnameLabel);

        JLabel dateOfBirthLabel = new JLabel("Date of Birth");
        dateOfBirthLabel.setFont(new Font("Georgia", Font.PLAIN, 18));
        dateOfBirthLabel.setBounds(29, 205, 117, 35);
        add(dateOfBirthLabel);

        JLabel NICLabel = new JLabel("NIC");
        NICLabel.setFont(new Font("Georgia", Font.PLAIN, 18));
        NICLabel.setBounds(29, 277, 117, 35);
        add(NICLabel);

        JLabel contactNoLabel = new JLabel("Contact No");
        contactNoLabel.setFont(new Font("Georgia", Font.PLAIN, 18));
        contactNoLabel.setBounds(29, 343, 117, 35);
        add(contactNoLabel);

        nameTxt = new JTextField();
        nameTxt.setColumns(10);
        nameTxt.setBounds(188, 72, 198, 50);
        nameTxt.setFont(new Font("SERIF",Font.PLAIN,18));
        add(nameTxt);

        surnameText = new JTextField();
        surnameText.setColumns(10);
        surnameText.setBounds(188, 131, 198, 50);
        surnameText.setFont(new Font("SERIF",Font.PLAIN,18));
        add(surnameText);

        contactNoText = new JTextField();
        contactNoText.setColumns(10);
        contactNoText.setBounds(188, 337, 198, 50);
        contactNoText.setFont(new Font("SERIF",Font.PLAIN,18));
        add(contactNoText);

        NICtext = new JTextField();
        NICtext.setColumns(10);
        NICtext.setBounds(188, 271, 198, 50);
        NICtext.setFont(new Font("SERIF",Font.PLAIN,18));
        add(NICtext);

        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setFont(new Font("Arial", Font.PLAIN, 15));
        dateChooser.setBounds(188, 199, 198, 50);
        add(dateChooser);

        JButton saveButton = new JButton("Save");
        saveButton.setFont(new Font("Georgia", Font.BOLD, 15));
        saveButton.setBounds(361, 412, 102, 40);
        saveButton.setBackground(new Color(149, 193, 232));
        saveButton.setFocusable(false);
        add(saveButton);
        saveButton.addActionListener(e-> {
            if(nameTxt.getText().isEmpty()|| surnameText.getText().isEmpty()|| dateChooser.getDate()==null|| contactNoText.getText().isEmpty()||NICtext.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Fill all the data to proceed!");
            }else{
                if(e.getSource()==saveButton){
                    int year = dateChooser.getDate().getYear() + 1900;
                    int mon = dateChooser.getDate().getMonth() + 1;
                    int date = dateChooser.getDate().getDay()+1;


                    WestminsterSkinConsultationManager.addPatient(new Patient(
                            nameTxt.getText(),surnameText.getText(), LocalDate.of(year,mon,date),contactNoText.getText(),NICtext.getText()
                    ));

                }
            }
        });

        JButton resetButton=new JButton("Reset");
        resetButton.setFont(new Font("Georgia", Font.BOLD, 15));
        resetButton.setBounds(240, 412, 102, 40);
        resetButton.setBackground(new Color(149, 193, 232));
        resetButton.setFocusable(false);

        resetButton.addActionListener(e->{
            nameTxt.setText(null);
            surnameText.setText(null);
            dateChooser.setCalendar(null);
            NICtext.setText(null);
            contactNoText.setText(null);

        });
        add(resetButton);

        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        new AddPatientForm();
    }

}
