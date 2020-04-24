package fitnessCalculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
/**
 * 
 * @author Kseniia
 *
 */
@SuppressWarnings("serial")
public class HealthyWeight extends JFrame {
	static Person person = null;
	private JPanel contentPane;
//healthyWeightCalculator(person)
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HealthyWeight frame = new HealthyWeight();
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
	public HealthyWeight() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(253, 242, 197));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel titleLabel = new JLabel("Healthy Weight");
		titleLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Toledo", Font.PLAIN, 18));
		titleLabel.setForeground(new Color(98, 61, 69));
		contentPane.add(titleLabel, BorderLayout.NORTH);
		
		JPanel healthyWeightResultPanel = newHealthyWeightResulPanel();
		contentPane.add(healthyWeightResultPanel, BorderLayout.CENTER);
	}

	private JPanel newHealthyWeightResulPanel() {
		person = Gui.getPerson();
		
		JPanel healthyWeightResultPanel = new JPanel();
		healthyWeightResultPanel.setBackground(new Color(253, 242, 197));
		healthyWeightResultPanel.setLayout(null);
		
		JLabel healthyWeightResultLabel = new JLabel("The healthy weight range for your height is ");
		healthyWeightResultLabel.setBounds(142, 25, 467, 59);
		healthyWeightResultLabel.setHorizontalAlignment(SwingConstants.CENTER);
		healthyWeightResultLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		healthyWeightResultPanel.add(healthyWeightResultLabel);
	
		JTextArea healthyWeightResultArea = new JTextArea("		Maintaining a Healthy Weight.\r\n" +
				"\r\n" + 
				"Being underweight, overweight, or obese are" + 
				" conditions that lead to significantly\ndifferent health complications. Managing these conditions however, involves\n" + 
				"addressing many similar underlying issues including diet, exercise, and possible mental\nhealth disorders.\r\n" +  
				"\r\n" + 
				"In the case of being underweight, increasing calorie intake through eating nutrient-rich\nfoods such as whole-grains," + 
				" vegetables, lean protein sources, and nuts and seeds\nmore frequently can help underweight individuals to gain weight " + 
				"in a healthy manner. \nIn addition, exercise such as weight lifting to increase muscle mass can also increase a\nperson's " + 
				"weight. When the underlying cause of a person being underweight is a mental\nhealth disorder such as anorexia or bulimia " + 
				"nervosa, treatment involves addressing the\npsychological problems in conjunction with physical approaches to increase weight.");
		healthyWeightResultArea.setBounds(59, 114, 655, 261);
		healthyWeightResultArea.setBackground(new Color(253, 242, 197));
		healthyWeightResultArea.setBorder(new EmptyBorder(0, 70, 0, 70));
		healthyWeightResultArea.setLineWrap(true);
		healthyWeightResultArea.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		healthyWeightResultPanel.add(healthyWeightResultArea);
		
		
		return healthyWeightResultPanel;
	}
}