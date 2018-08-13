package edwin.alkins.swingTest.littelGame2.core.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import edwin.alkins.swingTest.littelGame2.core.action.AbstractAction;
import edwin.alkins.swingTest.littelGame2.core.action.DefaultActionEntity;
import edwin.alkins.swingTest.littelGame2.core.entity.model.EntityModel;
import edwin.alkins.swingTest.littelGame2.core.entity.model.Model;

public abstract class Entity{

	protected double angle = 0d;
	protected double scale = 1d;
	protected AbstractAction action = new DefaultActionEntity();
	protected final int id_model;
	protected Model model;
	private Rectangle2D originalBounds;
	
	public Entity(int id_model) {
		this.id_model = id_model;
		this.model = EntityModel.getInstance().getModel(id_model);
		this.originalBounds = this.model.getBounds();
	}
	public void setAngle(double a) {
		this.angle = a;
	}
	public void setScale(double s) {
		this.scale = s;
	}
	public Rectangle2D getBounds() {
		return this.originalBounds;
	}
	public double getAngle() {
		return angle;
	}
	public double getScale() {
		return scale;
	}
	public Point2D getLocation() {
		return new Point2D.Double(this.originalBounds.getX(), this.originalBounds.getY());
	}
	public void setLocation(Point2D p) {
		this.originalBounds = new Rectangle2D.Double(p.getX(),p.getY(),this.originalBounds.getWidth(),this.originalBounds.getHeight());
	}
	public Point2D getLocationCenter() {
		double widthScaled = (this.originalBounds.getWidth()/2d)*scale;
		double heightScaled = (this.originalBounds.getHeight()/2d)*scale;
		return new Point2D.Double(this.originalBounds.getX()+widthScaled, this.originalBounds.getY()+heightScaled);
	}
	public void setLocationCenter(Point2D p) {
		double widthScaled = (this.originalBounds.getWidth()/2d)*scale;
		double heightScaled = (this.originalBounds.getHeight()/2d)*scale;
		this.originalBounds = new Rectangle2D.Double(p.getX()-widthScaled,p.getY()-heightScaled,this.originalBounds.getWidth(),this.originalBounds.getHeight());
	}
	public void draw(Graphics2D g) {
		Graphics2D gEntity = (Graphics2D) g.create();
		gEntity.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gEntity.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		gEntity.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		gEntity.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE);
		gEntity.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
		gEntity.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		AffineTransform at = getAffineTransform();
		/*gEntity.setColor(Color.BLACK);
		gEntity.draw(this.model.getShape(at));*/
		gEntity.drawRenderedImage(this.model.getImage(), at);
		gEntity.dispose();
	}
	public AffineTransform getAffineTransform() {
		AffineTransform at = new AffineTransform();
		at.translate(getBounds().getX(), getBounds().getY());
		at.rotate(Math.toRadians(angle), getBounds().getWidth()/2d*scale, getBounds().getHeight()/2d*scale);
		at.scale(scale, scale);
		return at;
	}
	
	public boolean isContaine(Point p) {
		return this.model.getShape(getAffineTransform()).contains(p);
	}
	public abstract void update(long timePass);
	public AbstractAction getAction() {
		return this.action;
	}
}