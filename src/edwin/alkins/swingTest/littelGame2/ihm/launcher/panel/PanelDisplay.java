package edwin.alkins.swingTest.littelGame2.ihm.launcher.panel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import edwin.alkins.swingTest.littelGame2.core.entity.Entity;

public class PanelDisplay extends JPanel{

	private static final long serialVersionUID = -1345297621672577495L;
	private List<Entity> entities = new ArrayList<>();
	
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
}
