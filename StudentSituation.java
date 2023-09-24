import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class StudentSituation extends JPanel implements ActionListener {
  Hashtable tuition_basic_information_table = null;
  JTextField student_ID, name, basic_tuition, Course, book_fees;
  JRadioButton Yes, No;
  Student student = null;
  ButtonGroup group = null;
  JButton input, reset;
  FileInputStream inOne = null;
  ObjectInputStream inTwo = null;
  FileOutputStream outOne = null;
  ObjectOutputStream outTwo = null;
  File file = null;

  public StudentSituation(File file) {

    this.file = file;
    student_ID = new JTextField(10);
    name = new JTextField(10);
    basic_tuition = new JTextField(10);
    Course = new JTextField(10);
    book_fees = new JTextField(10);
    group = new ButtonGroup();
    Yes = new JRadioButton("Yes", true);
    No = new JRadioButton("No", false);
    group.add(Yes);
    group.add(No);
    input = new JButton("Input");
    reset = new JButton("Reset");
    input.addActionListener(this);
    reset.addActionListener(this);
    Box box1 = Box.createHorizontalBox();
    box1.add(new JLabel("Student ID:", JLabel.CENTER));
    box1.add(student_ID);
    Box box2 = Box.createHorizontalBox();
    box2.add(new JLabel("Name:", JLabel.CENTER));
    box2.add(name);
    Box box3 = Box.createHorizontalBox();
    box3.add(new JLabel("Whether Tuition Fee has been Paid:", JLabel.CENTER));
    box3.add(Yes);
    box3.add(No);
    Box box4 = Box.createHorizontalBox();
    box4.add(new JLabel("Basic Tuition Fee:", JLabel.CENTER));
    box4.add(basic_tuition);
    Box box5 = Box.createHorizontalBox();
    box5.add(new JLabel("Course:", JLabel.CENTER));
    box5.add(Course);
    Box box6 = Box.createHorizontalBox();
    box6.add(new JLabel("Book Fee:", JLabel.CENTER));
    box6.add(book_fees);
    Box boxH = Box.createVerticalBox();
    boxH.add(box1);
    boxH.add(box2);
    boxH.add(box3);
    boxH.add(box4);
    boxH.add(box5);
    boxH.add(box6);
    boxH.add(Box.createVerticalGlue());
    JPanel pCenter = new JPanel();
    pCenter.add(boxH);
    setLayout(new BorderLayout());
    add(pCenter, BorderLayout.CENTER);
    JPanel pSouth = new JPanel();
    pSouth.add(input);
    pSouth.add(reset);
    add(pSouth, BorderLayout.SOUTH);
    validate();
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == input) {
      String number = "";
      number = student_ID.getText();

      if (number.length() > 0) {
        try {
          inOne = new FileInputStream(file);
          inTwo = new ObjectInputStream(inOne);
          tuition_basic_information_table = (Hashtable) inTwo.readObject();
          inOne.close();
          inTwo.close();
        } catch (Exception ee) {
        }
        if (tuition_basic_information_table.containsKey(number)) {
          String warning = "The Basic Tuition Information of this Student already exists. If you want to Modify it, please go to the Modification Page to Modify it!";

          JOptionPane.showMessageDialog(this, warning, "Warning!", JOptionPane.WARNING_MESSAGE);
        } else {
          String m = "Basic Tuition Information will be Entered!";
          int ok = JOptionPane.showConfirmDialog(this, m, "Confirm", JOptionPane.YES_NO_OPTION,
              JOptionPane.INFORMATION_MESSAGE);
          if (ok == JOptionPane.YES_OPTION) {
            String name_1 = name.getText();
            String basic_tuition_1 = basic_tuition.getText();
            String lodgingexpenses = Course.getText();
            String bookexpenses = book_fees.getText();
            String whetherhandingexpenses = null;
            if (Yes.isSelected()) {
              whetherhandingexpenses = Yes.getText();
            } else {
              whetherhandingexpenses = No.getText();
            }
            student = new Student();
            student.setNumber(number);
            student.setName(name_1);
            student.setBasictuition(basic_tuition_1);
            student.setLodgingexpenses(lodgingexpenses);
            student.setBookexpenses(bookexpenses);
            student.setWhetherhandingexpenses(whetherhandingexpenses);
            try {
              outOne = new FileOutputStream(file);
              outTwo = new ObjectOutputStream(outOne);
              tuition_basic_information_table.put(number, student);
              outTwo.writeObject(tuition_basic_information_table);
              outTwo.close();
              outOne.close();
              student_ID.setText(null);
              name.setText(null);
              basic_tuition.setText(null);
              Course.setText(null);
              book_fees.setText(null);
            } catch (Exception ee) {
              System.out.println(ee);
            }

          }
        }
      } else {
        String warning = "You must Enter your Student ID!";
        JOptionPane.showMessageDialog(this, warning, "Warning!", JOptionPane.WARNING_MESSAGE);
      }
    }
    if (e.getSource() == reset) {
      student_ID.setText(null);
      name.setText(null);
      basic_tuition.setText(null);
      Course.setText(null);
      book_fees.setText(null);

    }
  }
}
