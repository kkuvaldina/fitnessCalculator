package fitnessCalculator;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
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
	private JLayeredPane layeredPane;
	private JPanel formPanel;
	private JPanel mainPanel;
	private JPanel welcomePanel;
	private JTextField FirstNameField;
	private JTextField LastNameField;
	private JLayeredPane layeredPane2;
	private JPanel metricUnitsPanel;
	private JPanel usUnitsPanel;
	private JButton usButton;
	private JButton metricButton;
	private BMI bmi = new BMI();
	private BMR bmr = new BMR();
	private HealthyWeight healthyWeight = new HealthyWeight();
	private BodyType bodyType = new BodyType();
	Person person;
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
	
	private void switchPanels(JPanel panel, JLayeredPane layeredPane) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	

	private JPanel createFormPanel() {
		formPanel = new JPanel();
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
	
	/**
	 * 
	 * @param btn
	 */
	private void applyButtonDesign(JButton btn) {
		btn.setBackground(new Color(66, 183, 194));
		btn.setForeground(new Color(253, 242, 197));
		btn.setFont(new Font("Toledo", Font.PLAIN, 16));
	}

	
	
	private JButton newUsButton() {
		usButton = new JButton("US Units");
		usButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(usUnitsPanel, layeredPane2);
				
				usButton.setBackground(new Color(222, 202, 152));
				usButton.setForeground(new Color(66, 183, 194));
				
				metricButton.setBackground(new Color(66, 183, 194));
				metricButton.setForeground(new Color(253, 242, 197));
			}
		});
		usButton.setBackground(new Color(66, 183, 194));
		usButton.setForeground(new Color(253, 242, 197));
		usButton.setBackground(new Color(222, 202, 152));
		usButton.setForeground(new Color(66, 183, 194));
		usButton.setFont(new Font("Toledo", Font.PLAIN, 16));
		usButton.setBounds(10, 67, 290, 37);
		
		return usButton;
	}

	private JButton newMetricButton() {
		metricButton = new JButton("Metric Units");
		metricButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(metricUnitsPanel, layeredPane2);
				
				usButton.setBackground(new Color(66, 183, 194));
				usButton.setForeground(new Color(253, 242, 197));
				
				metricButton.setBackground(new Color(222, 202, 152));
				metricButton.setForeground(new Color(66, 183, 194));
			}
		});
		metricButton.setBounds(484, 67, 290, 37);
		metricButton.setBackground(new Color(66, 183, 194));
		metricButton.setForeground(new Color(253, 242, 197));
		metricButton.setFont(new Font("Toledo", Font.PLAIN, 16));
		return metricButton;
	}

	/**
	 * 
	 * @return
	 */
	private JPanel createMetricUnitsPanel() {
		metricUnitsPanel = new JPanel();
		metricUnitsPanel.setBackground(new Color(222, 202, 152));
		metricUnitsPanel.setLayout(null);
		
		JLabel ageLabel = new JLabel("Age");
		ageLabel.setFont(new Font("Toledo", Font.PLAIN, 13));
		ageLabel.setForeground(new Color(98, 61, 69));
		ageLabel.setBounds(81, 44, 92, 45);
		metricUnitsPanel.add(ageLabel);
		
		JTextField ageField = new JTextField();
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
		
		JLabel heightLabel = new JLabel("Height");
		heightLabel.setFont(new Font("Toledo", Font.PLAIN, 13));
		heightLabel.setForeground(new Color(98, 61, 69));
		heightLabel.setBounds(81, 156, 57, 45);
		metricUnitsPanel.add(heightLabel);
		
		JTextField cmField = new JTextField();
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
		highHipSizeLabel.setBounds(456, 156, 82, 45);
		metricUnitsPanel.add(highHipSizeLabel);
		
		JTextField highHipSizeField = new JTextField();
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
		hipSizeField.setForeground(new Color(98, 61, 69));
		hipSizeField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		hipSizeField.setBounds(552, 219, 145, 28);
		hipSizeField.setMargin(new Insets(5, 5, 5, 5));
		hipSizeField.addKeyListener(new KeyAdapter() {
			/*public void keyReleased(KeyEvent event) {
				String age = ageField.getText();
		        String height = cmField.getText();
		        String weight = weightField.getText();
		        String bustSize = bustSizeField.getText();
		        String waistSize = waistSizeField.getText();
		        String highHipSize = highHipSizeField.getText();
		        String hipSize = hipSizeField.getText();
		        
		        if (!age.isEmpty() && !height.isEmpty() && !weight.isEmpty() && !bustSize.isEmpty() 
		        		&& !waistSize.isEmpty() && !highHipSize.isEmpty() && !hipSize.isEmpty()) {
		        	submitButton.setEnabled(true);
		        }
		        else submitButton.setEnabled(false);
		    }*/
		});
		metricUnitsPanel.add(hipSizeField);
		
		JLabel hipSizeInchesLabel = new JLabel("cm");
		hipSizeInchesLabel.setFont(new Font("Toledo", Font.PLAIN, 12));
		hipSizeInchesLabel.setForeground(new Color(98, 61, 69));
		hipSizeInchesLabel.setBounds(705, 219, 57, 31);
		metricUnitsPanel.add(hipSizeInchesLabel);
		
		int gender;
		
		if(maleButton.isSelected()) gender = 1;
		else gender = 0;
		
		JButton submitButton = new JButton("Submit");		
		//submitButton.setEnabled(false);
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				person = new Person(FirstNameField.getText(), LastNameField.getText(), Integer.parseInt(ageField.getText()), 
						gender, Integer.parseInt(weightField.getText()), Double.parseDouble(cmField.getText()), 
						Double.parseDouble(bustSizeField.getText()), Double.parseDouble(waistSizeField.getText()), 
						Double.parseDouble(highHipSizeField.getText()), Double.parseDouble(hipSizeField.getText()));
				
				String fileName = FirstNameField.getText() + LastNameField.getText() + ".txt";
				
				try(PrintWriter writer = new PrintWriter(fileName)) {
					writer.println(UnitType.METRIC);
					writer.println(FirstNameField.getText());
					writer.println(LastNameField.getText());
					writer.println(Integer.parseInt(ageField.getText()));
					writer.println(gender);
					writer.println(Integer.parseInt(weightField.getText()));
					writer.println(Integer.parseInt(cmField.getText()));
					writer.println(Integer.parseInt(bustSizeField.getText()));
					writer.println(Integer.parseInt(waistSizeField.getText()));
					writer.println(Integer.parseInt(highHipSizeField.getText()));
					writer.println(Integer.parseInt(hipSizeField.getText()));
					
				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				}
				mainPanel = createMainPanel();
				layeredPane.add(mainPanel);
				
				switchPanels(mainPanel, layeredPane);
			}
		});
		//submitButton.setEnabled(false);
		submitButton.setBounds(332, 293, 122, 45);
		submitButton.setBackground(new Color(66, 183, 194));
		submitButton.setForeground(new Color(253, 242, 197));
		submitButton.setFont(new Font("Toledo", Font.PLAIN, 16));
		metricUnitsPanel.add(submitButton);
		
		return metricUnitsPanel;
	}

	/**
	 * 
	 * @return
	 */
	private JPanel createUsUnitsPanel() {
		usUnitsPanel = new JPanel();
		usUnitsPanel.setBackground(new Color(222, 202, 152));
		usUnitsPanel.setLayout(null);
		
		JLabel ageLabel = new JLabel("Age");
		ageLabel.setFont(new Font("Toledo", Font.PLAIN, 13));
		ageLabel.setForeground(new Color(98, 61, 69));
		ageLabel.setBounds(81, 44, 92, 45);
		usUnitsPanel.add(ageLabel);
		
		JTextField ageField = new JTextField();
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
		
		JLabel heightLabel = new JLabel("Height");
		heightLabel.setFont(new Font("Toledo", Font.PLAIN, 13));
		heightLabel.setForeground(new Color(98, 61, 69));
		heightLabel.setBounds(81, 156, 57, 45);
		usUnitsPanel.add(heightLabel);
		
		JTextField feetField = new JTextField();
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
		hipSizeField.setForeground(new Color(98, 61, 69));
		hipSizeField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		hipSizeField.setBounds(552, 219, 145, 28);
		hipSizeField.setMargin(new Insets(5, 5, 5, 5));
		hipSizeField.addKeyListener(new KeyAdapter() {
			/*public void keyReleased(KeyEvent event) {
				String age = ageField.getText();
		        int height1 = Integer.parseInt(feetField.getText());
		        int height2 = Integer.parseInt(inchesField.getText());
		        int weight = Integer.parseInt(weightField.getText());
		        int bustSize = Integer.parseInt(bustSizeField.getText());
		        int waistSize = Integer.parseInt(waistSizeField.getText());
		        int highHipSize = Integer.parseInt(highHipSizeField.getText());
		        int hipSize = Integer.parseInt(hipSizeField.getText());
		        
		        if (!age.isEmpty() && !height1.isEmpty() && !height2.isEmpty() && !weight.isEmpty() && !bustSize.isEmpty() 
		        		&& !waistSize.isEmpty() && !highHipSize.isEmpty() && !hipSize.isEmpty()) {
		        	submitButton.setEnabled(true);
		        }
		        else submitButton.setEnabled(false);
		    }*/
		});
		usUnitsPanel.add(hipSizeField);
		
		JLabel hipSizeInchesLabel = new JLabel("inches");
		hipSizeInchesLabel.setFont(new Font("Toledo", Font.PLAIN, 12));
		hipSizeInchesLabel.setForeground(new Color(98, 61, 69));
		hipSizeInchesLabel.setBounds(705, 219, 57, 31);
		usUnitsPanel.add(hipSizeInchesLabel);
		
		int gender;
		
		if(maleButton.isSelected()) gender = 1;
		else gender = 0;
		
		
		JButton submitButton = new JButton("Submit");
		//submitButton.setEnabled(false);
		submitButton.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				
				person = new Person(FirstNameField.getText(), LastNameField.getText(), Integer.parseInt(ageField.getText()), 
				gender, Integer.parseInt(weightField.getText()), Integer.parseInt(feetField.getText()), 
				Integer.parseInt(inchesField.getText()), Integer.parseInt(bustSizeField.getText()), Integer.parseInt(waistSizeField.getText()), 
				Integer.parseInt(highHipSizeField.getText()), Integer.parseInt(hipSizeField.getText()));
	
				String fileName = FirstNameField.getText() + LastNameField.getText() + ".txt";
				
				
				try(PrintWriter writer = new PrintWriter(fileName)) {
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
				mainPanel = createMainPanel();
				layeredPane.add(mainPanel);
				
				switchPanels(mainPanel, layeredPane);
			}
		});
		//submitButton.setEnabled(false);
		submitButton.setBounds(332, 293, 122, 45);
		submitButton.setBackground(new Color(66, 183, 194));
		submitButton.setForeground(new Color(253, 242, 197));
		submitButton.setFont(new Font("Toledo", Font.PLAIN, 16));
		usUnitsPanel.add(submitButton);
		
		return usUnitsPanel;
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
		        
		        if (!lastName.isEmpty() && !firstName.isEmpty()) btnSubmitButton.setEnabled(true);
		        else btnSubmitButton.setEnabled(false);
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
		        
		        if (!lastName.isEmpty() && !firstName.isEmpty()) btnSubmitButton.setEnabled(true);
		        else btnSubmitButton.setEnabled(false);
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
	private JPanel createMainPanel() {
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(253, 242, 197));
		mainPanel.setLayout(null);
		
		JLabel titleLabel = new JLabel();
		try {
			titleLabel.setText("Hello, " + Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(1));
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
	
	

	private JButton newHealthyWeightButton() {
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

	private JButton newBodyTypeButton() {
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

	private JButton newBmrButton() {
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

	private JButton newBmiButton() {
		JButton bmiButton = new JButton("BMI");
		bmiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//double bmiResult = bmiCalculator(person);
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
	private JPanel createProfilePanel() {
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
		
		//boolean usUnits = Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(0).equals("US");
		
		JLabel fullNameLabel = new JLabel();
		try {
			fullNameLabel.setText(Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(1) + " " + 
					Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(2));
		} catch (IOException e) { e.printStackTrace(); }
		fullNameLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		fullNameLabel.setBounds(48, 84, 93, 25);
		profilePanel.add(fullNameLabel);
		
		JLabel ageLabel = new JLabel();
		try {
			ageLabel.setText("Age " + Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(3));
		} catch (IOException e) { e.printStackTrace(); }
		ageLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		ageLabel.setBounds(48, 138, 93, 25);
		profilePanel.add(ageLabel);
		
		JLabel heightLabel = new JLabel();
		try {
			if(Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(0).equals("US")) {
				heightLabel.setText("Height " + Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(6) + "' " + 
						Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(7) + "\"");
			} else heightLabel.setText("Height " + Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(6) + " cm");
			
		} catch (IOException e) { e.printStackTrace(); }
		heightLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		heightLabel.setBounds(48, 195, 93, 25);
		profilePanel.add(heightLabel);
		
		JLabel weightLabel = new JLabel();
		try {
			if(Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(0).equals("US")) weightLabel.setText("Weight " + Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(5) + " lbs");
			else weightLabel.setText("Weight " + Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(5) + " kg");
			
		} catch (IOException e) { e.printStackTrace(); }
		weightLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		weightLabel.setBounds(48, 251, 93, 25);
		profilePanel.add(weightLabel);
		
		JLabel bustSizeLabel = new JLabel();
		try {
			if(Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(0).equals("US")) bustSizeLabel.setText("Bust Size " + Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(8) + "\"");
			else bustSizeLabel.setText("Bust Size " + Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(7) + " cm");
			
		} catch (IOException e) { e.printStackTrace(); }
		bustSizeLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		bustSizeLabel.setBounds(200, 84, 120, 25);
		profilePanel.add(bustSizeLabel);
		
		JLabel waistSizeLabel = new JLabel();
		try {
			if(Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(0).equals("US")) waistSizeLabel.setText("Waist Size " + Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(9) + "\"");
			else waistSizeLabel.setText("Waist Size " + Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(8) + " cm");
		} catch (IOException e) { e.printStackTrace(); }
		waistSizeLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		waistSizeLabel.setBounds(200, 138, 120, 25);
		profilePanel.add(waistSizeLabel);
		
		JLabel hightHipSizeLabel = new JLabel();
		try {
			if(Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(0).equals("US")) hightHipSizeLabel.setText("Hight Hip Size " + Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(10) + "\"");
			else hightHipSizeLabel.setText("Hight Hip Size " + Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(9) + " cm");
		} catch (IOException e) { e.printStackTrace(); }
		hightHipSizeLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		hightHipSizeLabel.setBounds(200, 195, 130, 25);
		profilePanel.add(hightHipSizeLabel);
		
		JLabel hipSizeLabel = new JLabel();
		try {
			if(Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(0).equals("US")) hipSizeLabel.setText("Hip Size " + Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(11) + "\"");
			else hipSizeLabel.setText("Hip Size " + Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(10) + " cm");
		} catch (IOException e) { e.printStackTrace(); }
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
	private void applyEditAndSubmitButtonDesign(JButton btn) {
		btn.setBackground(new Color(160, 121, 95));
		btn.setForeground(new Color(143, 200, 196));
		btn.setFont(new Font("Toledo", Font.PLAIN, 17));
	}
	
	

	/**
	 * 
	 * @return
	 */
	private JButton newEditButton() {
		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formPanel = createFormPanel();
				
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
				
				if(fileExists) {
					try {
						if (Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(0).equals("US")) {
							person = new Person(Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(1), 
									Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(2), 
									Integer.parseInt(Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(3)),
									Integer.parseInt(Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(4)), 
									Integer.parseInt(Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(5)), 
									Integer.parseInt(Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(6)),
									Integer.parseInt(Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(7)),
									Integer.parseInt(Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(8)),
									Integer.parseInt(Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(9)),
									Integer.parseInt(Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(10)),
									Integer.parseInt(Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(11))
									);
						} else {
							person = new Person(Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(1), 
									Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(2), 
									Integer.parseInt(Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(3)),
									Integer.parseInt(Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(4)), 
									Double.parseDouble(Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(5)), 
									Double.parseDouble(Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(6)),
									Double.parseDouble(Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(7)),
									Double.parseDouble(Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(8)),
									Double.parseDouble(Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(9)),
									Double.parseDouble(Files.readAllLines(Paths.get(FirstNameField.getText() + LastNameField.getText() + ".txt")).get(10))
									);
						}
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					mainPanel = createMainPanel();
					layeredPane.add(mainPanel);
					
					switchPanels(mainPanel, layeredPane);
				} else {
					formPanel = createFormPanel();
					layeredPane.add(formPanel);
					
					switchPanels(formPanel, layeredPane);
				}

			}
		});
		return submitButton;
	}
}
