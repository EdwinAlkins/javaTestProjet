package edwin.alkins.swingTest.littelGame.ihm;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class JPanelLoading extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8047886124763486924L;
	public static final String LODING = "/edwin/alkins/swingTest/littelGame/resource/image/gif/loading_gif_3.gif";

	/**
	 * Create the panel.
	 */
	public JPanelLoading() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel error = new JLabel();
		error.setIcon(new ImageIcon(JPanelError.class.getResource(LODING)));
		error.setHorizontalAlignment(SwingConstants.CENTER);
		add(error, BorderLayout.CENTER);
	}

}
