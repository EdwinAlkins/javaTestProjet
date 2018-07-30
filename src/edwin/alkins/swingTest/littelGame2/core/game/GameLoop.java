package edwin.alkins.swingTest.littelGame2.core.game;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;

import edwin.alkins.swingTest.littelGame2.core.entity.Entity;
import edwin.alkins.swingTest.littelGame2.core.entity.RobotEntity;
import edwin.alkins.swingTest.littelGame2.core.util.Tools;
import edwin.alkins.swingTest.littelGame2.ihm.launcher.FrameOfGameV1;

public class GameLoop implements Runnable {

        private boolean keepRunning = true;
        private List<Entity> entities = new ArrayList<>();
		private FrameOfGameV1 frame;
		private int fps;
		
		public GameLoop() {
		}
		
		public void preload() {
			frame = new FrameOfGameV1();
			frame.setVisible(true);
			int size = 100;
			for (int i = 0; i < size; i++) {
				double r1 = Tools.randome(0d, frame.getBounds().getWidth());
				double r2 = Tools.randome(0d, frame.getBounds().getHeight());
				double a = Tools.randome(0d, 360d);
				double s = Tools.randome(0.5d, 2d);
				RobotEntity robot = new RobotEntity();
				robot.setLocation(new Point2D.Double(r1,r2));
				robot.setAngle(a);
				robot.setScale(s);
				entities.add(robot);
			}
		}

		public void processInput() {
		}

		public void update() {
			entities.stream().forEach(e -> {
				e.setAngle(e.getAngle()+0.1d);
				e.setLocation(new Point2D.Double(e.getLocation().getX()+Tools.randome(-0.5d, 0.5d),e.getLocation().getY()+Tools.randome(-0.5d, 0.5d)));
			});
			frame.getPanelDisplayArea().setEntity(entities);
		}

		public void render() {
			frame.getPanelDisplayArea().repaint();
		}

        public void run() {
        	preload();
            // Calculate the optimal/maximum delay time
            // This is converted to nanos so it can be 
            // used to calculate the actual delay...
            long millisPerSecond = TimeUnit.MILLISECONDS.convert(1, TimeUnit.SECONDS);
            long optimalDelay = Math.round(millisPerSecond / 60);

            optimalDelay = TimeUnit.MILLISECONDS.toNanos(optimalDelay);

            // Last start of a "second" loop                    
            long loop = System.nanoTime();
            int frameCount = 0;
            // While gaming...
            while (keepRunning) {
                // Start of this cycle...
                long now = System.nanoTime();

                // Update the state and render the 
                // current frame...
    			update();
                render();

                // How long did that update take??
                long timeTaken = System.nanoTime();
                long delta = timeTaken - now;

                // Subtract the delay from the maximum delay
                long delay = optimalDelay - delta;
                if (delay > 0) {
                    try {
                        // Sleep expects milliseconds...
                        delay = TimeUnit.NANOSECONDS.toMillis(delay);
                        Thread.sleep(delay);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

                // Calculate if we've being running for a second yet...
                long loopDelay = TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - loop);
                // If the loop has been cycling for a second...
                if (loopDelay >= 1) {
                    // Reset the loop time
                    loop = System.nanoTime();
                    System.out.println("FPS = " + frameCount);
                    fps = frameCount;
                    frameCount = 0;
                } else {
                    // Add another frame to the pile...
                    frameCount++;
                }
            }
        }
    }