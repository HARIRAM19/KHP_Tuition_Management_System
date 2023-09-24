import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Hashtable;

public class StudentManager extends JFrame implements ActionListener {
	StudentSituation tuition_basic_information_input = null;
	ModifySituation tuition_basic_information_modification = null;
	Inquest basic_tuition_information_inquiry = null;
	Delete basic_tuition_information_delete = null;
	JMenuBar bar;
	JMenu fileMenu;
	JMenuItem input, modify, query, delete;
	Container con = null;
	Hashtable basic_tuition_information = null;
	File file = null;
	CardLayout card = null;
	JLabel label = null;
	JPanel pCenter;

	public StudentManager() {
		input = new JMenuItem("Student Information");
		modify = new JMenuItem("Modify Information");
		query = new JMenuItem("Get Information");
		delete = new JMenuItem("Delete Information");
		bar = new JMenuBar();
		fileMenu = new JMenu("MENU");
		fileMenu.add(input);
		fileMenu.add(modify);
		fileMenu.add(query);
		fileMenu.add(delete);
		bar.add(fileMenu);
		setJMenuBar(bar);
		label = new JLabel("Welcome to KHP Tution Portal!", JLabel.CENTER);
		label.setFont(new Font("TimesRoman", Font.BOLD, 24));
		label.setForeground(Color.red);
		basic_tuition_information = new Hashtable();
		input.addActionListener(this);
		modify.addActionListener(this);
		query.addActionListener(this);
		delete.addActionListener(this);
		card = new CardLayout();
		con = getContentPane();
		pCenter = new JPanel();
		pCenter.setLayout(card);
		file = new File("Basic_tuition_information.txt");
		if (!file.exists()) {
			try {
				FileOutputStream out = new FileOutputStream(file);
				ObjectOutputStream objectOut = new ObjectOutputStream(out);
				objectOut.writeObject(basic_tuition_information);
				objectOut.close();
				out.close();
			} catch (IOException e) {
			}
		}
		tuition_basic_information_input = new StudentSituation(file);
		tuition_basic_information_modification = new ModifySituation(file);
		basic_tuition_information_inquiry = new Inquest(this, file);
		basic_tuition_information_delete = new Delete(file);
		pCenter.add("Initial Layer", label);
		pCenter.add("Tuition Input Layer", tuition_basic_information_input);
		pCenter.add("Tuition Modification Layer", tuition_basic_information_modification);
		pCenter.add("Tuition Deletion Layer", basic_tuition_information_delete);
		con.add(pCenter, BorderLayout.CENTER);
		con.validate();
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		// setVisible(true);
		setBounds(50, 50, 900, 600);
		validate();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == input) {
			card.show(pCenter, "Tuition Input Layer");
		} else if (e.getSource() == modify) {
			card.show(pCenter, "Tuition Modification Layer");
		} else if (e.getSource() == query) {
			basic_tuition_information_inquiry.setVisible(true);
		} else if (e.getSource() == delete) {
			card.show(pCenter, "Tuition Deletion Layer");
		}
	}
}
