package edwin.alkins.swingTest.littelGame2.core.entity;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
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
	public AffineTransform getAffineTransform() {
		return getAffineTransform(new Point2D.Double(0d, 0d),1d,1d);
	}
	public void draw(Graphics2D gEntity, Point2D positionCam,double scaledW, double scaledH) {
		AffineTransform at = getAffineTransform(positionCam,scaledW,scaledH);
		this.model.render(gEntity, at);
	}
	public AffineTransform getAffineTransform(Point2D positionCam, double scaledW, double scaledH) {
		AffineTransform at = new AffineTransform();
		at.translate((getBounds().getX()-positionCam.getX())*scaledW, (getBounds().getY()-positionCam.getY())*scaledH);
		at.rotate(Math.toRadians(angle), getBounds().getWidth()/2d*scale*scaledW, getBounds().getHeight()/2d*scale*scaledH);
		at.scale(scale*scaledW, scale*scaledH);
		return at;
	}
	public boolean isContaine(Point p) {
		return this.model.getShape(getAffineTransform()).contains(p);
	}
	public Shape getShape() {
		return this.model.getShape(getAffineTransform());
	}
	public abstract void update(long timePass);
	public AbstractAction getAction() {
		return this.action;
	}
}