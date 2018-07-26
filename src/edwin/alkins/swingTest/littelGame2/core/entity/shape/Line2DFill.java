package edwin.alkins.swingTest.littelGame2.core.entity.shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Line2DFill extends Line2D.Double {

	private static final long serialVersionUID = 2242321316918145417L;
	private Color fillColor = Color.black;
	private Color borderColor = Color.black;
	private boolean isDrawnFill = true;
	private boolean isDrawnBorder = true;

	public Line2DFill() {
		super();
	}
	
	public Line2DFill(double x1, double y1, double x2, double y2) {
		super(x1,y1,x2,y2);
	} 
	
	public Line2DFill(Point2D p1, Point2D p2) {
		super(p1,p2);
	}
	
	public Color getFillColor() {
		return fillColor;
	}

	public Line2DFill setFillColor(Color fillColor) {
		this.fillColor = fillColor;
		return this;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public Line2DFill setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
		return this;
	}

	public boolean isDrawnBorder() {
		return isDrawnBorder;
	}

	public Line2DFill setDrawnBorder(boolean isDrawnBorder) {
		this.isDrawnBorder = isDrawnBorder;
		return this;
	}

	public boolean isDrawnFill() {
		return isDrawnFill;
	}

	public Line2DFill setDrawnFill(boolean isDrawnFill) {
		this.isDrawnFill = isDrawnFill;
		return this;
	}
	
	public void draw(Graphics2D g2d) {
		if(isDrawnBorder) {
			g2d.setColor(borderColor);
			g2d.draw(this);
		}
		if(isDrawnFill) {
			g2d.setColor(this.fillColor);
			g2d.fill(this);
		}
	}
}
