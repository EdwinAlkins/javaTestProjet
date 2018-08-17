package edwin.alkins.swingTest.littelGame2.core.entity.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
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
import edwin.alkins.swingTest.littelGame2.core.util.GlobalProperty;

public class Model {

	protected List<Rectangle2DFill> rectangles = new ArrayList<>();
	protected List<Arc2DFill> arcs = new ArrayList<>();
	protected List<Line2DFill> lines = new ArrayList<>();
	protected List<PolygonFill> polygons = new ArrayList<>();
	protected List<Ellipse2DFill> ellipses = new ArrayList<>();
	protected Rectangle2D originalBounds;
	private BufferedImage image;
	private Shape shape;
	
	public Model(Rectangle2D bounds) {
		this.originalBounds = bounds;
		updateShape();
		render();
	}
	public Rectangle2D getBounds() {
		return this.originalBounds;
	}
	public void addRectangle2D(Rectangle2DFill r) {
		this.rectangles.add(r);
	}
	public void addArc2D(Arc2DFill a) {
		this.arcs.add(a);
	}
	public void addLine2D(Line2DFill l) {
		this.lines.add(l);
	}
	public void addPolygon(PolygonFill p) {
		this.polygons.add(p);
	}
	public void addEllipse2D(Ellipse2DFill e) {
		this.ellipses.add(e);
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
	public void updateShape() {
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
		this.shape = shape;
	}
	/**
	 * @deprecated slower
	 */
	public void render() {
		this.image = new BufferedImage((int)this.originalBounds.getWidth(), (int)this.originalBounds.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D gEntity = (Graphics2D) image.createGraphics();
		paintEntity(gEntity);
		gEntity.dispose();
	}
	public void render(Graphics2D gEntity, AffineTransform at) {
		if(GlobalProperty.getInstance().performance == 0) {
			gEntity.setColor(Color.BLACK);
			gEntity.draw(getShape(at));
		} else {
			gEntity.setTransform(at);
			paintEntity(gEntity);
		}
	}
	public Shape getShape(AffineTransform t) {
		return t.createTransformedShape(this.shape);
	}
	public Shape getShape() {
		return this.shape;
	}
	public BufferedImage getImage() {
		return this.image;
	}
	public BufferedImage makeImage(Shape s) {
	    Rectangle r = s.getBounds();
	    BufferedImage image = new BufferedImage(r.width, r.height, BufferedImage.TYPE_3BYTE_BGR);
	    Graphics2D gr = image.createGraphics();
	    // move the shape in the region of the image
	    gr.translate(-r.x, -r.y);
	    gr.setColor(Color.BLACK);
	    gr.draw(s);
	    gr.dispose();
	    return image;
	}
}
