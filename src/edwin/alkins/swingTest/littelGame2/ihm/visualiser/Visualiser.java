package edwin.alkins.swingTest.littelGame2.ihm.visualiser;

import edwin.alkins.swingTest.littelGame2.core.world.World;
import edwin.alkins.swingTest.littelGame2.ihm.launcher.FrameOfGameV1;

public class Visualiser {

	@SuppressWarnings("unused")
	private World world;
	private FrameOfGameV1 frame;
	
	public Visualiser(World world) {
		this.world = world;
		this.frame = new FrameOfGameV1(world);
		frame.setVisible(true);
	}
	
	public void render() {
		frame.render();
	}
}
