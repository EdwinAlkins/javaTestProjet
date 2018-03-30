package edwin.alkins.swingTest.littelGame.ihm;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class JPanelError extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6861023855514649801L;
	public static final String ERROR = "/edwin/alkins/swingTest/littelGame/resource/image/gif/error_gif_1.gif";
	
	/**
	 * Create the panel.
	 */
	public JPanelError() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel error = new JLabel();
		error.setIcon(new ImageIcon(JPanelError.class.getResource(ERROR)));
		error.setHorizontalAlignment(SwingConstants.CENTER);
		add(error, BorderLayout.CENTER);

	}

}
