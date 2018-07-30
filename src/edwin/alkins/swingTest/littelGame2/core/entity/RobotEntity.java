package edwin.alkins.swingTest.littelGame2.core.entity;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import edwin.alkins.swingTest.littelGame2.core.entity.shape.Ellipse2DFill;
import edwin.alkins.swingTest.littelGame2.core.entity.shape.Rectangle2DFill;
import edwin.alkins.swingTest.littelGame2.core.inventory.Inventory;

public class RobotEntity extends Entity{

	protected Inventory inventory;
	
	public RobotEntity() {
		super(new Rectangle2D.Double(0,0,32d,26d));
		initialize();
	}

	@Override
	public void initialize() {
		super.rectangles.add(new Rectangle2DFill(2d, 0d, 2d, 10d).setDrawnBorder(false).setFillColor(Color.black));
		super.rectangles.add(new Rectangle2DFill(0d, 10d, 6d, 12d).setDrawnBorder(false).setFillColor(Color.red));
		super.rectangles.add(new Rectangle2DFill(6d, 6d, 20d, 20d).setDrawnBorder(false).setFillColor(Color.blue));
		super.rectangles.add(new Rectangle2DFill(14d, 14d, 4d, 12d).setDrawnBorder(false).setFillColor(Color.red));
		super.rectangles.add(new Rectangle2DFill(26d, 10d, 6d, 12d).setDrawnBorder(false).setFillColor(Color.red));
		super.rectangles.add(new Rectangle2DFill(28d, 0d, 2d, 10d).setDrawnBorder(false).setFillColor(Color.black));
		
		super.ellipses.add(new Ellipse2DFill(10d, 8d, 4d, 4d).setDrawnBorder(false).setFillColor(Color.green));
		super.ellipses.add(new Ellipse2DFill(18d, 8d, 4d, 4d).setDrawnBorder(false).setFillColor(Color.green));
		this.inventory = new Inventory(10);
	}

	@Override
	public void update() {
		
	}
}
