package edwin.alkins.swingTest.littelGame2.core.entity;

import java.awt.geom.Rectangle2D;

import edwin.alkins.swingTest.littelGame2.core.entity.shape.Ellipse2DFill;
import edwin.alkins.swingTest.littelGame2.core.entity.shape.Rectangle2DFill;

public class DefaultEntity extends Entity {

	public DefaultEntity(Rectangle2D bounds) {
		super(bounds);
		initialize();
	}

	public void initialize() {
		super.ellipses.add(new Ellipse2DFill(0, 0, originalBounds.getWidth()/2d, originalBounds.getHeight()/2d));
		super.rectangles.add(new Rectangle2DFill(0, 0, originalBounds.getWidth()/2d, originalBounds.getHeight()/2d));
	}

	@Override
	public void update() {
	}
}
