package edwin.alkins.swingTest.littelGame2.ihm.visualiser;

import javax.swing.JWindow;

import edwin.alkins.swingTest.littelGame2.core.event.EventManager;
import edwin.alkins.swingTest.littelGame2.core.world.World;
import edwin.alkins.swingTest.littelGame2.ihm.Windows.FrameOfGameV1;
import edwin.alkins.swingTest.littelGame2.ihm.camera.Camera;

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
