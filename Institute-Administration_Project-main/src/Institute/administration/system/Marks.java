package Institute.administration.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Marks extends JFrame implements ActionListener {
    String rollno;
    JButton cancel;

    Marks(String rollno) {
        this.rollno = rollno;

        setSize(500, 600);
        setLocation(500, 100);
        setLayout(null);

        getContentPane().setBackground(new Color(210, 252, 248));

        JLabel heading = new JLabel("Solapur University");
        heading.setBounds(100, 10, 500, 25);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);

        JLabel subheading = new JLabel("Result of Examination 2024");
        subheading.setBounds(100, 50, 500, 20);
        subheading.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(subheading);

        JLabel lblrollno = new JLabel("Roll Number " + rollno);
        lblrollno.setBounds(60, 100, 500, 20);
        lblrollno.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblrollno);

        JLabel lblsemester = new JLabel();
        lblsemester.setBounds(60, 130, 500, 20);
        lblsemester.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblsemester);

        JLabel sub1 = new JLabel();
        sub1.setBounds(100, 200, 500, 20);
        sub1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sub1);

        JLabel sub2 = new JLabel();
        sub2.setBounds(100, 230, 500, 20);
        sub2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sub2);

        JLabel sub3 = new JLabel();
        sub3.setBounds(100, 260, 500, 20);
        sub3.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sub3);

        JLabel sub4 = new JLabel();
        sub4.setBounds(100, 290, 500, 20);
        sub4.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sub4);

        JLabel sub5 = new JLabel();
        sub5.setBounds(100, 320, 500, 20);
        sub5.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sub5);

        // New JLabel for Total Percentage
        JLabel lblTotalPercentage = new JLabel();
        lblTotalPercentage.setBounds(100, 360, 500, 20);
        lblTotalPercentage.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblTotalPercentage);

        try {
            database b = new database();

            ResultSet rs1 = b.statement.executeQuery("select * from subject where rollno = '" + rollno + "'");
            String[] subjects = new String[5];
            int[] marks = new int[5];
            int totalMarksObtained = 0;
            int totalPossibleMarks = 500; // Assuming each subject is out of 100, total for 5 subjects = 500

            while (rs1.next()) {
                subjects[0] = rs1.getString("sub1");
                subjects[1] = rs1.getString("sub2");
                subjects[2] = rs1.getString("sub3");
                subjects[3] = rs1.getString("sub4");
                subjects[4] = rs1.getString("sub5");
            }

            ResultSet rs2 = b.statement.executeQuery("select * from marks where rollno = '" + rollno + "'");
            while (rs2.next()) {
                marks[0] = rs2.getInt("mark1");
                marks[1] = rs2.getInt("mark2");
                marks[2] = rs2.getInt("mark3");
                marks[3] = rs2.getInt("mark4");
                marks[4] = rs2.getInt("mark5");

                sub1.setText(subjects[0] + "------------" + marks[0]);
                sub2.setText(subjects[1] + "------------" + marks[1]);
                sub3.setText(subjects[2] + "------------" + marks[2]);
                sub4.setText(subjects[3] + "------------" + marks[3]);
                sub5.setText(subjects[4] + "------------" + marks[4]);
                lblsemester.setText("Semester " + rs2.getString("semester"));

                // Calculate total marks obtained
                for (int mark : marks) {
                    totalMarksObtained += mark;
                }
            }

            // Calculate percentage
            double percentage = ((double) totalMarksObtained / totalPossibleMarks) * 100;
            lblTotalPercentage.setText("Total Percentage: " + String.format("%.2f", percentage) + "%");

        } catch (Exception e) {
            e.printStackTrace();
        }

        cancel = new JButton("Back");
        cancel.setBounds(250, 500, 120, 25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
    }

    public static void main(String[] args) {
        new Marks("");
    }
}
