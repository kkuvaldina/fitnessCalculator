package fitnessCalculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * BMI class extends JFrame, gets number from Calculations class and displays user's BMI result and description.
 * 
 * @author Kseniia
 *
 */
@SuppressWarnings("serial")
public class BMI extends JFrame {
	static Person person = null;
	private JPanel contentPane;
	double bmi = 0; 
	String result; 
	Color color = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BMI frame = new BMI();
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
	public BMI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(253, 242, 197));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel titleLabel = new JLabel("BMI (The Body Mass Index)");
		titleLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Toledo", Font.PLAIN, 18));
		titleLabel.setForeground(new Color(98, 61, 69));
		contentPane.add(titleLabel, BorderLayout.NORTH);
		
		JPanel bmiResultPanel = newBmiResulPanel();
		contentPane.add(bmiResultPanel, BorderLayout.CENTER);
		
	}
	
	/**
	 * Returns the description of BMI number in certain color.
	 * @return result of the BMI
	 */
	private String getBmiResult() {
		if(bmi<16) {
			result = "Severe Thinness";
			color = Color.RED;
		} else if(bmi>=16 && bmi<17) {
			result = "Moderate Thinness";
			color = Color.ORANGE;
		} else if(bmi>=17 && bmi<18.5) {
			result = "Mild Thinness";
			color = Color.ORANGE;
		} else if(bmi>=18.5 && bmi<25) {
			result = "Normal";
			color = Color.GREEN;
		} else if(bmi>=25 && bmi<30) {
			result = "Overweight";
			color = Color.ORANGE;
		} else if(bmi>=30 && bmi<35) {
			result = "Obese Class I";
			color = Color.ORANGE;
		} else if(bmi>=35 && bmi<40) {
			result = "Obese Class II";
			color = Color.RED;
		} else {
			result = "Obese Class III";
			color = Color.RED;
		}
		return result;
	}

	/**
	 * Returns panel with BMI result
	 * @return BMI panel
	 */
	private JPanel newBmiResulPanel() {
		person = Gui.getPerson();
		bmi = Calculations.bmiCalculator(person);
		result = getBmiResult();
		
		JPanel bmiResultPanel = new JPanel();
		bmiResultPanel.setBackground(new Color(253, 242, 197));
		bmiResultPanel.setLayout(null);
		
		JLabel bmiResultLabel = new JLabel("Your BMI " + bmi);
		bmiResultLabel.setBounds(197, 0, 198, 59);
		bmiResultLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		bmiResultLabel.setFont(new Font("Toledo", Font.PLAIN, 14));
		bmiResultPanel.add(bmiResultLabel);
		
		JLabel normalBmiLabel = new JLabel("Healthy BMI Range: 18.5 kg/m2 - 25 kg/m2");
		normalBmiLabel.setBounds(0, 83, 774, 64);
		normalBmiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		normalBmiLabel.setFont(new Font("Toledo", Font.PLAIN, 14));
		bmiResultPanel.add(normalBmiLabel);
		
		JTextArea bmiResultArea = new JTextArea("BMI is a measurement of a person's leanness or corpulence based on their height and weight,\n" + 
		"and is intended to quantify tissue mass. It is widely used as a general indicator of whether a\nperson has a healthy body weight for " + 
		"their height. Specifically, the value obtained from the\ncalculation of BMI is used to categorize whether a person is underweight, normal" + 
		" weight,\noverweight, or obese depending on what range the value falls between. These ranges of BMI\nvary based on factors such as region " + 
		"and age, and are sometimes further divided into\nsubcategories such as severely underweight or very severely obese. Being overweight or\n" + 
		"underweight can have significant health effects, so while BMI is an imperfect measure of\nhealthy body weight, it is a useful indicator of" + 
		" whether any additional testing or action is\nrequired.");
		bmiResultArea.setBounds(30, 158, 709, 217);
		bmiResultArea.setBackground(new Color(253, 242, 197));
		bmiResultArea.setBorder(new EmptyBorder(0, 70, 0, 70));
		bmiResultArea.setLineWrap(true);
		bmiResultArea.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		bmiResultPanel.add(bmiResultArea);
		
		JLabel lblNewLabel = new JLabel("(" + result + ")");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		lblNewLabel.setBounds(416, 12, 167, 30);
		lblNewLabel.setForeground(color);
		bmiResultPanel.add(lblNewLabel);
		
		return bmiResultPanel;
	}
}
