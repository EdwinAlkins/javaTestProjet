package edwin.alkins.swingTest.littelGame2.core.entity;

import java.awt.geom.Rectangle2D;

import edwin.alkins.swingTest.littelGame2.core.entity.model.EntityModel;
import edwin.alkins.swingTest.littelGame2.core.entity.model.Model;
import edwin.alkins.swingTest.littelGame2.core.entity.shape.PolygonFill;
import edwin.alkins.swingTest.littelGame2.core.inventory.Inventory;

public class DefaultEntity extends Entity {

	protected Inventory inventory;
	public static int id = -1;

	public DefaultEntity() {
		super(id);
		this.inventory = new Inventory(10);
	}

	@SuppressWarnings("deprecation")
	public static void initialize() {
		if(id == -1) {
			Rectangle2D.Double bounds = new Rectangle2D.Double(0, 0, 34d, 34d);
			Model m = new Model(bounds);

			m.addPolygon(new PolygonFill(new int[] {0,2,4,30,32,34,15},new int[] {0,30,34,30,34,15},6));
			
			m.render();
			m.updateShape();
			EntityModel instance = EntityModel.getInstance();
			id = instance.getNewId();
			instance.linkModel(id, m);
		}
	}
	
	@Override
	public void update(long timePass) {
	}
}
