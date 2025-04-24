import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

public class StudentRegistrationForm extends JFrame {
    JTextField nameField, mobileField;
    JTextArea addressArea;
    JRadioButton maleBtn, femaleBtn;
    JComboBox<String> dayBox, monthBox, yearBox;
    JCheckBox termsBox;
    JButton submitBtn, resetBtn;
    JLabel resultLabel;

    public StudentRegistrationForm() {
        setTitle("Registration Form");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel heading = new JLabel("Registration Form");
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        heading.setBounds(150, 10, 300, 30);
        add(heading);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(50, 60, 100, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 60, 150, 25);
        add(nameField);

        JLabel mobileLabel = new JLabel("Mobile");
        mobileLabel.setBounds(50, 100, 100, 25);
        add(mobileLabel);

        mobileField = new JTextField();
        mobileField.setBounds(150, 100, 150, 25);
        add(mobileField);

        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setBounds(50, 140, 100, 25);
        add(genderLabel);

        maleBtn = new JRadioButton("Male");
        femaleBtn = new JRadioButton("Female");
        maleBtn.setBounds(150, 140, 70, 25);
        femaleBtn.setBounds(220, 140, 80, 25);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleBtn);
        genderGroup.add(femaleBtn);
        add(maleBtn);
        add(femaleBtn);

        JLabel dobLabel = new JLabel("DOB");
        dobLabel.setBounds(50, 180, 100, 25);
        add(dobLabel);

        String[] days = new String[31];
        for (int i = 1; i <= 31; i++) days[i - 1] = String.valueOf(i);
        dayBox = new JComboBox<>(days);
        dayBox.setBounds(150, 180, 50, 25);
        add(dayBox);

        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                           "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        monthBox = new JComboBox<>(months);
        monthBox.setBounds(210, 180, 60, 25);
        add(monthBox);

        String[] years = new String[51];
        for (int i = 1970; i <= 2020; i++) years[i - 1970] = String.valueOf(i);
        yearBox = new JComboBox<>(years);
        yearBox.setBounds(280, 180, 70, 25);
        add(yearBox);

        JLabel addressLabel = new JLabel("Address");
        addressLabel.setBounds(50, 220, 100, 25);
        add(addressLabel);

        addressArea = new JTextArea();
        addressArea.setBounds(150, 220, 200, 60);
        add(addressArea);

        termsBox = new JCheckBox("Accept Terms And Conditions.");
        termsBox.setBounds(50, 300, 250, 25);
        add(termsBox);

        submitBtn = new JButton("Submit");
        submitBtn.setBounds(80, 340, 100, 30);
        resetBtn = new JButton("Reset");
        resetBtn.setBounds(200, 340, 100, 30);
        add(submitBtn);
        add(resetBtn);

        resultLabel = new JLabel();
        resultLabel.setBounds(50, 380, 400, 60);
        resultLabel.setVerticalAlignment(JLabel.TOP);
        add(resultLabel);

        submitBtn.addActionListener(e -> handleSubmit());
        resetBtn.addActionListener(e -> resetForm());

        setVisible(true);
    }

    void handleSubmit() {
        String name = nameField.getText().trim();
        String mobile = mobileField.getText().trim();
        String gender = maleBtn.isSelected() ? "Male" : (femaleBtn.isSelected() ? "Female" : "");
        String day = (String) dayBox.getSelectedItem();
        String month = (String) monthBox.getSelectedItem();
        int monthIndex = monthBox.getSelectedIndex() + 1;
        String year = (String) yearBox.getSelectedItem();
        String address = addressArea.getText().trim();

        if (name.isEmpty() || mobile.isEmpty() || gender.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }

        if (!mobile.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, "Mobile number must be 10 digits.");
            return;
        }

        int dayInt = Integer.parseInt(day);
        int yearInt = Integer.parseInt(year);
        LocalDate dob = LocalDate.of(yearInt, monthIndex, dayInt);
        if (dob.isAfter(LocalDate.of(2010, 1, 1))) {
            JOptionPane.showMessageDialog(this, "DOB must be before 1st Jan 2010.");
            return;
        }

        if (!termsBox.isSelected()) {
            JOptionPane.showMessageDialog(this, "Please accept the terms and conditions.");
            return;
        }

        // Display Result
        resultLabel.setText("<html>Registration Successful!<br/>"
                + "Name: " + name + "<br/>"
                + "Mobile: " + mobile + "<br/>"
                + "Gender: " + gender + "<br/>"
                + "DOB: " + day + " " + month + " " + year + "<br/>"
                + "Address: " + address + "</html>");
    }

    void resetForm() {
        nameField.setText("");
        mobileField.setText("");
        gender.clearSelection();
        dayBox.setSelectedIndex(0);
        monthBox.setSelectedIndex(0);
        yearBox.setSelectedIndex(0);
        addressArea.setText("");
        termsBox.setSelected(false);
        resultLabel.setText("");
    }

    ButtonGroup gender = new ButtonGroup();

    public static void main(String[] args) {
        new StudentRegistrationForm();
    }
}

