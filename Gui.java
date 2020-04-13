package fitnessCalculator;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;

/**
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
	private static BMI bmi = new BMI();
	private static BMR bmr = new BMR();
	private static HealthyWeight healthyWeight = new HealthyWeight();
	private static BodyType bodyType = new BodyType();
	static Person person;
	static double bmiResult;

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

	public static double getBmiResult() {
		return bmiResult;
	}
	
	public static JTextField getFirstNameField() {
		return FirstNameField;
	}
	
	public static JTextField getLastNameField() {
		return LastNameField;
	}
	
	public static Person getPerson() {
		return person;
	}
	
	public static JPanel getMainPanel() {
		return mainPanel;
	}
	
	public static JLayeredPane getLayeredPane(int x) {
		return layeredPane;
	}

	public static void switchPanels(JPanel panel, JLayeredPane layeredPane) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	public static void switchPanels(FormPanel panel, JLayeredPane layeredPane) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

	

	/**
	 * 
	 * @param btn
	 */
	private static void applyButtonDesign(JButton btn) {
		btn.setBackground(new Color(66, 183, 194));
		btn.setForeground(new Color(253, 242, 197));
		btn.setFont(new Font("Toledo", Font.PLAIN, 16));
	}


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

	/**
	 * 
	 * @return
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

		JLabel progressLabel = new JLabel("Your Progress");
		progressLabel.setBounds(505, 67, 155, 50);
		progressLabel.setHorizontalAlignment(SwingConstants.CENTER);
		progressLabel.setFont(new Font("Toledo", Font.PLAIN, 15));
		progressLabel.setForeground(new Color(98, 61, 69));
		mainPanel.add(progressLabel);

		JButton bmiButton = newBmiButton();
		mainPanel.add(bmiButton);

		JButton bmrButton = newBmrButton();
		mainPanel.add(bmrButton);

		JButton bodyTypeButton = newBodyTypeButton();
		mainPanel.add(bodyTypeButton);

		JButton healthyWeightButton = newHealthyWeightButton();
		mainPanel.add(healthyWeightButton);

		return mainPanel;
	}

	private static JButton newHealthyWeightButton() {
		JButton healthyWeightButton = new JButton("Healthy Weight");
		healthyWeightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				healthyWeight.setVisible(true);
			}
		});
		applyButtonDesign(healthyWeightButton);
		healthyWeightButton.setBounds(584, 365, 193, 83);
		return healthyWeightButton;
	}

	private static JButton newBodyTypeButton() {
		JButton bodyTypeButton = new JButton("Body Type");
		bodyTypeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bodyType.setVisible(true);
			}
		});
		applyButtonDesign(bodyTypeButton);
		bodyTypeButton.setBounds(381, 365, 193, 83);
		return bodyTypeButton;
	}

	private static JButton newBmrButton() {
		JButton bmrButton = new JButton("BMR");
		bmrButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bmr.setVisible(true);
			}
		});
		applyButtonDesign(bmrButton);
		bmrButton.setBounds(584, 271, 193, 83);
		return bmrButton;
	}

	private static JButton newBmiButton() {
		JButton bmiButton = new JButton("BMI");
		bmiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// double bmiResult = bmiCalculator(person);
				bmiResult = Calculations.bmiCalculator(person);
				bmi.setVisible(true);
			}
		});
		applyButtonDesign(bmiButton);
		bmiButton.setBounds(381, 271, 193, 83);
		return bmiButton;
	}

	/**
	 * 
	 * @return
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
		fullNameLabel.setBounds(48, 84, 93, 25);
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
		heightLabel.setBounds(48, 195, 93, 25);
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
		weightLabel.setBounds(48, 251, 93, 25);
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
		hightHipSizeLabel.setBounds(200, 195, 130, 25);
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
	 * 
	 * @param btn
	 */
	private static void applyEditAndSubmitButtonDesign(JButton btn) {
		btn.setBackground(new Color(160, 121, 95));
		btn.setForeground(new Color(143, 200, 196));
		btn.setFont(new Font("Toledo", Font.PLAIN, 17));
	}

	/**
	 * 
	 * @return
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
	 * 
	 * @return
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
					//layeredPane.add(mainPanel);

					switchPanels(mainPanel, layeredPane);
				} else {
					formPanel = FormPanel.createFormPanel();
					//layeredPane.add(formPanel);

					switchPanels(formPanel, layeredPane);
				}

			}
		});
		return submitButton;
	}
}
