import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class Inquest extends JDialog implements ActionListener {
	Hashtable tuition_basic_information_table = null;
	JTextField student_ID, name, basic_tuition, Course, book_fees;
	JRadioButton Yes, No;
	JButton query;
	ButtonGroup group = null;
	FileInputStream inOne = null;
	ObjectInputStream inTwo = null;
	File file = null;

	public Inquest(JFrame f, File file) {
		super(f, "Query box", false);
		this.file = file;
		student_ID = new JTextField(10);
		query = new JButton("GET");
		student_ID.addActionListener(this);
		query.addActionListener(this);
		name = new JTextField(10);
		name.setEditable(false);
		basic_tuition = new JTextField(10);
		basic_tuition.setEditable(false);
		Course = new JTextField(10);
		Course.setEditable(false);
		book_fees = new JTextField(10);
		book_fees.setEditable(false);
		Yes = new JRadioButton("Yes", false);
		No = new JRadioButton("No", false);
		group = new ButtonGroup();
		group.add(Yes);
		group.add(No);
		Box box1 = Box.createHorizontalBox();
		box1.add(new JLabel("Enter the Student ID to be Queried:", JLabel.CENTER));
		box1.add(student_ID);
		box1.add(query);
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
		Container con = getContentPane();
		con.add(pCenter, BorderLayout.CENTER);
		con.validate();
		setVisible(false);
		setBounds(200, 100, 600, 270);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		name.setText(null);
		basic_tuition.setText(null);
		Course.setText(null);
		book_fees.setText(null);

		if (e.getSource() == query || e.getSource() == student_ID) {
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
					String warning = "The Student Number does not Exist!";
					JOptionPane.showMessageDialog(this, warning, "Warning!", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				String warning = "You must Enput your Student ID!";
				JOptionPane.showMessageDialog(this, warning, "Warning!", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
