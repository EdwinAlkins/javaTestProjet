package edwin.alkins.theExecutionDirectoryPath;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class LauncherOfTheExecutionDirectoryPath {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LauncherOfTheExecutionDirectoryPath window = new LauncherOfTheExecutionDirectoryPath();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LauncherOfTheExecutionDirectoryPath() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel textForDirectoryPath = new JLabel("New label");
		textForDirectoryPath.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(textForDirectoryPath, BorderLayout.CENTER);
		
		try {
			textForDirectoryPath.setText(System.getProperty("user.dir"));
		}catch(Exception e) {
			textForDirectoryPath.setText("sa marche pas");
		}
	}

}
