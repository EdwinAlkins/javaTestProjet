package edwin.alkins.swingTest.littelGame2.core.entity;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edwin.alkins.swingTest.littelGame2.core.action.AbstractAction;
import edwin.alkins.swingTest.littelGame2.core.action.DefaultActionEntity;
import edwin.alkins.swingTest.littelGame2.core.entity.shape.Arc2DFill;
import edwin.alkins.swingTest.littelGame2.core.entity.shape.Ellipse2DFill;
import edwin.alkins.swingTest.littelGame2.core.entity.shape.Line2DFill;
import edwin.alkins.swingTest.littelGame2.core.entity.shape.PolygonFill;
import edwin.alkins.swingTest.littelGame2.core.entity.shape.Rectangle2DFill;

public abstract class Entity{

	protected List<Rectangle2DFill> rectangles = new ArrayList<>();
	protected List<Arc2DFill> arcs = new ArrayList<>();
	protected List<Line2DFill> lines = new ArrayList<>();
	protected List<PolygonFill> polygons = new ArrayList<>();
	protected List<Ellipse2DFill> ellipses = new ArrayList<>();
	protected double angle = 0d;
	protected double scale = 1d;
	protected Rectangle2D originalBounds;
	protected AbstractAction action = new DefaultActionEntity();
	
	public Entity(Rectangle2D bounds) {
		this.originalBounds = bounds;
		setBounds(bounds.getBounds());
	}
	public abstract void initialize();
	public void setAngle(double a) {
		this.angle = a;
	}
	public void setScale(double s) {
		this.scale = s;
	}
	public void setBounds(Rectangle2D bounds) {
		this.originalBounds = bounds;
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
	public List<Rectangle2DFill> getRectangles() {
        return Collections.unmodifiableList(rectangles);
    }
	public List<Arc2DFill> getArcs() {
        return Collections.unmodifiableList(arcs);
    }
	public List<Line2DFill> getLines() {
        return Collections.unmodifiableList(lines);
    }
	public List<PolygonFill> getPolygon() {
        return Collections.unmodifiableList(polygons);
    }
	public List<Ellipse2DFill> getEllipses() {
        return Collections.unmodifiableList(ellipses);
    }
	public Graphics2D getGraphics() {
		BufferedImage imgEntity = new BufferedImage((int)Math.round(getBounds().getWidth()), (int)Math.round(getBounds().getHeight()), BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = imgEntity.createGraphics();
		paintEntity(g);
		g.dispose();
		return g;
	};
	
	private void paintEntity(Graphics2D g) {
		for(Rectangle2DFill rectangle:rectangles) {
			rectangle.draw(g);
		}
		for(Arc2DFill arc:arcs) {
			arc.draw(g);
		}
		for(Line2DFill line:lines) {
			line.draw(g);
		}
		for(Ellipse2DFill ellipse:ellipses) {
			ellipse.draw(g);
		}
		for(PolygonFill polygon:polygons) {
			polygon.draw(g);
		}
	};
	
	private Shape creatShape() {
		Area shape = new Area();
		for(Rectangle2DFill rectangle:rectangles) {
			shape.add(new Area(rectangle));
		}
		for(Arc2DFill arc:arcs) {
			shape.add(new Area(arc));
		}
		for(Line2DFill line:lines) {
			shape.add(new Area(line));
		}
		for(Ellipse2DFill ellipse:ellipses) {
			shape.add(new Area(ellipse));
		}
		for(PolygonFill polygon:polygons) {
			shape.add(new Area(polygon));
		}
		return shape;
	}
	
	public void draw(Graphics2D g) {
		Graphics2D gEntity = (Graphics2D) g.create();
		gEntity.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gEntity.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		gEntity.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		gEntity.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE);
		gEntity.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
		gEntity.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		AffineTransform at = new AffineTransform();
		at.translate(getBounds().getX(), getBounds().getY());
		at.rotate(Math.toRadians(angle), getBounds().getWidth()/2d*scale, getBounds().getHeight()/2d*scale);
		at.scale(scale, scale);
		gEntity.setTransform(at);
		paintEntity(gEntity);
		gEntity.dispose();
	}
	
	public boolean isContaine(Point p) {
		AffineTransform t = new AffineTransform();
		t.translate(getBounds().getX(), getBounds().getY());
		t.rotate(Math.toRadians(angle), getBounds().getWidth()/2d*scale, getBounds().getHeight()/2d*scale);
		t.scale(scale, scale);
		return t.createTransformedShape(creatShape()).contains(p);
	}
	public abstract void update(long timePass);
	public AbstractAction getAction() {
		return this.action;
	}
}