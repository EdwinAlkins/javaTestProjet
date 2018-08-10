package edwin.alkins.swingTest.littelGame2.core.entity.model;

import java.awt.geom.Rectangle2D;
import java.util.LinkedHashMap;
import java.util.Map;

public class EntityModel {
	private static EntityModel instance = null;
	private Map<Integer,Model> resources;
	private static int id_increment = 0;
	
	private EntityModel() {
		this.resources = new LinkedHashMap<>();
	}
	
	public static EntityModel getInstance() {
		if (instance == null)
			instance = new EntityModel();
		return instance;
	}
	
	public Model getModel(int id) {
		return this.resources.getOrDefault(id, new Model(new Rectangle2D.Double(0d,0d,1d,1d)));
	}
	public void linkModel(int id, Model model) {
		this.resources.put(id, model);
	}
	public synchronized int getNewId() {
		return id_increment++;
	}
}
