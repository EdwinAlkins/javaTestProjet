package edwin.alkins.swingTest.littelGame2.core.world;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;


import edwin.alkins.swingTest.littelGame2.core.entity.Entity;

public class World {

	private List<Entity> entities = new ArrayList<>();
	
	public void addEntity(Entity entity) {
		this.entities.add(entity);
	}
	public List<Entity> getListOfEntities(){
		return Collections.unmodifiableList(this.entities);
	}
	public List<Entity> getListOfEntities(double x, double y, double width, double height) {
		List<Entity> entitiesInBounds = new ArrayList<>();
		Rectangle2D r = new Rectangle2D.Double(x, y, width, height);
		Consumer<Entity> action = new Consumer<Entity>() {
			public void accept(Entity t) {
				if(t.getShape().intersects(r))
					entitiesInBounds.add(t);
			}
		};
		this.entities.stream().forEach(action);
		return Collections.unmodifiableList(entitiesInBounds);
	}
}
