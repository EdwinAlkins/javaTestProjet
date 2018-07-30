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
import java.util.stream.Stream;

import javax.swing.JPanel;

import edwin.alkins.swingTest.littelGame2.core.entity.Entity;
import edwin.alkins.swingTest.littelGame2.ihm.launcher.vue.inventory.InternalFrameInventory;

public class PanelDisplay extends JPanel implements MouseListener, KeyListener{

	private static final long serialVersionUID = -1345297621672577495L;
	private List<Entity> entities = new ArrayList<Entity>();
	
	public PanelDisplay() {
		this.addMouseListener(this);
		this.addKeyListener(this);
		setLayout(null);
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
		this.entities.stream().forEach(
			entity -> {
				if(entity.isContaine(e.getPoint())) {
					entity.update();
					InternalFrameInventory internalFrameInventory = new InternalFrameInventory();
					add(internalFrameInventory);
					internalFrameInventory.setVisible(true);
				}
			}
		);
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyChar());
		this.entities.stream().forEach(
			entity -> {
				Point2D mouve = new Point2D.Double();
				System.out.println(e.getKeyChar());
				if(e.getKeyCode() == KeyEvent.VK_0)
					mouve.setLocation(entity.getLocation().getX(),entity.getLocation().getY()-1d);
				if(e.getKeyCode() == KeyEvent.VK_UP)
					mouve.setLocation(entity.getLocation().getX(),entity.getLocation().getY()-0.5d);
				else if(e.getKeyCode() == KeyEvent.VK_DOWN)
					mouve.setLocation(entity.getLocation().getX(),entity.getLocation().getY()+0.5d);
				else if(e.getKeyCode() == KeyEvent.VK_LEFT)
					mouve.setLocation(entity.getLocation().getX()-0.5d,entity.getLocation().getY());
				else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
					mouve.setLocation(entity.getLocation().getX()+0.5d,entity.getLocation().getY());
				entity.setLocation(mouve);
			}
		);
	}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}