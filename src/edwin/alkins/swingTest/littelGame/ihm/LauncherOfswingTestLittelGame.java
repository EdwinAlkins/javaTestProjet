package edwin.alkins.swingTest.littelGame.ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;

import edwin.alkins.swingTest.littelGame.core.ManagerIHM;
import edwin.alkins.swingTest.littelGame.exception.ExceptionComponentIsAlreadyInTheContainer;
import edwin.alkins.swingTest.littelGame.exception.ExceptionComponentIsNotInTheContainer;

public class LauncherOfswingTestLittelGame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LauncherOfswingTestLittelGame window = new LauncherOfswingTestLittelGame();
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
	public LauncherOfswingTestLittelGame() {
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
		
		JPanel northPanel = new JPanelResourceDisplay();
		frame.getContentPane().add(northPanel, BorderLayout.NORTH);
		
		JPanel westPanel = new JPanel();
		frame.getContentPane().add(westPanel, BorderLayout.WEST);
		
		JPanel southPanel = new JPanel();
		frame.getContentPane().add(southPanel, BorderLayout.SOUTH);
		
		JPanel eastPanel = new JPanel();
		frame.getContentPane().add(eastPanel, BorderLayout.EAST);
		
		JPanel centerPanel = new JPanel();
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		
		ManagerIHM.getInstance().setFrame(frame);
		
		try {
			ManagerIHM.getInstance().addComponent(JPanelResourceDisplay.class.getName(), northPanel);
		} catch (ExceptionComponentIsAlreadyInTheContainer e1) {
			e1.printStackTrace();
		}
		
		try {
			//ManagerIHM.getInstance().addComponent(northPanel.getClass().getName(), northPanel);
			ManagerIHM.getInstance().addComponentOfFrame(BorderLayout.NORTH,northPanel.getClass().getName());
			//ManagerIHM.getInstance().addComponent(southPanel.getClass().getName(), southPanel);
			//ManagerIHM.getInstance().addComponent(eastPanel.getClass().getName(), eastPanel);
			//ManagerIHM.getInstance().addComponent(centerPanel.getClass().getName(), centerPanel);
		} catch (ExceptionComponentIsAlreadyInTheContainer | ExceptionComponentIsNotInTheContainer e) {
			e.printStackTrace();
		}
	}

}
