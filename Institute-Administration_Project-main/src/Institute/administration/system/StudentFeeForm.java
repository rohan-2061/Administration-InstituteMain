package Institute.administration.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.sql.ResultSet;

public class StudentFeeForm extends JFrame implements ActionListener {
    Choice CrollNumber;
    JComboBox<String> courseBox, departmentBox, semesterBox;
    JLabel totalAmount;
    JButton pay, update, back, print;
    JTextArea receiptArea; // To display the receipt within the UI

    StudentFeeForm() {
        getContentPane().setBackground(new Color(210, 252, 251));

        JLabel roolNumber = new JLabel("Select Roll Number");
        roolNumber.setBounds(40, 60, 150, 20);
        roolNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
        add(roolNumber);

        CrollNumber = new Choice();
        CrollNumber.setBounds(200, 60, 150, 20);
        add(CrollNumber);

        try {
            database b = new database();
            ResultSet rs = b.statement.executeQuery("select * from student");
            while (rs.next()) {
                CrollNumber.add(rs.getString("rollno"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel name = new JLabel("Name");
        name.setBounds(40, 100, 150, 20);
        add(name);

        JLabel textName = new JLabel();
        textName.setBounds(200, 100, 150, 20);
        add(textName);

        JLabel fname = new JLabel("Last Name");
        fname.setBounds(40, 140, 150, 20);
        add(fname);

        JLabel textfName = new JLabel();
        textfName.setBounds(200, 140, 150, 20);
        add(textfName);

        JLabel Qualification = new JLabel("Course");
        Qualification.setBounds(40, 180, 150, 20);
        add(Qualification);

        String[] course = {"BTech", "BBA", "BCA", "BSC", "MSC", "MBA", "MCA", "MCom", "MA", "BA"};
        courseBox = new JComboBox<>(course);
        courseBox.setBounds(200, 180, 150, 20);
        add(courseBox);

        JLabel Department = new JLabel("Branch");
        Department.setBounds(40, 220, 150, 20);
        add(Department);

        String[] department = {"Computer Science", "Electronics", "Mechanical", "Civil", "IT"};
        departmentBox = new JComboBox<>(department);
        departmentBox.setBounds(200, 220, 150, 20);
        add(departmentBox);

        JLabel textsemester = new JLabel("Semester");
        textsemester.setBounds(40, 260, 150, 20);
        add(textsemester);

        String[] semester = {"semester1", "semester2", "semester3", "semester4", "semester5", "semester6", "semester7", "semester8"};
        semesterBox = new JComboBox<>(semester);
        semesterBox.setBounds(200, 260, 150, 20);
        add(semesterBox);

        JLabel total = new JLabel("Total Payable");
        total.setBounds(40, 300, 150, 20);
        add(total);

        totalAmount = new JLabel("");
        totalAmount.setBounds(200, 300, 150, 20);
        add(totalAmount);

        update = new JButton("Update");
        update.setBounds(30, 380, 100, 25);
        update.addActionListener(this);
        add(update);

        pay = new JButton("Pay");
        pay.setBounds(150, 380, 100, 25);
        pay.addActionListener(this);
        add(pay);

        print = new JButton("Print Receipt");
        print.setBounds(270, 380, 150, 25);
        print.addActionListener(this);
        add(print);

        back = new JButton("Back");
        back.setBounds(450, 380, 100, 25);
        back.addActionListener(this);
        add(back);

        receiptArea = new JTextArea();
        receiptArea.setBounds(400, 50, 450, 300);
        receiptArea.setEditable(false);
        receiptArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(receiptArea);

        setSize(900, 500);
        setLocation(300, 100);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == update) {
            String course = (String) courseBox.getSelectedItem();
            String semester = (String) semesterBox.getSelectedItem();
            try {
                database b = new database();
                ResultSet rs = b.statement.executeQuery("select * from fee where course = '" + course + "'");
                while (rs.next()) {
                    totalAmount.setText(rs.getString(semester));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == pay) {
            String rollno = CrollNumber.getSelectedItem();
            String course = (String) courseBox.getSelectedItem();
            String semester = (String) semesterBox.getSelectedItem();
            String branch = (String) departmentBox.getSelectedItem();
            String total = totalAmount.getText();

            // Generate Receipt Content
            receiptArea.setText("---------- Fee Receipt ----------\n");
            receiptArea.append("Roll Number: " + rollno + "\n");
            receiptArea.append("Course: " + course + "\n");
            receiptArea.append("Branch: " + branch + "\n");
            receiptArea.append("Semester: " + semester + "\n");
            receiptArea.append("Total Payable: " + total + "\n");
            receiptArea.append("---------------------------------\n");
            JOptionPane.showMessageDialog(null, "Fee Submitted Successfully!");
        } else if (e.getSource() == print) {
            try {
                receiptArea.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new StudentFeeForm();
    }
}
