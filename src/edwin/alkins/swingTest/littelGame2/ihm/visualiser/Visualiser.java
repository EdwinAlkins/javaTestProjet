package edwin.alkins.swingTest.littelGame2.ihm.visualiser;

import edwin.alkins.swingTest.littelGame2.core.event.EventManager;
import edwin.alkins.swingTest.littelGame2.core.world.World;
import edwin.alkins.swingTest.littelGame2.ihm.camera.Camera;
import edwin.alkins.swingTest.littelGame2.ihm.launcher.FrameOfGameV1;

public class Visualiser {

	@SuppressWarnings("unused")
	private World world;
	private FrameOfGameV1 frame;
	private Camera camera;
	
	public Visualiser(World world) {
		this.world = world;
		this.camera = new Camera(0,0,1000,1000);
		EventManager.getInstance().setCamera(camera);
		this.frame = new FrameOfGameV1(world,camera);
		frame.setVisible(true);
	}
	
	public void render() {
		frame.render();
	}
}
