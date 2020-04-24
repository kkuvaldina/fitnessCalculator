package fitnessCalculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

/**
 * 
 * @author Kseniia
 *
 */
@SuppressWarnings("serial")
public class BodyType extends JFrame {
	static Person person = null;
	private JPanel contentPane;
	private String[] bodyTypeImageArr = { "/fitnessCalculator/Images/bottom_hourglass.jpg",
			"/fitnessCalculator/Images/hourglass.jpg", "/fitnessCalculator/Images/inverted_triangle.jpg",
			"/fitnessCalculator/Images/rectangle.jpg", "/fitnessCalculator/Images/spoon.jpg",
			"/fitnessCalculator/Images/top_hourglass.jpg", "/fitnessCalculator/Images/triangle.jpg" };
	private JPanel bodyTypeResultPanel;
	private JLabel picLabel;
	private int lblImage;
	private String bodyTypeResult = "";
	private JTextArea textArea = new JTextArea();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BodyType frame = new BodyType();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private int switchLblImage() {
		int num;
		switch (bodyTypeResult) {
		case "Bottom Hourglass":
			num = 0;
			textArea = new JTextArea("As a bottom hourglass, you have the general hourglass shape,\nbut your hip measurements are slightly larger than your bust.\r\n" + 
					"\r\n" + 
					"Stylists probably point you toward form-fitting knits and\ndresses."); 
			break;
		case "HourGlass":
			num = 1;
			textArea = new JTextArea("If your hips and bust are nearly equal in size and you have a\nwell-defined waist that’s narrower than both, you have an\nhourglass shape.\r\n" + 
					"\r\n" + 
					"Your legs and upper body are probably considered\nproportionate.\r\n" + 
					"\r\n" + 
					"Your shoulders may be slightly rounded, and you most likely\nhave a rounded buttocks.\r\n" + 
					"\r\n" + 
					"Form-fitting or tailored clothing have traditionally been\ndesigned with this body type in mind.");
			break;
		case "Inverted Triangle":
			num = 2;
			textArea = new JTextArea("If your shoulders and bust are larger than your relatively\nnarrow hips, you have what’s known as an inverted triangle\nor “apple” shape.\r\n" + 
					"\r\n" + 
					"Stylists may recommend tops that have some shaping around the waist and more open necklines, or clothing that shows off\nyour legs.");
			break;
		case "Rectangle":
			num = 3;
			textArea = new JTextArea("If your waist measurements are about the same as your\nhip or bust, and your shoulders and hips are about the same\nwidth, you have what’s called a “banana” or rectangle body\ntype.\r\n" + 
					"\r\n" + 
					"Stylists will probably point you toward off-the-shoulder tops,\ntube dresses, and belted waists.");
			break;
		case "Spoon":
			num = 4;
			textArea = new JTextArea("The spoon body type is pretty similar to the triangle or\n“pear” shape." + 
					"Your hips are larger than your bust or the rest of your body and may have a “shelf”-like appearance." + 
					"You likely have a defined waist. You may also carry some weight in your upper arms and upper thighs." + 
					"You may be told to look for dresses that have classic “baby doll” cuts or other items with an empire waist.");
			break;
		case "Top Hourglass":
			num = 5;
			textArea = new JTextArea("As a top hourglass, you have the general hourglass shape,\nbut your bust measurements are slightly larger than your hips.\r\n" + 
					"\r\n" +
					"Boot cut or slightly flared pants probably fit you well,\nas do full or A-line skirts and tailored jackets.");
			break;
		case "Triangle":
			num = 6;
			textArea = new JTextArea("With this shape, your shoulders and bust are narrower than\nyour hips.\r\n" + 
					"\r\n" +
					"You probably have slim arms and a fairly defined waist. Your waist most\nlikely slopes out to your hips.\r\n" + 
					"\r\n" +
					"Stylists often recommend\nclothing that shows off the waistline.");
			break;
		default:
			num = 0;
		}
		return num;
	}

	/**
	 * Create the frame.
	 */
	public BodyType() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 15, 5, 15));
		contentPane.setBackground(new Color(253, 242, 197));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLabel titleLabel = new JLabel("Body Type");
		titleLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Toledo", Font.PLAIN, 18));
		titleLabel.setForeground(new Color(98, 61, 69));
		contentPane.add(titleLabel, BorderLayout.NORTH);

		bodyTypeResultPanel = newBodyTypeResulPanel();
		contentPane.add(bodyTypeResultPanel, BorderLayout.CENTER);

		

	}

	/**
	 * 
	 * @return
	 */
	private JLabel newPicLabel() {
		JLabel lblpicLabel = new JLabel(new ImageIcon(BodyType.class.getResource(bodyTypeImageArr[lblImage])));
		return lblpicLabel;
	}

	/**
	 * 
	 * @return
	 */
	private JPanel newBodyTypeResulPanel() {
		person = Gui.getPerson();
		bodyTypeResult = Calculations.bodyTypeCalculator(person);
		lblImage = switchLblImage();
		
		picLabel = newPicLabel();
		contentPane.add(picLabel, BorderLayout.EAST);
		
		JPanel bodyTypeResultPanel = new JPanel();
		bodyTypeResultPanel.setBackground(new Color(253, 242, 197));
		bodyTypeResultPanel.setLayout(null);
		
		JLabel bodyTypeResultLabel = new JLabel(bodyTypeResult);
		bodyTypeResultLabel.setBounds(131, 11, 279, 55);
		bodyTypeResultLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bodyTypeResultLabel.setFont(new Font("Toledo", Font.PLAIN, 14));
		bodyTypeResultPanel.add(bodyTypeResultLabel);
		
		textArea.setBounds(40, 77, 446, 294);
		textArea.setBackground(new Color(253, 242, 197));
		textArea.setBorder(new EmptyBorder(0, 40, 0, 40));
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		bodyTypeResultPanel.add(textArea);

		return bodyTypeResultPanel;
	}
}
