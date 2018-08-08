package edwin.alkins.swingTest.littelGame2.core.entity.shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D.Double;

public class Rectangle2DFill extends Double {

	private static final long serialVersionUID = -3342702345194952811L;
	private Color fillColor = Color.black;
	private Color borderColor = Color.black;
	private boolean isDrawnFill = true;
	private boolean isDrawnBorder = true;

	public Rectangle2DFill() {
		super();
	}
	
	public Rectangle2DFill(double x, double y, double w, double h)  {
		super(x,y,w,h);
	}
	
	public Color getFillColor() {
		return fillColor;
	}

	public Rectangle2DFill setFillColor(Color fillColor) {
		this.fillColor = fillColor;
		return this;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public Rectangle2DFill setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
		return this;
	}

	public boolean isDrawnBorder() {
		return isDrawnBorder;
	}

	public Rectangle2DFill setDrawnBorder(boolean isDrawnBorder) {
		this.isDrawnBorder = isDrawnBorder;
		return this;
	}

	public boolean isDrawnFill() {
		return isDrawnFill;
	}

	public Rectangle2DFill setDrawnFill(boolean isDrawnFill) {
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
