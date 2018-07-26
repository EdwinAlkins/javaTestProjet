package edwin.alkins.swingTest.littelGame2.ihm.launcher;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edwin.alkins.swingTest.littelGame2.core.entity.Entity;
import edwin.alkins.swingTest.littelGame2.ihm.launcher.panel.PanelDisplay;

import java.awt.Canvas;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class FrameOfGameV1 extends JFrame {
	private static final long serialVersionUID = -2008926181773251163L;
	private JPanel contentPane;
	private PanelDisplay panelDisplay;

	/**
	 * Create the frame.
	 */
	public FrameOfGameV1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panelDisplay = new PanelDisplay();
		contentPane.add(panelDisplay, BorderLayout.CENTER);
	}
	
	public PanelDisplay getPanelDisplayArea() {
		return this.panelDisplay;
	}
}
