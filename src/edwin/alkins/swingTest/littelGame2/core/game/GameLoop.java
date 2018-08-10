package edwin.alkins.swingTest.littelGame2.core.game;

import java.awt.geom.Point2D;
import java.util.concurrent.TimeUnit;

import edwin.alkins.swingTest.littelGame2.core.entity.DefaultEntity;
import edwin.alkins.swingTest.littelGame2.core.entity.RobotEntity;
import edwin.alkins.swingTest.littelGame2.core.event.EventManager;
import edwin.alkins.swingTest.littelGame2.core.physicalEngine.PhysicalEngine;
import edwin.alkins.swingTest.littelGame2.core.util.Tools;
import edwin.alkins.swingTest.littelGame2.core.world.World;
import edwin.alkins.swingTest.littelGame2.ihm.visualiser.Visualiser;

public class GameLoop implements Runnable {

        private boolean keepRunning = true;
		private PhysicalEngine physicalEngine;
		private Visualiser visualiser;
		@SuppressWarnings("unused")
		private int fps;
		
		public GameLoop() {
			this.physicalEngine = new PhysicalEngine();
		}
		
		public void preload() {
			RobotEntity.initialize();
			DefaultEntity.initialize();
			World world = new World();
			this.physicalEngine.setWorld(world);
			this.visualiser  = new Visualiser(world);
			int size = 1000;
			for (int i = 0; i < size; i++) {
				double r1 = Tools.randome(0d, 500d);
				double r2 = Tools.randome(0d, 500d);
				double a = Tools.randome(0d, 360d);
				double s = Tools.randome(0.5d, 2d);
				RobotEntity robot = new RobotEntity();
				robot.setLocation(new Point2D.Double(r1,r2));
				robot.setAngle(a);
				robot.setScale(s);
				world.addEntity(robot);
			}
		}

		public void processInput() {
			EventManager.getInstance().processEvent(this.physicalEngine.getWorld().getListOfEntities());
		}

		public void update(long l) {
			this.physicalEngine.update(l);
		}

		public void render() {
			visualiser.render();
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
            long beforeTime = System.currentTimeMillis();
            int frameCount = 0;
            // While gaming...
            while (keepRunning) {
                // Start of this cycle...
                long now = System.nanoTime();

                // Update the state and render the 
                // current frame...
                processInput();
                long timafter = System.currentTimeMillis();
                long timePass = timafter - beforeTime;
            	update(timePass);
    			render();
    			beforeTime = System.currentTimeMillis();

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
                //System.out.println(optimalDelay+" "+delta+" "+delay+" "+loop+" "+loopDelay+" "+timePass);
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