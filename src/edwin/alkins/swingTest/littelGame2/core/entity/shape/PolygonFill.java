package edwin.alkins.swingTest.littelGame2.core.entity.shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class PolygonFill extends Polygon {

	private static final long serialVersionUID = 2710243414839852125L;
	private Color fillColor = Color.black;
	private Color borderColor = Color.black;
	private boolean isDrawnFill = true;
	private boolean isDrawnBorder = true;

	public PolygonFill() {
		super();
	}
	
	public PolygonFill(int[] xpoints, int[] ypoints, int npoints)  {
		super(xpoints,ypoints,npoints) ;
	}
	
	public Color getFillColor() {
		return fillColor;
	}

	public PolygonFill setFillColor(Color fillColor) {
		this.fillColor = fillColor;
		return this;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public PolygonFill setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
		return this;
	}

	public boolean isDrawnBorder() {
		return isDrawnBorder;
	}

	public PolygonFill setDrawnBorder(boolean isDrawnBorder) {
		this.isDrawnBorder = isDrawnBorder;
		return this;
	}

	public boolean isDrawnFill() {
		return isDrawnFill;
	}

	public PolygonFill setDrawnFill(boolean isDrawnFill) {
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
