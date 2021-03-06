package edwin.alkins.swingTest.littelGame2.ihm.camera;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.function.Consumer;

import edwin.alkins.swingTest.littelGame2.core.entity.Entity;
import edwin.alkins.swingTest.littelGame2.core.game.GameLoop;
import edwin.alkins.swingTest.littelGame2.core.world.World;

public class Camera {
	
	private double x = 0;
	private double y = 0;
	private double width = 1;
	private double height = 1;
	private BufferedImage image;
	
	public Camera(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		initImage();
	}
	public void setLocation(Point2D p) {
		this.x = p.getX();
		this.y = p.getY();
	}
	public void initImage() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsConfiguration gc = ge.getDefaultScreenDevice().getDefaultConfiguration();
		this.image= gc.createCompatibleImage((int)Math.round(this.width), (int)Math.round(this.height), Transparency.TRANSLUCENT);
		this.image.setAccelerationPriority(1);
	}
	public void setBounds(double width, double height) {
		this.width = width;
		this.height = height;
		initImage();
	}
	/**
	 * @deprecated slower
	 * @param w
	 * @return
	 */
	public BufferedImage getImageRenderer(World w) {
		Graphics2D g2D = image.createGraphics();
		g2D.setBackground(Color.WHITE);
		g2D.clearRect(0, 0, (int) Math.round(this.width), (int) Math.round(this.height));
		Point2D location = getLocation();
		Consumer<Entity> consumer = new Consumer<Entity>() {
			public void accept(Entity entity) {
				entity.draw(g2D,location,1d,1d);
			}
		};
		w.getListOfEntities(this.x,this.y,this.width,this.height).stream().forEach(consumer);
		g2D.dispose();
		return image;
	}
	public BufferedImage getImageRenderer(World w, Dimension dimension) {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsConfiguration gc = ge.getDefaultScreenDevice().getDefaultConfiguration();
		BufferedImage buffimage = gc.createCompatibleImage((int)Math.round((dimension.getWidth()<=0)?1:dimension.getWidth()),(int)Math.round((dimension.getHeight()<=0)?1:dimension.getHeight()));
		buffimage.setAccelerationPriority(1);
		Graphics2D g2D = (Graphics2D) buffimage.createGraphics();
		g2D.setBackground(Color.WHITE);
		g2D.clearRect(0, 0, (int) Math.round(dimension.getWidth()), (int) Math.round(dimension.getHeight()));
		double scaledW = dimension.getWidth()/this.getWidth();
		double scaledH = dimension.getHeight()/this.getHeight();
		Point2D location = getLocation();
		
		Graphics2D entitiesGraphs = (Graphics2D) g2D.create();
		Consumer<Entity> consumer = new Consumer<Entity>() {
			public void accept(Entity entity) {
				entity.draw(entitiesGraphs,location,scaledW,scaledH);
			}
		};
		w.getListOfEntities(this.x,this.y,this.width,this.height).stream().forEach(consumer);
		entitiesGraphs.dispose();
		
		String strFps = "fps:"+GameLoop.fps;
		Rectangle2D r = g2D.getFontMetrics().getStringBounds(strFps, g2D);
		g2D.setColor(Color.BLACK);
		g2D.drawString(strFps, (float)(dimension.getWidth()-r.getWidth()), (float)(dimension.getHeight()-g2D.getFontMetrics().getDescent()));
		g2D.dispose();
		return buffimage;
	}
	public void getDrawRenderer(World w, Graphics2D g2D,Dimension dimension) {
		g2D.setBackground(Color.WHITE);
		g2D.clearRect(0, 0, (int) Math.round(dimension.getWidth()), (int) Math.round(dimension.getHeight()));
		double scaledW = dimension.getWidth()/this.getWidth();
		double scaledH = dimension.getHeight()/this.getHeight();
		Point2D location = getLocation();
		
		Graphics2D entitiesGraphs = (Graphics2D) g2D.create();
		Consumer<Entity> consumer = new Consumer<Entity>() {
			public void accept(Entity entity) {
				entity.draw(entitiesGraphs,location,scaledW,scaledH);
			}
		};
		w.getListOfEntities(this.x,this.y,this.width,this.height).stream().forEach(consumer);
		entitiesGraphs.dispose();
		
		g2D.dispose();
	}
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
	public double getWidth() {
		return this.width;
	}
	public double getHeight() {
		return this.height;
	}
	public Point2D getLocation() {
		return new Point2D.Double(this.x, this.y);
	}
}
