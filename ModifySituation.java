import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class ModifySituation extends JPanel implements ActionListener {
	Hashtable tuition_basic_information_table = null;
	JTextField student_ID, name, basic_tuition, Course, book_fees;
	JRadioButton Yes, No;
	ButtonGroup group = null;
	JButton start_to_modify, input_modification, reset;
	FileInputStream inOne = null;
	ObjectInputStream inTwo = null;
	FileOutputStream outOne = null;
	ObjectOutputStream outTwo = null;
	File file = null;

	public ModifySituation(File file) {
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
		start_to_modify = new JButton("Start Editing");
		input_modification = new JButton("Enter Modification");
		input_modification.setEnabled(false);
		reset = new JButton("Reset");
		student_ID.addActionListener(this);
		start_to_modify.addActionListener(this);
		input_modification.addActionListener(this);
		reset.addActionListener(this);
		Box box1 = Box.createHorizontalBox();
		box1.add(new JLabel("Enter the Student ID to Modify the Information:", JLabel.CENTER));
		box1.add(student_ID);
		box1.add(start_to_modify);
		Box box2 = Box.createHorizontalBox();
		box2.add(new JLabel("Name:", JLabel.CENTER));
		box2.add(name);
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
		// boxH.add(box3);
		boxH.add(box4);
		boxH.add(box5);
		boxH.add(box6);
		boxH.add(Box.createVerticalGlue());
		JPanel pCenter = new JPanel();
		pCenter.add(boxH);
		setLayout(new BorderLayout());
		add(pCenter, BorderLayout.CENTER);
		JPanel pSouth = new JPanel();
		pSouth.add(input_modification);
		pSouth.add(reset);
		add(pSouth, BorderLayout.SOUTH);
		validate();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == start_to_modify || e.getSource() == student_ID) {
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
					input_modification.setEnabled(true);
					Student stu = (Student) tuition_basic_information_table.get(number);
					name.setText(stu.getName());
					basic_tuition.setText(stu.getBasictuition());
					Course.setText(stu.getLodgingexpenses());
					book_fees.setText(stu.getBookexpenses());
					if (stu.getWhetherhandingexpenses().equals("Yes")) {
						Yes.setSelected(true);
					} else {
						No.setSelected(true);
					}
				} else {
					input_modification.setEnabled(false);

					String warning = "The Student Number does not Exist!";
					JOptionPane.showMessageDialog(this, warning, "Warning!", JOptionPane.WARNING_MESSAGE);
					student_ID.setText(null);
					name.setText(null);
					basic_tuition.setText(null);
					Course.setText(null);
					book_fees.setText(null);

				}
			} else {
				input_modification.setEnabled(false);

				String warning = "You must Enter your Student ID!";
				JOptionPane.showMessageDialog(this, warning, "Warning!", JOptionPane.WARNING_MESSAGE);
				student_ID.setText(null);
				name.setText(null);
				basic_tuition.setText(null);
				Course.setText(null);
				book_fees.setText(null);
			}
		} else if (e.getSource() == input_modification) {
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
					String question = "The Basic Information of the Student already exists, do you want to Modify his or her Basic Tuition Information!!";

					JOptionPane.showMessageDialog(this, question, "Warning!", JOptionPane.QUESTION_MESSAGE);

					String m = "Basic Tuition Information will be Revised!";
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
						Student student = new Student();
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
						input_modification.setEnabled(false);
					} else if (ok == JOptionPane.NO_OPTION) {
						input_modification.setEnabled(true);
					}
				} else {

					String warning = "The Student ID has no Basic Information and cannot be Modified!";
					JOptionPane.showMessageDialog(this, warning, "Warning!", JOptionPane.WARNING_MESSAGE);
					input_modification.setEnabled(false);
				}
			} else {
				String warning = "You must Enter your Student ID!";
				JOptionPane.showMessageDialog(this, warning, "Warning!", JOptionPane.WARNING_MESSAGE);
				input_modification.setEnabled(false);
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
