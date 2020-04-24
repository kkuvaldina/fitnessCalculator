package fitnessCalculator;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

/**
 * 
 * @author Kseniia
 *
 */
@SuppressWarnings("serial")
public class FormPanel extends JPanel {
	private static JPanel metricUnitsPanel;
	private static JPanel usUnitsPanel;
	private static JButton usButton;
	private static JButton metricButton;
	private static JLayeredPane layeredPane2;
	static JTextField FirstNameField = Gui.getFirstNameField();
	static JTextField LastNameField = Gui.getLastNameField();
	static Person person = Gui.getPerson();
	static JPanel mainPanel = Gui.getMainPanel();
	private static String unit = "US";
	private static List<String> weightFile = new ArrayList<>();

	/**
	 * Create the panel.
	 */
	public FormPanel() {

	}

	public static JPanel createFormPanel() {
		JPanel formPanel = new JPanel();
		formPanel.setBackground(new Color(253, 242, 197));
		formPanel.setLayout(null);

		JLabel titleLabel = new JLabel("Please Fill Out Form");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Toledo", Font.PLAIN, 18));
		titleLabel.setForeground(new Color(98, 61, 69));
		titleLabel.setBounds(131, 11, 502, 45);
		formPanel.add(titleLabel);

		layeredPane2 = new JLayeredPane();
		layeredPane2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(128, 0, 0)));
		layeredPane2.setBounds(10, 102, 764, 349);
		formPanel.add(layeredPane2);
		layeredPane2.setLayout(new CardLayout(0, 0));

		JPanel usUnitsPanel = createUsUnitsPanel();
		layeredPane2.add(usUnitsPanel);

		JPanel metricUnitsPanel = createMetricUnitsPanel();
		layeredPane2.add(metricUnitsPanel);

		JButton usButton = newUsButton();
		formPanel.add(usButton);

		JButton metricButton = newMetricButton();
		formPanel.add(metricButton);

		return formPanel;
	}

	private static JButton newUsButton() {
		String fileName = FirstNameField.getText() + LastNameField.getText() + ".txt";
		boolean fileExists = new File(fileName).exists();

		usButton = new JButton("US Units");
		usButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gui.switchPanels(usUnitsPanel, layeredPane2);
				usButton.setBackground(new Color(222, 202, 152));
				usButton.setForeground(new Color(66, 183, 194));

				metricButton.setBackground(new Color(66, 183, 194));
				metricButton.setForeground(new Color(253, 242, 197));

				unit = "US";
			}
		});
		usButton.setBackground(new Color(222, 202, 152));
		usButton.setForeground(new Color(66, 183, 194));
		usButton.setFont(new Font("Toledo", Font.PLAIN, 16));
		usButton.setBounds(10, 67, 290, 37);

		if (fileExists) {
			try {
				if (Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(0)
						.equals("US")) {
					Gui.switchPanels(usUnitsPanel, layeredPane2);
					usButton.setBackground(new Color(222, 202, 152));
					usButton.setForeground(new Color(66, 183, 194));
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		return usButton;
	}

	private static JButton newMetricButton() {
		String fileName = FirstNameField.getText() + LastNameField.getText() + ".txt";
		boolean fileExists = new File(fileName).exists();

		metricButton = new JButton("Metric Units");
		metricButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gui.switchPanels(metricUnitsPanel, layeredPane2);

				usButton.setBackground(new Color(66, 183, 194));
				usButton.setForeground(new Color(253, 242, 197));

				metricButton.setBackground(new Color(222, 202, 152));
				metricButton.setForeground(new Color(66, 183, 194));

				unit = "METRIC";
			}
		});
		metricButton.setBounds(484, 67, 290, 37);
		metricButton.setBackground(new Color(66, 183, 194));
		metricButton.setForeground(new Color(253, 242, 197));
		metricButton.setFont(new Font("Toledo", Font.PLAIN, 16));

		if (fileExists) {
			try {
				if (Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(0)
						.equals("METRIC")) {
					Gui.switchPanels(metricUnitsPanel, layeredPane2);

					usButton.setBackground(new Color(66, 183, 194));
					usButton.setForeground(new Color(253, 242, 197));

					metricButton.setBackground(new Color(222, 202, 152));
					metricButton.setForeground(new Color(66, 183, 194));
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return metricButton;
	}

	// METRIC UNITS PANEL
	private static JPanel createMetricUnitsPanel() {
		metricUnitsPanel = new JPanel();
		metricUnitsPanel.setBackground(new Color(222, 202, 152));
		metricUnitsPanel.setLayout(null);

		String fileName = FirstNameField.getText() + LastNameField.getText() + ".txt";
		boolean fileExists = new File(fileName).exists();

		boolean weightFileExists = new File(FirstNameField.getText() + LastNameField.getText() + "Weight.txt").exists();

		JLabel ageLabel = new JLabel("Age");
		ageLabel.setFont(new Font("Toledo", Font.PLAIN, 13));
		ageLabel.setForeground(new Color(98, 61, 69));
		ageLabel.setBounds(81, 44, 92, 45);
		metricUnitsPanel.add(ageLabel);

		JTextField ageField = new JTextField();
		if (fileExists)
			ageField.setText(String.valueOf(person.getAge()));
		ageField.setForeground(new Color(98, 61, 69));
		ageField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		ageField.setBounds(129, 51, 193, 28);
		ageField.setMargin(new Insets(5, 5, 5, 5));
		metricUnitsPanel.add(ageField);

		JLabel genderLabel = new JLabel("Gender");
		genderLabel.setFont(new Font("Toledo", Font.PLAIN, 13));
		genderLabel.setForeground(new Color(98, 61, 69));
		genderLabel.setBounds(81, 100, 92, 45);
		metricUnitsPanel.add(genderLabel);

		JRadioButton maleButton = new JRadioButton("Male");
		maleButton.setMnemonic(KeyEvent.VK_B);
		maleButton.setActionCommand("Male");
		maleButton.setBounds(151, 107, 71, 31);
		maleButton.setBackground(new Color(222, 202, 152));
		maleButton.setSelected(true);
		metricUnitsPanel.add(maleButton);

		JRadioButton femaleButton = new JRadioButton("Female");
		femaleButton.setMnemonic(KeyEvent.VK_B);
		femaleButton.setActionCommand("Female");
		femaleButton.setBounds(235, 107, 71, 31);
		femaleButton.setBackground(new Color(222, 202, 152));
		metricUnitsPanel.add(femaleButton);

		ButtonGroup group = new ButtonGroup();
		group.add(maleButton);
		group.add(femaleButton);

		if (fileExists) {
			if (person.getGender() == 1) {
				maleButton.setSelected(true);
			} else {
				femaleButton.setSelected(true);
			}
		}

		JLabel heightLabel = new JLabel("Height");
		heightLabel.setFont(new Font("Toledo", Font.PLAIN, 13));
		heightLabel.setForeground(new Color(98, 61, 69));
		heightLabel.setBounds(81, 156, 70, 45);
		metricUnitsPanel.add(heightLabel);

		JTextField cmField = new JTextField();
		if (fileExists)
			cmField.setText(String.valueOf(person.getHeightCm()));
		cmField.setForeground(new Color(98, 61, 69));
		cmField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		cmField.setBounds(129, 163, 68, 28);
		cmField.setMargin(new Insets(5, 5, 5, 5));
		metricUnitsPanel.add(cmField);

		JLabel heightCmLabel = new JLabel("cm");
		heightCmLabel.setFont(new Font("Toledo", Font.PLAIN, 12));
		heightCmLabel.setForeground(new Color(98, 61, 69));
		heightCmLabel.setBounds(207, 164, 37, 28);
		metricUnitsPanel.add(heightCmLabel);

		JLabel weightLabel = new JLabel("Weight");
		weightLabel.setFont(new Font("Toledo", Font.PLAIN, 13));
		weightLabel.setForeground(new Color(98, 61, 69));
		weightLabel.setBounds(81, 212, 57, 45);
		metricUnitsPanel.add(weightLabel);

		JTextField weightField = new JTextField();
		if (fileExists)
			weightField.setText(String.valueOf(person.getWeightKg()));
		weightField.setForeground(new Color(98, 61, 69));
		weightField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		weightField.setBounds(129, 219, 68, 28);
		weightField.setMargin(new Insets(5, 5, 5, 5));
		metricUnitsPanel.add(weightField);

		JLabel kgLabel = new JLabel("kg");
		kgLabel.setFont(new Font("Toledo", Font.PLAIN, 12));
		kgLabel.setForeground(new Color(98, 61, 69));
		kgLabel.setBounds(207, 220, 57, 28);
		metricUnitsPanel.add(kgLabel);

		JLabel bustSizeLabel = new JLabel("Bust Size");
		bustSizeLabel.setFont(new Font("Toledo", Font.PLAIN, 13));
		bustSizeLabel.setForeground(new Color(98, 61, 69));
		bustSizeLabel.setBounds(456, 44, 57, 45);
		metricUnitsPanel.add(bustSizeLabel);

		JTextField bustSizeField = new JTextField();
		if (fileExists)
			bustSizeField.setText(String.valueOf(person.getBustSizeCm()));
		bustSizeField.setForeground(new Color(98, 61, 69));
		bustSizeField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		bustSizeField.setBounds(552, 51, 145, 28);
		bustSizeField.setMargin(new Insets(5, 5, 5, 5));
		metricUnitsPanel.add(bustSizeField);

		JLabel bustSizeCmLabel = new JLabel("cm");
		bustSizeCmLabel.setFont(new Font("Toledo", Font.PLAIN, 12));
		bustSizeCmLabel.setForeground(new Color(98, 61, 69));
		bustSizeCmLabel.setBounds(705, 51, 57, 31);
		metricUnitsPanel.add(bustSizeCmLabel);

		JLabel waistSizeLabel = new JLabel("Waist Size");
		waistSizeLabel.setFont(new Font("Toledo", Font.PLAIN, 13));
		waistSizeLabel.setForeground(new Color(98, 61, 69));
		waistSizeLabel.setBounds(456, 100, 82, 45);
		metricUnitsPanel.add(waistSizeLabel);

		JTextField waistSizeField = new JTextField();
		if (fileExists)
			waistSizeField.setText(String.valueOf(person.getWaistSizeCm()));
		waistSizeField.setForeground(new Color(98, 61, 69));
		waistSizeField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		waistSizeField.setBounds(552, 107, 145, 28);
		waistSizeField.setMargin(new Insets(5, 5, 5, 5));
		metricUnitsPanel.add(waistSizeField);

		JLabel waistSizeCmLabel = new JLabel("cm");
		waistSizeCmLabel.setFont(new Font("Toledo", Font.PLAIN, 12));
		waistSizeCmLabel.setForeground(new Color(98, 61, 69));
		waistSizeCmLabel.setBounds(705, 107, 57, 31);
		metricUnitsPanel.add(waistSizeCmLabel);

		JLabel highHipSizeLabel = new JLabel("High Hip Size");
		highHipSizeLabel.setFont(new Font("Toledo", Font.PLAIN, 13));
		highHipSizeLabel.setForeground(new Color(98, 61, 69));
		highHipSizeLabel.setBounds(456, 156, 90, 45);
		metricUnitsPanel.add(highHipSizeLabel);

		JTextField highHipSizeField = new JTextField();
		if (fileExists)
			highHipSizeField.setText(String.valueOf(person.getHipHeightCm()));
		highHipSizeField.setForeground(new Color(98, 61, 69));
		highHipSizeField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		highHipSizeField.setBounds(552, 163, 145, 28);
		highHipSizeField.setMargin(new Insets(5, 5, 5, 5));
		metricUnitsPanel.add(highHipSizeField);

		JLabel highHipSizeCmLabel = new JLabel("cm");
		highHipSizeCmLabel.setFont(new Font("Toledo", Font.PLAIN, 12));
		highHipSizeCmLabel.setForeground(new Color(98, 61, 69));
		highHipSizeCmLabel.setBounds(705, 163, 57, 31);
		metricUnitsPanel.add(highHipSizeCmLabel);

		JLabel hipSizeLabel = new JLabel("Hip Size");
		hipSizeLabel.setFont(new Font("Toledo", Font.PLAIN, 13));
		hipSizeLabel.setForeground(new Color(98, 61, 69));
		hipSizeLabel.setBounds(456, 212, 82, 45);
		metricUnitsPanel.add(hipSizeLabel);

		JTextField hipSizeField = new JTextField();
		if (fileExists)
			hipSizeField.setText(String.valueOf(person.getHipSizeCm()));
		hipSizeField.setForeground(new Color(98, 61, 69));
		hipSizeField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		hipSizeField.setBounds(552, 219, 145, 28);
		hipSizeField.setMargin(new Insets(5, 5, 5, 5));
		metricUnitsPanel.add(hipSizeField);

		JLabel hipSizeInchesLabel = new JLabel("cm");
		hipSizeInchesLabel.setFont(new Font("Toledo", Font.PLAIN, 12));
		hipSizeInchesLabel.setForeground(new Color(98, 61, 69));
		hipSizeInchesLabel.setBounds(705, 219, 57, 31);
		metricUnitsPanel.add(hipSizeInchesLabel);

		JLabel requireFieldsLabel = new JLabel("");
		requireFieldsLabel.setFont(new Font("Toledo", Font.PLAIN, 13));
		requireFieldsLabel.setForeground(new Color(98, 61, 69));
		requireFieldsLabel.setBounds(300, 10, 150, 45);
		metricUnitsPanel.add(requireFieldsLabel);

		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				weightFile = new ArrayList<>();

				try {
					if (weightFileExists && fileExists) {
						if (!Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt"))
								.get(0).equals(unit)) { // FIXME
							try (Scanner reader = new Scanner(
									new File(FirstNameField.getText() + LastNameField.getText() + "Weight.txt"))) {
								while (reader.hasNextLine()) {
									readWeight(reader.nextLine());
								}
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							}

							try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
									FirstNameField.getText() + LastNameField.getText() + "Weight.txt")))) {
								out.print("");
							} catch (IOException ex) {
								ex.printStackTrace();
							}

							weightFile.forEach(el -> {

								String[] arr = el.split(",");
								Double weight = Double.parseDouble(arr[0]) / 2.205;

								try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
										FirstNameField.getText() + LastNameField.getText() + "Weight.txt", true)))) {
									out.println(weight + "," + arr[1]);
								} catch (IOException ex) {
									ex.printStackTrace();
								}
							});
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd");
				LocalDate localDate = LocalDate.now();

				if (ageField.getText().isEmpty() || cmField.getText().isEmpty() || weightField.getText().isEmpty()
						|| bustSizeField.getText().isEmpty() || waistSizeField.getText().isEmpty()
						|| highHipSizeField.getText().isEmpty() || hipSizeField.getText().isEmpty()) {
					requireFieldsLabel.setText("All Fields Are Required!");
				} else {
					int gender;

					if (maleButton.isSelected()) {
						gender = 1;
					} else {
						gender = 0;
					}

					person = new Person(FirstNameField.getText(), LastNameField.getText(),
							Integer.parseInt(ageField.getText()), gender, Double.parseDouble(weightField.getText()),
							Double.parseDouble(cmField.getText()), Double.parseDouble(bustSizeField.getText()),
							Double.parseDouble(waistSizeField.getText()),
							Double.parseDouble(highHipSizeField.getText()), Double.parseDouble(hipSizeField.getText()));
					Gui.setPerson(person);

					String fileName = FirstNameField.getText() + LastNameField.getText() + ".txt";

					try (PrintWriter writer = new PrintWriter(fileName)) {
						writer.println(UnitType.METRIC);
						writer.println(FirstNameField.getText());
						writer.println(LastNameField.getText());
						writer.println(Integer.parseInt(ageField.getText()));
						writer.println(gender);
						writer.println(Double.parseDouble(weightField.getText()));
						writer.println(Double.parseDouble(cmField.getText()));
						writer.println(Double.parseDouble(bustSizeField.getText()));
						writer.println(Double.parseDouble(waistSizeField.getText()));
						writer.println(Double.parseDouble(highHipSizeField.getText()));
						writer.println(Double.parseDouble(hipSizeField.getText()));

					} catch (FileNotFoundException ex) {
						ex.printStackTrace();
					}

					try (PrintWriter out = new PrintWriter(new BufferedWriter(
							new FileWriter(FirstNameField.getText() + LastNameField.getText() + "Weight.txt", true)))) {
						out.println(Double.parseDouble(weightField.getText()) + "," + dtf.format(localDate).toString());
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					mainPanel = Gui.createMainPanel();
					Gui.getLayeredPane(1).add(mainPanel);

					Gui.switchPanels(mainPanel, Gui.getLayeredPane(1));
				}
			}

		});
		submitButton.setBounds(332, 293, 122, 45);
		submitButton.setBackground(new Color(66, 183, 194));
		submitButton.setForeground(new Color(253, 242, 197));
		submitButton.setFont(new Font("Toledo", Font.PLAIN, 16));
		metricUnitsPanel.add(submitButton);

		return metricUnitsPanel;
	}

	private static void readWeight(String line) {

		weightFile.add(line);
	}

	// US UNITS PANEL
	private static JPanel createUsUnitsPanel() {
		usUnitsPanel = new JPanel();
		usUnitsPanel.setBackground(new Color(222, 202, 152));
		usUnitsPanel.setLayout(null);

		String fileName = FirstNameField.getText() + LastNameField.getText() + ".txt";
		boolean fileExists = new File(fileName).exists();
		boolean weightFileExists = new File(FirstNameField.getText() + LastNameField.getText() + "Weight.txt").exists();

		JLabel ageLabel = new JLabel("Age");
		ageLabel.setFont(new Font("Toledo", Font.PLAIN, 13));
		ageLabel.setForeground(new Color(98, 61, 69));
		ageLabel.setBounds(81, 44, 92, 45);
		usUnitsPanel.add(ageLabel);

		JTextField ageField = new JTextField();
		if (fileExists)
			ageField.setText(String.valueOf(person.getAge()));
		ageField.setForeground(new Color(98, 61, 69));
		ageField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		ageField.setBounds(129, 51, 193, 28);
		ageField.setMargin(new Insets(5, 5, 5, 5));
		usUnitsPanel.add(ageField);

		JLabel genderLabel = new JLabel("Gender");
		genderLabel.setFont(new Font("Toledo", Font.PLAIN, 13));
		genderLabel.setForeground(new Color(98, 61, 69));
		genderLabel.setBounds(81, 100, 92, 45);
		usUnitsPanel.add(genderLabel);

		JRadioButton maleButton = new JRadioButton("Male");
		maleButton.setMnemonic(KeyEvent.VK_B);
		maleButton.setActionCommand("Male");
		maleButton.setBounds(151, 107, 71, 31);
		maleButton.setBackground(new Color(222, 202, 152));
		maleButton.setSelected(true);
		usUnitsPanel.add(maleButton);

		JRadioButton femaleButton = new JRadioButton("Female");
		femaleButton.setMnemonic(KeyEvent.VK_B);
		femaleButton.setActionCommand("Female");
		femaleButton.setBounds(235, 107, 71, 31);
		femaleButton.setBackground(new Color(222, 202, 152));
		usUnitsPanel.add(femaleButton);

		ButtonGroup group = new ButtonGroup();
		group.add(maleButton);
		group.add(femaleButton);

		if (fileExists) {
			if (person.getGender() == 1) {
				maleButton.setSelected(true);
			} else {
				femaleButton.setSelected(true);
			}
		}

		JLabel heightLabel = new JLabel("Height");
		heightLabel.setFont(new Font("Toledo", Font.PLAIN, 13));
		heightLabel.setForeground(new Color(98, 61, 69));
		heightLabel.setBounds(81, 156, 57, 45);
		usUnitsPanel.add(heightLabel);

		JTextField feetField = new JTextField();
		if (fileExists)
			feetField.setText(String.valueOf(person.getHeightFt()));
		feetField.setForeground(new Color(98, 61, 69));
		feetField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		feetField.setBounds(129, 163, 68, 28);
		feetField.setMargin(new Insets(5, 5, 5, 5));
		usUnitsPanel.add(feetField);

		JLabel heightFeetLabel = new JLabel("feet");
		heightFeetLabel.setFont(new Font("Toledo", Font.PLAIN, 12));
		heightFeetLabel.setForeground(new Color(98, 61, 69));
		heightFeetLabel.setBounds(207, 164, 37, 28);
		usUnitsPanel.add(heightFeetLabel);

		JTextField inchesField = new JTextField();
		if (fileExists)
			inchesField.setText(String.valueOf(person.getHeightInch()));
		inchesField.setForeground(new Color(98, 61, 69));
		inchesField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		inchesField.setBounds(254, 163, 68, 28);
		inchesField.setMargin(new Insets(5, 5, 5, 5));
		usUnitsPanel.add(inchesField);

		JLabel heightInchesLabel = new JLabel("inches");
		heightInchesLabel.setFont(new Font("Toledo", Font.PLAIN, 12));
		heightInchesLabel.setForeground(new Color(98, 61, 69));
		heightInchesLabel.setBounds(332, 164, 52, 28);
		usUnitsPanel.add(heightInchesLabel);

		JLabel weightLabel = new JLabel("Weight");
		weightLabel.setFont(new Font("Toledo", Font.PLAIN, 13));
		weightLabel.setForeground(new Color(98, 61, 69));
		weightLabel.setBounds(81, 212, 57, 45);
		usUnitsPanel.add(weightLabel);

		JTextField weightField = new JTextField();
		if (fileExists)
			weightField.setText(String.valueOf(person.getWeightLbs()));
		weightField.setForeground(new Color(98, 61, 69));
		weightField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		weightField.setBounds(129, 219, 68, 28);
		weightField.setMargin(new Insets(5, 5, 5, 5));
		usUnitsPanel.add(weightField);

		JLabel poundsLabel = new JLabel("pounds");
		poundsLabel.setFont(new Font("Toledo", Font.PLAIN, 12));
		poundsLabel.setForeground(new Color(98, 61, 69));
		poundsLabel.setBounds(207, 220, 57, 28);
		usUnitsPanel.add(poundsLabel);

		JLabel bustSizeLabel = new JLabel("Bust Size");
		bustSizeLabel.setFont(new Font("Toledo", Font.PLAIN, 13));
		bustSizeLabel.setForeground(new Color(98, 61, 69));
		bustSizeLabel.setBounds(456, 44, 57, 45);
		usUnitsPanel.add(bustSizeLabel);

		JTextField bustSizeField = new JTextField();
		if (fileExists)
			bustSizeField.setText(String.valueOf(person.getBustSizeInch()));
		bustSizeField.setForeground(new Color(98, 61, 69));
		bustSizeField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		bustSizeField.setBounds(552, 51, 145, 28);
		bustSizeField.setMargin(new Insets(5, 5, 5, 5));
		usUnitsPanel.add(bustSizeField);

		JLabel bustSizeInchesLabel = new JLabel("inches");
		bustSizeInchesLabel.setFont(new Font("Toledo", Font.PLAIN, 12));
		bustSizeInchesLabel.setForeground(new Color(98, 61, 69));
		bustSizeInchesLabel.setBounds(705, 51, 57, 31);
		usUnitsPanel.add(bustSizeInchesLabel);

		JLabel waistSizeLabel = new JLabel("Waist Size");
		waistSizeLabel.setFont(new Font("Toledo", Font.PLAIN, 13));
		waistSizeLabel.setForeground(new Color(98, 61, 69));
		waistSizeLabel.setBounds(456, 100, 82, 45);
		usUnitsPanel.add(waistSizeLabel);

		JTextField waistSizeField = new JTextField();
		if (fileExists)
			waistSizeField.setText(String.valueOf(person.getWaistSizeInch()));
		waistSizeField.setForeground(new Color(98, 61, 69));
		waistSizeField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		waistSizeField.setBounds(552, 107, 145, 28);
		waistSizeField.setMargin(new Insets(5, 5, 5, 5));
		usUnitsPanel.add(waistSizeField);

		JLabel waistSizeInchesLabel = new JLabel("inches");
		waistSizeInchesLabel.setFont(new Font("Toledo", Font.PLAIN, 12));
		waistSizeInchesLabel.setForeground(new Color(98, 61, 69));
		waistSizeInchesLabel.setBounds(705, 107, 57, 31);
		usUnitsPanel.add(waistSizeInchesLabel);

		JLabel highHipSizeLabel = new JLabel("High Hip Size");
		highHipSizeLabel.setFont(new Font("Toledo", Font.PLAIN, 13));
		highHipSizeLabel.setForeground(new Color(98, 61, 69));
		highHipSizeLabel.setBounds(456, 156, 82, 45);
		usUnitsPanel.add(highHipSizeLabel);

		JTextField highHipSizeField = new JTextField();
		if (fileExists)
			highHipSizeField.setText(String.valueOf(person.getHipHeightInch()));
		highHipSizeField.setForeground(new Color(98, 61, 69));
		highHipSizeField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		highHipSizeField.setBounds(552, 163, 145, 28);
		highHipSizeField.setMargin(new Insets(5, 5, 5, 5));
		usUnitsPanel.add(highHipSizeField);

		JLabel highHipSizeInchesLabel = new JLabel("inches");
		highHipSizeInchesLabel.setFont(new Font("Toledo", Font.PLAIN, 12));
		highHipSizeInchesLabel.setForeground(new Color(98, 61, 69));
		highHipSizeInchesLabel.setBounds(705, 163, 57, 31);
		usUnitsPanel.add(highHipSizeInchesLabel);

		JLabel hipSizeLabel = new JLabel("Hip Size");
		hipSizeLabel.setFont(new Font("Toledo", Font.PLAIN, 13));
		hipSizeLabel.setForeground(new Color(98, 61, 69));
		hipSizeLabel.setBounds(456, 212, 82, 45);
		usUnitsPanel.add(hipSizeLabel);

		JTextField hipSizeField = new JTextField();
		if (fileExists)
			hipSizeField.setText(String.valueOf(person.getHipSizeInch()));
		hipSizeField.setForeground(new Color(98, 61, 69));
		hipSizeField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		hipSizeField.setBounds(552, 219, 145, 28);
		hipSizeField.setMargin(new Insets(5, 5, 5, 5));
		usUnitsPanel.add(hipSizeField);

		JLabel hipSizeInchesLabel = new JLabel("inches");
		hipSizeInchesLabel.setFont(new Font("Toledo", Font.PLAIN, 12));
		hipSizeInchesLabel.setForeground(new Color(98, 61, 69));
		hipSizeInchesLabel.setBounds(705, 219, 57, 31);
		usUnitsPanel.add(hipSizeInchesLabel);

		JLabel requireFieldsLabel = new JLabel("");
		requireFieldsLabel.setFont(new Font("Toledo", Font.PLAIN, 13));
		requireFieldsLabel.setForeground(new Color(98, 61, 69));
		requireFieldsLabel.setBounds(300, 10, 150, 45);
		usUnitsPanel.add(requireFieldsLabel);

		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				weightFile = new ArrayList<>();

				try {
					if (weightFileExists && fileExists) {
						if (!Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt"))
								.get(0).equals(unit)) {
							try (Scanner reader = new Scanner(
									new File(FirstNameField.getText() + LastNameField.getText() + "Weight.txt"))) {
								while (reader.hasNextLine()) {
									readWeight(reader.nextLine());
								}
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							}

							try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
									FirstNameField.getText() + LastNameField.getText() + "Weight.txt")))) {
								out.print("");
							} catch (IOException ex) {
								ex.printStackTrace();
							}

							weightFile.forEach(el -> {

								String[] arr = el.split(",");
								Double weight = Double.parseDouble(arr[0]) * 2.205;

								try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
										FirstNameField.getText() + LastNameField.getText() + "Weight.txt", true)))) {
									out.println(weight + "," + arr[1]);
								} catch (IOException ex) {
									ex.printStackTrace();
								}
							});
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd");
				LocalDate localDate = LocalDate.now();

				if (ageField.getText().isEmpty() || feetField.getText().isEmpty() || inchesField.getText().isEmpty()
						|| weightField.getText().isEmpty() || bustSizeField.getText().isEmpty()
						|| waistSizeField.getText().isEmpty() || highHipSizeField.getText().isEmpty()
						|| hipSizeField.getText().isEmpty()) {
					requireFieldsLabel.setText("All Fields Are Required!");
				} else {

					int gender;

					if (maleButton.isSelected()) {
						gender = 1;
					} else {
						gender = 0;
					}

					person = new Person(FirstNameField.getText(), LastNameField.getText(),
							Integer.parseInt(ageField.getText()), gender, Integer.parseInt(weightField.getText()),
							Integer.parseInt(feetField.getText()), Integer.parseInt(inchesField.getText()),
							Integer.parseInt(bustSizeField.getText()), Integer.parseInt(waistSizeField.getText()),
							Integer.parseInt(highHipSizeField.getText()), Integer.parseInt(hipSizeField.getText()));

					Gui.setPerson(person);

					String fileName = FirstNameField.getText() + LastNameField.getText() + ".txt";

					try (PrintWriter writer = new PrintWriter(fileName)) {
						writer.println(UnitType.US);
						writer.println(FirstNameField.getText());
						writer.println(LastNameField.getText());
						writer.println(Integer.parseInt(ageField.getText()));
						writer.println(gender);
						writer.println(Integer.parseInt(weightField.getText()));
						writer.println(Integer.parseInt(feetField.getText()));
						writer.println(Integer.parseInt(inchesField.getText()));
						writer.println(Integer.parseInt(bustSizeField.getText()));
						writer.println(Integer.parseInt(waistSizeField.getText()));
						writer.println(Integer.parseInt(highHipSizeField.getText()));
						writer.println(Integer.parseInt(hipSizeField.getText()));

					} catch (FileNotFoundException ex) {
						ex.printStackTrace();
					}

					try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
							FirstNameField.getText() + LastNameField.getText() + "Weight.txt", true)));) {
						out.println(Double.parseDouble(weightField.getText()) + "," + dtf.format(localDate).toString());
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					mainPanel = Gui.createMainPanel();
					Gui.getLayeredPane(1).add(mainPanel);

					Gui.switchPanels(mainPanel, Gui.getLayeredPane(1));
				}
			}
		});
		submitButton.setBounds(332, 293, 122, 45);
		submitButton.setBackground(new Color(66, 183, 194));
		submitButton.setForeground(new Color(253, 242, 197));
		submitButton.setFont(new Font("Toledo", Font.PLAIN, 16));
		usUnitsPanel.add(submitButton);

		return usUnitsPanel;
	}
}
