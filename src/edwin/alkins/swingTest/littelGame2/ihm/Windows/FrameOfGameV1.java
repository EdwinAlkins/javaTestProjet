package edwin.alkins.swingTest.littelGame2.ihm.Windows;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edwin.alkins.swingTest.littelGame2.core.world.World;
import edwin.alkins.swingTest.littelGame2.ihm.camera.Camera;
import edwin.alkins.swingTest.littelGame2.ihm.panel.PanelDisplay;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

public class FrameOfGameV1 extends JFrame {
	private static final long serialVersionUID = -2008926181773251163L;
	private JPanel contentPane;
	private PanelDisplay panelDisplay;

	/**
	 * Create the frame.
	 * @param camera 
	 */
	public FrameOfGameV1(World world, Camera camera) {
		setBackground(Color.WHITE);
		setUndecorated(new Boolean(System.getProperty("isFrame")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		if(new Boolean(System.getProperty("fullScrean")))
			setBounds(new Rectangle(tailleEcran));
		else
			setBounds(AffineTransform.getScaleInstance(0.5, 0.5).createTransformedShape(new Rectangle(new Point(tailleEcran.width/2, tailleEcran.height/2), tailleEcran)).getBounds());
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(1, 1, 1, 1));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panelDisplay = new PanelDisplay(world,camera);
		contentPane.add(panelDisplay, BorderLayout.CENTER);
	}

	public void render() {
		if(this.isActive())
			this.panelDisplay.render();
	}
}
