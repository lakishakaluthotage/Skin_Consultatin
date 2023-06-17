package GUI;

        import javax.swing.*;
        import java.awt.*;
        import java.util.ArrayList;
        import java.util.List;
        import javax.swing.table.DefaultTableModel;
        import javax.swing.table.TableModel;
        import javax.swing.table.TableRowSorter;

        import static Console.WestminsterSkinConsultationManager.getConsoleDoctorList;

public class ViewDoctors extends JFrame {

    private JTable table;
    public TableRowSorter myTableRowSorter;

    public static void main(String[] args) {
        new ViewDoctors();
    }

    public ViewDoctors() {
        setTitle("View Doctors");
        setBounds(100, 100, 711, 575);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JRadioButton surnameButton = new JRadioButton("Surname");
        surnameButton.setFont(new Font("Georgia", Font.BOLD, 15));
        surnameButton.setBounds(41, 24, 141, 23);
        surnameButton.setFocusable(false);
        add(surnameButton);


        //JTable
        table = new JTable();
        String[] columNames = {"Name", "Surname", "Date of Birth","Mobile Number","Medical License","Specialisation"};
        Object[][] data = new Object[][]{};
        TableModel model = new DefaultTableModel(data,columNames);
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setRowHeight(30);

        JButton sortButton = new JButton("Sort");
        sortButton.setFont(new Font("Georgia", Font.BOLD, 15));
        sortButton.setBounds(41, 472, 102, 40);
        sortButton.setBackground(new Color(149, 193, 232));
        sortButton.setFocusable(false);
        add(sortButton);
        sortButton.addActionListener( e->
        {
            myTableRowSorter = new TableRowSorter(model);
            table.setRowSorter(myTableRowSorter);
            java.util.List<RowSorter.SortKey> sortKeys = new ArrayList<>();

            int columnIndexToSort = 1;
            sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));

            myTableRowSorter.setSortKeys(sortKeys);
            myTableRowSorter.sort();
        });

        JPanel tablePanel = new JPanel();
        scrollPane.add(tablePanel);
        scrollPane.setBounds(50, 100, 625, 300);
        add(scrollPane);

        DefaultTableModel model1 = (DefaultTableModel) table.getModel();
        getConsoleDoctorList().forEach(doctor -> {
            model1.addRow(new Object[]{
                    doctor.getName(),
                    doctor.getSurname(),
                    doctor.getDOB(),
                    doctor.getMobileNumber(),
                    doctor.getMedicalLicenceNumber(),
                    doctor.getSpecialisation()});
        });


        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Georgia", Font.BOLD, 15));
        resetButton.setBounds(192, 472, 102, 40);
        resetButton.setFocusable(false);
        resetButton.setBackground(new Color(149, 193, 232));
        add(resetButton);
        resetButton.addActionListener(e -> {
            myTableRowSorter = new TableRowSorter(model);
            table.setRowSorter(myTableRowSorter);
            List<RowSorter.SortKey> sortKeys = new ArrayList<>();

            int columnIndexToSort = 1;
            sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.UNSORTED));

            myTableRowSorter.setSortKeys(sortKeys);
            myTableRowSorter.sort();
        });

        setVisible(true);
        setResizable(false);
    }
}
