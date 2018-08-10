package edwin.alkins.swingTest.littelGame2.ihm.panel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import edwin.alkins.swingTest.littelGame2.core.entity.Entity;
import edwin.alkins.swingTest.littelGame2.core.event.EventManager;
import edwin.alkins.swingTest.littelGame2.core.world.World;

public class PanelDisplay extends JPanel implements MouseListener, KeyListener{

	private static final long serialVersionUID = -1345297621672577495L;
	private World world;
	private BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
	
	public PanelDisplay(World world) {
		this.world = world;
		this.addMouseListener(this);
		this.addKeyListener(this);
		setLayout(null);
		this.setFocusable(true);
		this.setIgnoreRepaint(true);
	}
	
	/*@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}*/

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

	public void render() {
		this.image = getImageRenderer(world.getListOfEntities());
		//repaint();
		getGraphics().drawImage(image, 0, 0, null);
	}
	public BufferedImage getImageRenderer(List<Entity> entities) {
		BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2D = img.createGraphics();
		for(Entity entity:entities) {
			entity.draw(g2D);			
		}
		return img;
	}
}