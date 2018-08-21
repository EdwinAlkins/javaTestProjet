package edwin.alkins.swingTest.littelGame2.ihm.panel;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import edwin.alkins.swingTest.littelGame2.core.event.EventManager;
import edwin.alkins.swingTest.littelGame2.core.world.World;
import edwin.alkins.swingTest.littelGame2.ihm.camera.Camera;

public class PanelDisplay extends JPanel implements MouseListener, KeyListener, MouseWheelListener{

	private static final long serialVersionUID = -1345297621672577495L;
	private World world;
	private BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
	private Camera camera;
	
	public PanelDisplay(World world, Camera camera) {
		this.world = world;
		this.camera = camera;
		this.addMouseListener(this);
		this.addKeyListener(this);
		this.addMouseWheelListener(this);
		setLayout(null);
		this.setFocusable(true);
		this.setIgnoreRepaint(true);
		this.setDoubleBuffered(true);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {
		int button = e.getButton();
		boolean isControlDown = e.isControlDown();
		Point position = e.getPoint();
		int x = (int)Math.round((double)position.x*((double)this.camera.getWidth()/(double)getWidth())+this.camera.getX());
		int y = (int)Math.round((double)position.y*((double)this.camera.getHeight()/(double)getHeight())+this.camera.getY());
		position = new Point(x, y);
		EventManager.getInstance().setMouseEventPress(button,isControlDown,position);
	}
	public void mouseReleased(MouseEvent e) {}
	public void keyPressed(KeyEvent e) {
		EventManager.getInstance().setKeyEvent(e);
	}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		double w = this.camera.getWidth() + this.camera.getWidth()/100*e.getPreciseWheelRotation();
		double h = this.camera.getHeight() + this.camera.getHeight()/100*e.getPreciseWheelRotation();
		this.camera.setBounds(w, h);
	}

	public void render() {
		this.image = camera.getImageRenderer(world, getSize());
		getGraphics().drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}
}