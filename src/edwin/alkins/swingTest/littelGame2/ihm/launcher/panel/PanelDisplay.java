package edwin.alkins.swingTest.littelGame2.ihm.launcher.panel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import edwin.alkins.swingTest.littelGame2.core.entity.Entity;
import edwin.alkins.swingTest.littelGame2.core.event.EventManager;

public class PanelDisplay extends JPanel implements MouseListener, KeyListener{

	private static final long serialVersionUID = -1345297621672577495L;
	private List<Entity> entities = new ArrayList<Entity>();
	
	public PanelDisplay() {
		this.addMouseListener(this);
		this.addKeyListener(this);
		setLayout(null);
		this.setFocusable(true);
		this.setIgnoreRepaint(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(Entity entity:entities) {
			entity.draw((Graphics2D)g);
		}
	}

	public void setEntity(List<Entity> entities) {
		this.entities = entities;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {
		EventManager.getInstance().setMouseEventPress(e);
	}
	public void mouseReleased(MouseEvent e) {}
	public void keyPressed(KeyEvent e) {
		EventManager.getInstance().setKeyEvent(e);
	}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}