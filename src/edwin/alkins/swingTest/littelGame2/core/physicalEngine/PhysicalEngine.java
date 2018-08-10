package edwin.alkins.swingTest.littelGame2.core.physicalEngine;

import edwin.alkins.swingTest.littelGame2.core.world.World;

public class PhysicalEngine {
	
	private World world;

	public void update(long timePass) {
		this.world.getListOfEntities().stream().forEach(e -> {
			e.update(timePass);
		});
	}

	public void setWorld(World world) {
		this.world = world;
	}
	public World getWorld() {
		return this.world;
	}
}
