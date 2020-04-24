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
 * BMR class extends JFrame, gets number from Calculations class and displays user's BMR result and description.
 * 
 * @author Kseniia
 *
 */
@SuppressWarnings("serial")
public class BMR extends JFrame {
	static Person person = null;
	private JPanel contentPane;
	private int bmr = 0; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BMR frame = new BMR();
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
	public BMR() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(253, 242, 197));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel titleLabel = new JLabel("BMR (The Basal Metabolic Rate)");
		titleLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Toledo", Font.PLAIN, 18));
		titleLabel.setForeground(new Color(98, 61, 69));
		contentPane.add(titleLabel, BorderLayout.NORTH);
		
		JPanel bmrResultPanel = newBmrResulPanel();
		contentPane.add(bmrResultPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Returns panel with BMR result
	 * @return BMR panel
	 */
	private JPanel newBmrResulPanel() {
		person = Gui.getPerson();
		bmr = Calculations.bmrCalculator(person);
		
		JPanel bmrResultPanel = new JPanel();
		bmrResultPanel.setBackground(new Color(253, 242, 197));
		bmrResultPanel.setLayout(null);
		
		JLabel bmrResultLabel = new JLabel("BMR = " + bmr + " calories/day");
		bmrResultLabel.setBounds(290, 27, 198, 59);
		bmrResultLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bmrResultLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		bmrResultPanel.add(bmrResultLabel);
	
		JTextArea bmiResultArea = new JTextArea("Basal Metabolic Rate is the number of calories required" + 
		" to keep your body\nfunctioning at rest. BMR is also known as your body’s metabolism; therefore," + 
		"\nany increase to your metabolic weight, such as exercise, will increase your BMR.\nOnce you’ve " + 
		"determined your BMR, you can begin to monitor how many calories\na day you need to maintain or lose weight.");
		bmiResultArea.setBounds(78, 126, 612, 249);
		bmiResultArea.setBackground(new Color(253, 242, 197));
		bmiResultArea.setBorder(new EmptyBorder(0, 70, 0, 70));
		bmiResultArea.setLineWrap(true);
		bmiResultArea.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		bmrResultPanel.add(bmiResultArea);
		
		return bmrResultPanel;
	}
}
