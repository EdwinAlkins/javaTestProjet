package edwin.alkins.swingTest.littelGame2.ihm.launcher;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edwin.alkins.swingTest.littelGame2.core.world.World;
import edwin.alkins.swingTest.littelGame2.ihm.panel.PanelDisplay;

public class FrameOfGameV1 extends JFrame {
	private static final long serialVersionUID = -2008926181773251163L;
	private JPanel contentPane;
	private PanelDisplay panelDisplay;

	/**
	 * Create the frame.
	 */
	public FrameOfGameV1(World world) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panelDisplay = new PanelDisplay(world);
		contentPane.add(panelDisplay, BorderLayout.CENTER);
	}

	public void render() {
		//this.panelDisplay.repaint();
		this.panelDisplay.render();
	}
}
