package fitnessCalculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
/**
 * 
 * @author Kseniia
 *
 */
@SuppressWarnings("serial")
public class BodyType extends JFrame {
	static Person person = null;
	private JPanel contentPane;
	
//bodyTypeCalculator(person)
	
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

	/**
	 * Create the frame.
	 */
	public BodyType() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(253, 242, 197));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel titleLabel = new JLabel("Body Type");
		titleLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Toledo", Font.PLAIN, 18));
		titleLabel.setForeground(new Color(98, 61, 69));
		contentPane.add(titleLabel, BorderLayout.NORTH);
		
		JPanel bodyTypeResultPanel = newBodyTypeResulPanel();
		contentPane.add(bodyTypeResultPanel, BorderLayout.CENTER);
	}

	private JPanel newBodyTypeResulPanel() {
		person = Gui.getPerson();
		
		JPanel bodyTypeResultPanel = new JPanel();
		bodyTypeResultPanel.setBackground(new Color(253, 242, 197));
		bodyTypeResultPanel.setLayout(null);
		return bodyTypeResultPanel;
	}
}
