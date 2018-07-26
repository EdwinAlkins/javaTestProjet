package edwin.alkins.swingTest.littelGame2.core.entity.shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;

public class Arc2DFill extends Arc2D.Double {

	private static final long serialVersionUID = -4435429241779220600L;
	private Color fillColor = Color.black;
	private Color borderColor = Color.black;
	private boolean isDrawnFill = true;
	private boolean isDrawnBorder = true;

	public Arc2DFill() {
		super();
	}
	
	public Arc2DFill(int type) {
		super(type);
	}
	
	public Arc2DFill(double x, double y, double w, double h, double start, double extent, int type)  {
		super(x,y,w,h,start,extent,type);
	}
	
	public Arc2DFill(Rectangle2D ellipseBounds, double start, double extent, int type) {
		super(ellipseBounds,start,extent,type);
	}

	public Color getFillColor() {
		return fillColor;
	}

	public Arc2DFill setFillColor(Color fillColor) {
		this.fillColor = fillColor;
		return this;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public Arc2DFill setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
		return this;
	}

	public boolean isDrawnBorder() {
		return isDrawnBorder;
	}

	public Arc2DFill setDrawnBorder(boolean isDrawnBorder) {
		this.isDrawnBorder = isDrawnBorder;
		return this;
	}

	public boolean isDrawnFill() {
		return isDrawnFill;
	}

	public void setDrawnFill(boolean isDrawnFill) {
		this.isDrawnFill = isDrawnFill;
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
