package edwin.alkins.swingTest.littelGame2.core.entity.shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D.Double;

public class Ellipse2DFill extends Double {

	private static final long serialVersionUID = -4429775056336142974L;
	private Color fillColor = Color.black;
	private Color borderColor = Color.black;
	private boolean isDrawnFill = true;
	private boolean isDrawnBorder = true;

	public Ellipse2DFill() {
		super();
	}
	
	public Ellipse2DFill(double x, double y, double w, double h) {
		super(x,y,w,h);
	}
	
	public Color getFillColor() {
		return fillColor;
	}

	public Ellipse2DFill setFillColor(Color fillColor) {
		this.fillColor = fillColor;
		return this;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public Ellipse2DFill setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
		return this;
	}

	public boolean isDrawnBorder() {
		return isDrawnBorder;
	}

	public Ellipse2DFill setDrawnBorder(boolean isDrawnBorder) {
		this.isDrawnBorder = isDrawnBorder;
		return this;
	}

	public boolean isDrawnFill() {
		return isDrawnFill;
	}

	public Ellipse2DFill setDrawnFill(boolean isDrawnFill) {
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
