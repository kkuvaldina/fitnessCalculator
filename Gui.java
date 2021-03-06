package fitnessCalculator;

import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.CategorySeries;
import org.knowm.xchart.XChartPanel;

/**
 * Gui class displays the fitness program to calculate your BMI, BMR, Body Type, Healthy Weight.
 * It creates file and writes your profile data into that file, so user doesn't need to reenter data every time he/she launches program.
 * 
 * @author Kelsie and Kseniia
 *
 */
@SuppressWarnings("serial")
public class Gui extends JFrame {
	private JPanel contentPane;
	private static JLayeredPane layeredPane;
	private static JPanel formPanel;
	private static JPanel mainPanel;
	private JPanel welcomePanel;
	private static JTextField FirstNameField;
	private static JTextField LastNameField;
	private static BMI bmi;
	private static BMR bmr;
	private static HealthyWeight healthyWeight;
	private static BodyType bodyType;
	static Person person;
	static JPanel lineChartWeightPanel;
	private static List<String> dates = new ArrayList<>();
	private static List<Number> weights = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(253, 242, 197));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 784, 462);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));

		JPanel welcomePanel = createWelcomePanel();
		layeredPane.add(welcomePanel);
	}

	/**
	 * Returns First name from JTextField
	 * @return First name
	 */
	public static JTextField getFirstNameField() {
		return FirstNameField;
	}

	/**
	 * Returns Last name from JTextField.
	 * @return Last name
	 */
	public static JTextField getLastNameField() {
		return LastNameField;
	}

	/**
	 * Returns person.
	 * @return the person
	 */
	public static Person getPerson() {
		return person;
	}

	/**
	 * Sets person to a new one.
	 * @param person2 person to be created
	 */
	public static void setPerson(Person person2) {
		person = person2;

	}

	/**
	 * Returns Main panel.
	 * @return main panel
	 */
	public static JPanel getMainPanel() {
		return mainPanel;
	}

	/**
	 * Returns LayeredPane in order to switch panels.
	 * @param x
	 * @return	LayeredPane
	 */
	public static JLayeredPane getLayeredPane(int x) {
		return layeredPane;
	}

	/**
	 * Switches panels.
	 * @param panel	Panel to be switched to
	 * @param layeredPane	
	 */
	public static void switchPanels(JPanel panel, JLayeredPane layeredPane) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

	/**
	 * Switches panels.
	 * @param panel	Panel to be switched to
	 * @param layeredPane	
	 */
	public static void switchPanels(FormPanel panel, JLayeredPane layeredPane) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

	/**
	 * Applies style for the buttons.
	 * @param btn	button to be styled
	 */
	private static void applyButtonDesign(JButton btn) {
		btn.setBackground(new Color(66, 183, 194));
		btn.setForeground(new Color(253, 242, 197));
		btn.setFont(new Font("Toledo", Font.PLAIN, 16));
	}

	//***WELCOME PANEL***
	/**
	 * Returns Welcome (first) panel.
	 * @return	welcome panel
	 */
	private JPanel createWelcomePanel() {
		welcomePanel = new JPanel();
		welcomePanel.setBackground(new Color(253, 242, 197));
		welcomePanel.setLayout(null);

		JLabel titleLabel = new JLabel("Welcome to Fitness Calculator! Please Enter your Name");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Toledo", Font.PLAIN, 18));
		titleLabel.setForeground(new Color(98, 61, 69));
		titleLabel.setBounds(119, 11, 502, 45);
		welcomePanel.add(titleLabel);

		JButton btnSubmitButton = newSubmitButton();
		welcomePanel.add(btnSubmitButton);

		JLabel lblFirstNameLabel = new JLabel("First Name:");
		lblFirstNameLabel.setFont(new Font("Toledo", Font.PLAIN, 17));
		lblFirstNameLabel.setForeground(new Color(66, 183, 194));
		lblFirstNameLabel.setBounds(229, 129, 87, 24);
		lblFirstNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		welcomePanel.add(lblFirstNameLabel);

		FirstNameField = new JTextField();
		FirstNameField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent event) {
				String firstName = FirstNameField.getText();
				String lastName = LastNameField.getText();

				if (!lastName.isEmpty() && !firstName.isEmpty())
					btnSubmitButton.setEnabled(true);
				else
					btnSubmitButton.setEnabled(false);
			}
		});
		FirstNameField.setForeground(new Color(98, 61, 69));
		FirstNameField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		FirstNameField.setBounds(333, 127, 193, 28);
		FirstNameField.setMargin(new Insets(5, 5, 5, 5));
		welcomePanel.add(FirstNameField);
		FirstNameField.setColumns(10);

		JLabel lblLastNameLabel = new JLabel("Last Name:");
		lblLastNameLabel.setFont(new Font("Toledo", Font.PLAIN, 17));
		lblLastNameLabel.setForeground(new Color(66, 183, 194));
		lblLastNameLabel.setBounds(229, 205, 86, 24);
		lblLastNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		welcomePanel.add(lblLastNameLabel);

		LastNameField = new JTextField();
		LastNameField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent event) {
				String firstName = FirstNameField.getText();
				String lastName = LastNameField.getText();

				if (!lastName.isEmpty() && !firstName.isEmpty())
					btnSubmitButton.setEnabled(true);
				else
					btnSubmitButton.setEnabled(false);
			}
		});
		LastNameField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		LastNameField.setForeground(new Color(98, 61, 69));
		LastNameField.setBounds(333, 203, 193, 28);
		LastNameField.setMargin(new Insets(5, 5, 5, 5));
		welcomePanel.add(LastNameField);
		LastNameField.setColumns(10);

		return welcomePanel;
	}

	//***MAIN PANEL***
	/**
	 * Returns Main panel with Progress chart, Profile and buttons.
	 * @return	main panel
	 */
	public static JPanel createMainPanel() {
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(253, 242, 197));
		mainPanel.setLayout(null);

		JLabel titleLabel = new JLabel();
		try {
			titleLabel.setText("Hello, " + Files
					.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(1));
		} catch (IOException e) {
			e.printStackTrace();
		}
		titleLabel.setBounds(121, 11, 502, 45);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Toledo", Font.PLAIN, 18));
		titleLabel.setForeground(new Color(98, 61, 69));
		mainPanel.add(titleLabel);

		JPanel profilePanel = createProfilePanel();
		mainPanel.add(profilePanel);

		JButton bmiButton = newBmiButton();
		mainPanel.add(bmiButton);

		JButton bmrButton = newBmrButton();
		mainPanel.add(bmrButton);

		JButton bodyTypeButton = newBodyTypeButton();
		mainPanel.add(bodyTypeButton);

		JButton healthyWeightButton = newHealthyWeightButton();
		mainPanel.add(healthyWeightButton);

		lineChartWeightPanel = newChartWeightPanel();
		mainPanel.add(lineChartWeightPanel);

		return mainPanel;
	}

	/**
	 * Returns Progress Chart with weights entered.
	 * @return	Progress Chart
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static JPanel newChartWeightPanel() {
		dates = new ArrayList<>();
		weights = new ArrayList<>();

		try (Scanner reader = new Scanner(
				new File(FirstNameField.getText() + LastNameField.getText() + "Weight.txt"))) {
			while (reader.hasNextLine()) {
				readWeight(reader.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		CategoryChart chart = new CategoryChartBuilder().width(500).height(480).title("Your Progress")
				.xAxisTitle("Date").yAxisTitle("Weight").build();
		
		//generic collection
		List<String> x = new ArrayList<>();
		List<Number> y = new ArrayList<>();

		//Checks number of all weights, displays last 7
		if (weights.size() > 7) {
			for (int i = dates.size() - 7; i <= dates.size() - 1; i++) {
				x.add(dates.get(i));
			}
			for (int j = weights.size() - 7; j <= weights.size() - 1; j++) {
				y.add(weights.get(j));
			}
		} else {
			x = new ArrayList<>(dates);
			y = new ArrayList<>(weights);
		}

		chart.getStyler().setPlotGridLinesVisible(false);
		chart.getStyler().setAvailableSpaceFill(.4);
		chart.getStyler().setLegendVisible(false);
		chart.getStyler().setHasAnnotations(true);

		CategorySeries series = chart.addSeries("Weight", x, y);
		series.setFillColor(new Color(66, 183, 194));

		JPanel lineChartWeightPanel = new XChartPanel(chart);
		lineChartWeightPanel.setBounds(381, 71, 396, 200);

		return lineChartWeightPanel;
	}

	/**
	 * Reads line, splits it and adds data to two lists.
	 * @param line	line to be read
	 */
	private static void readWeight(String line) {
		String[] arr = line.split(",");

		BigDecimal bd = new BigDecimal(arr[0]).setScale(1, RoundingMode.HALF_UP);
		double weight = bd.doubleValue();
		
		//generic collection
		Collections.addAll(dates, arr[1]); 
		Collections.addAll(weights, weight);
	}

	//***4 BUTTONS***
	/**
	 * Returns button for Healthy weight calculator. 
	 * Opens Calculator on click.
	 * @return	button
	 */
	private static JButton newHealthyWeightButton() {
		JButton healthyWeightButton = new JButton("Healthy Weight");
		healthyWeightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				healthyWeight = new HealthyWeight();
				healthyWeight.setVisible(true);
			}
		});
		applyButtonDesign(healthyWeightButton);
		healthyWeightButton.setBounds(584, 365, 193, 83);
		return healthyWeightButton;
	}

	/**
	 * Returns button for Body type calculator.
	 * Opens Calculator on click.
	 * @return	button
	 */
	private static JButton newBodyTypeButton() {
		JButton bodyTypeButton = new JButton("Body Type");
		bodyTypeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bodyType = new BodyType();
				bodyType.setVisible(true);
			}
		});
		applyButtonDesign(bodyTypeButton);
		bodyTypeButton.setBounds(381, 365, 193, 83);
		return bodyTypeButton;
	}

	/**
	 * Returns button for BMR calculator.
	 * Opens Calculator on click.
	 * @return	button
	 */
	private static JButton newBmrButton() {
		JButton bmrButton = new JButton("BMR");
		bmrButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bmr = new BMR();
				bmr.setVisible(true);
			}
		});
		applyButtonDesign(bmrButton);
		bmrButton.setBounds(584, 271, 193, 83);
		return bmrButton;
	}

	/**
	 * Returns button for BMI calculator.
	 * Opens Calculator on click.
	 * @return	button
	 */
	private static JButton newBmiButton() {
		JButton bmiButton = new JButton("BMI");
		bmiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bmi = new BMI();
				bmi.setVisible(true);
			}
		});
		applyButtonDesign(bmiButton);
		bmiButton.setBounds(381, 271, 193, 83);
		return bmiButton;
	}

	//***PROFILE PANEL***
	/**
	 * Returns panel with profile information taken from the file.
	 * @return profile panel on the main panel
	 */
	private static JPanel createProfilePanel() {
		JPanel profilePanel = new JPanel();
		profilePanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(98, 61, 69)));
		profilePanel.setBackground(new Color(222, 202, 152));
		profilePanel.setBounds(10, 71, 361, 380);
		profilePanel.setLayout(null);

		JLabel profileTitle = new JLabel("Your Profile");
		profileTitle.setHorizontalAlignment(SwingConstants.CENTER);
		profileTitle.setFont(new Font("Toledo", Font.PLAIN, 15));
		profileTitle.setForeground(new Color(98, 61, 69));
		profileTitle.setBounds(115, 22, 131, 33);
		profilePanel.add(profileTitle);

		JLabel fullNameLabel = new JLabel();
		try {
			fullNameLabel.setText(
					Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(1)
							+ " "
							+ Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt"))
									.get(2));
		} catch (IOException e) {
			e.printStackTrace();
		}
		fullNameLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		fullNameLabel.setBounds(48, 84, 115, 25);
		profilePanel.add(fullNameLabel);

		JLabel ageLabel = new JLabel();
		try {
			ageLabel.setText("Age " + Files
					.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(3));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ageLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		ageLabel.setBounds(48, 138, 93, 25);
		profilePanel.add(ageLabel);

		JLabel heightLabel = new JLabel();
		try {
			if (Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(0)
					.equals("US")) {
				heightLabel.setText("Height "
						+ Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt"))
								.get(6)
						+ "' "
						+ Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt"))
								.get(7)
						+ "\"");
			} else
				heightLabel
						.setText("Height "
								+ Files.readAllLines(
										Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(6)
								+ " cm");

		} catch (IOException e) {
			e.printStackTrace();
		}
		heightLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		heightLabel.setBounds(48, 195, 110, 25);
		profilePanel.add(heightLabel);

		JLabel weightLabel = new JLabel();
		try {
			if (Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(0)
					.equals("US"))
				weightLabel
						.setText("Weight "
								+ Files.readAllLines(
										Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(5)
								+ " lbs");
			else
				weightLabel
						.setText("Weight "
								+ Files.readAllLines(
										Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(5)
								+ " kg");

		} catch (IOException e) {
			e.printStackTrace();
		}
		weightLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		weightLabel.setBounds(48, 251, 110, 25);
		profilePanel.add(weightLabel);

		JLabel bustSizeLabel = new JLabel();
		try {
			if (Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(0)
					.equals("US"))
				bustSizeLabel
						.setText("Bust Size "
								+ Files.readAllLines(
										Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(8)
								+ "\"");
			else
				bustSizeLabel
						.setText("Bust Size "
								+ Files.readAllLines(
										Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(7)
								+ " cm");

		} catch (IOException e) {
			e.printStackTrace();
		}
		bustSizeLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		bustSizeLabel.setBounds(200, 84, 120, 25);
		profilePanel.add(bustSizeLabel);

		JLabel waistSizeLabel = new JLabel();
		try {
			if (Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(0)
					.equals("US"))
				waistSizeLabel
						.setText("Waist Size "
								+ Files.readAllLines(
										Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(9)
								+ "\"");
			else
				waistSizeLabel
						.setText("Waist Size "
								+ Files.readAllLines(
										Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(8)
								+ " cm");
		} catch (IOException e) {
			e.printStackTrace();
		}
		waistSizeLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		waistSizeLabel.setBounds(200, 138, 120, 25);
		profilePanel.add(waistSizeLabel);

		JLabel hightHipSizeLabel = new JLabel();
		try {
			if (Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(0)
					.equals("US"))
				hightHipSizeLabel
						.setText("Hight Hip Size "
								+ Files.readAllLines(
										Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(10)
								+ "\"");
			else
				hightHipSizeLabel
						.setText("Hight Hip Size "
								+ Files.readAllLines(
										Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(9)
								+ " cm");
		} catch (IOException e) {
			e.printStackTrace();
		}
		hightHipSizeLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		hightHipSizeLabel.setBounds(200, 195, 160, 25);
		profilePanel.add(hightHipSizeLabel);

		JLabel hipSizeLabel = new JLabel();
		try {
			if (Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(0)
					.equals("US"))
				hipSizeLabel
						.setText("Hip Size "
								+ Files.readAllLines(
										Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(11)
								+ "\"");
			else
				hipSizeLabel
						.setText("Hip Size "
								+ Files.readAllLines(
										Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(10)
								+ " cm");
		} catch (IOException e) {
			e.printStackTrace();
		}
		hipSizeLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		hipSizeLabel.setBounds(200, 251, 120, 25);
		profilePanel.add(hipSizeLabel);

		JButton editButton = newEditButton();
		profilePanel.add(editButton);

		return profilePanel;
	}

	/**
	 * Applies style settings for Edit and Submit buttons.
	 * @param btn	button to be styled
	 */
	private static void applyEditAndSubmitButtonDesign(JButton btn) {
		btn.setBackground(new Color(160, 121, 95));
		btn.setForeground(new Color(143, 200, 196));
		btn.setFont(new Font("Toledo", Font.PLAIN, 17));
	}

	/**
	 * Returns Edit button on profile panel in order to edit personal data.
	 * @return Edit button
	 */
	private static JButton newEditButton() {
		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formPanel = FormPanel.createFormPanel();
				switchPanels(formPanel, layeredPane);
			}
		});
		applyEditAndSubmitButtonDesign(editButton);
		editButton.setBounds(115, 305, 107, 42);
		return editButton;
	}

	/**
	 * Returns Submit button on the Welcome panel.
	 * Displays Main panel if profile was already created, Form panel otherwise
	 * @return Submit button
	 */
	private JButton newSubmitButton() {
		JButton submitButton = new JButton("Submit");
		applyEditAndSubmitButtonDesign(submitButton);
		submitButton.setBounds(333, 313, 107, 42);
		submitButton.setEnabled(false);
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = FirstNameField.getText() + LastNameField.getText() + ".txt";
				boolean fileExists = new File(fileName).exists();

				//Checks if file was previously created, creates Person
				if (fileExists) {
					try {
						if (Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt"))
								.get(0).equals("US")) {
							person = new Person(
									Files.readAllLines(
											Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt"))
											.get(1),
									Files.readAllLines(
											Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt"))
											.get(2),
									Integer.parseInt(Files
											.readAllLines(Paths
													.get(FirstNameField.getText() + LastNameField.getText() + ".txt"))
											.get(3)),
									Integer.parseInt(Files
											.readAllLines(Paths
													.get(FirstNameField.getText() + LastNameField.getText() + ".txt"))
											.get(4)),
									Integer.parseInt(Files
											.readAllLines(Paths
													.get(FirstNameField.getText() + LastNameField.getText() + ".txt"))
											.get(5)),
									Integer.parseInt(Files
											.readAllLines(Paths
													.get(FirstNameField.getText() + LastNameField.getText() + ".txt"))
											.get(6)),
									Integer.parseInt(Files
											.readAllLines(Paths
													.get(FirstNameField.getText() + LastNameField.getText() + ".txt"))
											.get(7)),
									Integer.parseInt(Files
											.readAllLines(Paths
													.get(FirstNameField.getText() + LastNameField.getText() + ".txt"))
											.get(8)),
									Integer.parseInt(Files
											.readAllLines(Paths
													.get(FirstNameField.getText() + LastNameField.getText() + ".txt"))
											.get(9)),
									Integer.parseInt(Files
											.readAllLines(Paths
													.get(FirstNameField.getText() + LastNameField.getText() + ".txt"))
											.get(10)),
									Integer.parseInt(Files
											.readAllLines(Paths
													.get(FirstNameField.getText() + LastNameField.getText() + ".txt"))
											.get(11)));
						} else {
							person = new Person(
									Files.readAllLines(
											Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt"))
											.get(1),
									Files.readAllLines(
											Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt"))
											.get(2),
									Integer.parseInt(Files
											.readAllLines(Paths
													.get(FirstNameField.getText() + LastNameField.getText() + ".txt"))
											.get(3)),
									Integer.parseInt(Files
											.readAllLines(Paths
													.get(FirstNameField.getText() + LastNameField.getText() + ".txt"))
											.get(4)),
									Double.parseDouble(Files
											.readAllLines(Paths
													.get(FirstNameField.getText() + LastNameField.getText() + ".txt"))
											.get(5)),
									Double.parseDouble(Files
											.readAllLines(Paths
													.get(FirstNameField.getText() + LastNameField.getText() + ".txt"))
											.get(6)),
									Double.parseDouble(Files
											.readAllLines(Paths
													.get(FirstNameField.getText() + LastNameField.getText() + ".txt"))
											.get(7)),
									Double.parseDouble(Files
											.readAllLines(Paths
													.get(FirstNameField.getText() + LastNameField.getText() + ".txt"))
											.get(8)),
									Double.parseDouble(Files
											.readAllLines(Paths
													.get(FirstNameField.getText() + LastNameField.getText() + ".txt"))
											.get(9)),
									Double.parseDouble(Files
											.readAllLines(Paths
													.get(FirstNameField.getText() + LastNameField.getText() + ".txt"))
											.get(10)));
						}
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					mainPanel = createMainPanel();

					switchPanels(mainPanel, layeredPane);
				} else {
					//if file wasn't created before, Form panel opens
					formPanel = FormPanel.createFormPanel();

					switchPanels(formPanel, layeredPane);
				}
			}
		});
		return submitButton;
	}
}
