package edwin.alkins.swingTest.littelGame2.ihm.launcher.panel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.swing.JPanel;

import edwin.alkins.swingTest.littelGame2.core.entity.Entity;

public class PanelDisplay extends JPanel implements MouseListener{

	private static final long serialVersionUID = -1345297621672577495L;
	private List<Entity> entities = new ArrayList<Entity>();
	
	public PanelDisplay() {
		this.addMouseListener(this);
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
		this.entities.stream().forEach(entity -> {if(entity.isContaine(e.getPoint())) System.out.println("lol");});
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
}