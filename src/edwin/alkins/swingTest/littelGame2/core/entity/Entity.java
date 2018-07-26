package edwin.alkins.swingTest.littelGame2.core.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edwin.alkins.swingTest.littelGame2.core.entity.shape.Arc2DFill;
import edwin.alkins.swingTest.littelGame2.core.entity.shape.Ellipse2DFill;
import edwin.alkins.swingTest.littelGame2.core.entity.shape.Line2DFill;
import edwin.alkins.swingTest.littelGame2.core.entity.shape.PolygonFill;
import edwin.alkins.swingTest.littelGame2.core.entity.shape.Rectangle2DFill;

public abstract class Entity {

	protected Rectangle2D bounds;
	protected List<Rectangle2DFill> rectangles = new ArrayList<>();
	protected List<Arc2DFill> arcs = new ArrayList<>();
	protected List<Line2DFill> lines = new ArrayList<>();
	protected List<PolygonFill> polygons = new ArrayList<>();
	protected List<Ellipse2DFill> ellipses = new ArrayList<>();
	protected double angle = 0d;
	protected double size = 1d;
	
	public Entity(Rectangle2D bounds) {
		this.bounds = bounds;
	}
	
	public abstract void initialize();
	public void setLocation(Point2D location) {
		this.bounds = new Rectangle2D.Double(location.getX(), location.getY(), bounds.getWidth(), bounds.getHeight());
	}
	public void setAngle(double a) {
		this.angle = a;
	}
	public Point2D getLocation() {
		return new Point2D.Double(this.bounds.getX(),this.bounds.getY());
	}
	public void setSize(double s) {
		this.size = s;
	}
	public Rectangle2D getBounds() {
		return bounds;
	}
	public double getAngle() {
		return angle;
	}
	public double getSize() {
		return size;
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
		BufferedImage imgEntity = new BufferedImage((int)Math.round(bounds.getWidth()), (int)Math.round(bounds.getHeight()), BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = imgEntity.createGraphics();
		paintEntity(g);
		g.dispose();
		return g;
	};
	
	private void paintEntity(Graphics2D g) {
		for(Rectangle2DFill rectangle:rectangles) {
			//g.draw(rectangle);
			rectangle.draw(g);
		}
		for(Arc2DFill arc:arcs) {
			//g.draw(arc);
			arc.draw(g);
		}
		for(Line2DFill line:lines) {
			//g.draw(line);
			line.draw(g);
		}
		for(Ellipse2DFill ellipse:ellipses) {
			//g.draw(ellipse);
			ellipse.draw(g);
		}
		for(PolygonFill polygon:polygons) {
			//g.drawPolygon(polygon);
			polygon.draw(g);
		}
	};
	
	public void draw(Graphics2D g) {
		Graphics2D gEntity = (Graphics2D) g.create();
		gEntity.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gEntity.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		gEntity.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		gEntity.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE);
		gEntity.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
		gEntity.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		AffineTransform at = new AffineTransform();
		at.translate(bounds.getX(), bounds.getY());
		at.rotate(Math.toRadians(angle), bounds.getWidth()/2d, bounds.getHeight()/2d);
		at.scale(size, size);
		gEntity.setTransform(at);
		paintEntity(gEntity);
		gEntity.dispose();
	}
}