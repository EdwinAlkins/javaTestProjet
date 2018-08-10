package edwin.alkins.swingTest.littelGame2.core.entity;

import java.awt.geom.Rectangle2D;

import edwin.alkins.swingTest.littelGame2.core.entity.model.EntityModel;
import edwin.alkins.swingTest.littelGame2.core.entity.model.Model;
import edwin.alkins.swingTest.littelGame2.core.entity.shape.Ellipse2DFill;
import edwin.alkins.swingTest.littelGame2.core.entity.shape.Rectangle2DFill;
import edwin.alkins.swingTest.littelGame2.core.inventory.Inventory;

public class DefaultEntity extends Entity {

	protected Inventory inventory;
	public static int id = -1;

	public DefaultEntity() {
		super(id);
		this.inventory = new Inventory(10);
	}

	public static void initialize() {
		if(id == -1) {
			Rectangle2D.Double bounds = new Rectangle2D.Double(0, 0, 32d, 26d);
			Model m = new Model(bounds);
			m.addRectangle2D(new Rectangle2DFill(0, 0, bounds.getWidth()/2d, bounds.getHeight()/2d));

			m.addEllipse2D(new Ellipse2DFill(0, 0, bounds.getWidth()/2d, bounds.getHeight()/2d));
			m.render();
			EntityModel instance = EntityModel.getInstance();
			id = instance.getNewId();
			instance.linkModel(id, m);
		}
	}
	
	@Override
	public void update(long timePass) {
	}
}
