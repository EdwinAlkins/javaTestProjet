package edwin.alkins.swingTest.littelGame2.core.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edwin.alkins.swingTest.littelGame2.core.entity.Entity;

public class World {

	private List<Entity> entities = new ArrayList<>();
	
	public void addEntity(Entity entity) {
		this.entities.add(entity);
	}
	public List<Entity> getListOfEntities(){
		return Collections.unmodifiableList(this.entities);
	}
}
