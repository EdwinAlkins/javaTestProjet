package edwin.alkins.swingTest.littelGame2.core.event;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import edwin.alkins.swingTest.littelGame2.core.entity.Entity;

public class EventManager {

	private static EventManager instance = null;
	private EventMouse eventMouse = new EventMouse();
	private EventKey eventKey = new EventKey();

	private EventManager() {
	}

	public static EventManager getInstance() {
		if (instance == null)
			instance = new EventManager();
		return instance;
	}
	
	public void processEvent(List<Entity> entities) {
		this.eventMouse.process(entities);
		this.eventKey.process(this.eventMouse.getListOfEntitiesHasSelected());
	}
	public void setMouseEventPress(MouseEvent e) {
		this.eventMouse.setEvent(e);			
	}
	public void setKeyEvent(KeyEvent e) {
		this.eventKey.setEvent(e);
	}
	
	class EventMouse{
		private int button = -1;
		private boolean isControlDown = false;
		private Point position = new Point(0, 0);
		private List<Entity> listOfEntitiesHasSelected = new ArrayList<Entity>();
		
		public void setEvent(MouseEvent e) {
			this.button = e.getButton();
			this.isControlDown = e.isControlDown();
			this.position = e.getPoint();
		}
		public void process(List<Entity> entities) {
			if(this.button == MouseEvent.BUTTON1) {
				if(!this.isControlDown)
					this.listOfEntitiesHasSelected.clear();
				entities.stream().forEach(
					entity -> {
						if(entity.isContaine(this.position)) {
							if(!this.isControlDown)
								this.listOfEntitiesHasSelected.clear();
							this.listOfEntitiesHasSelected.add(entity);
						}
					}
				);
			}
			else if(this.button == MouseEvent.BUTTON3) {
				entities.stream().forEach(
					entity -> {
						entity.getAction().setTarget(this.position);
					}
				);
			}
			this.button = -1;
			this.isControlDown = false;
		}
		public List<Entity> getListOfEntitiesHasSelected(){
			return this.listOfEntitiesHasSelected;
		}
	}
	class EventKey{
		private int key = -1;
		
		public void setEvent(KeyEvent e) {
			this.key = e.getKeyCode();
		}
		public void process(List<Entity> entities) {
			Point2D mouve = new Point2D.Double(0,0);
			if(this.key == KeyEvent.VK_0)
				mouve.setLocation(0,-0.5d);
			if(this.key == KeyEvent.VK_UP)
				mouve.setLocation(0,-0.5d);
			else if(this.key == KeyEvent.VK_DOWN)
				mouve.setLocation(0,0.5d);
			else if(this.key == KeyEvent.VK_LEFT)
				mouve.setLocation(-0.5d,0);
			else if(this.key == KeyEvent.VK_RIGHT)
				mouve.setLocation(0.5d,0);
			if(!mouve.equals(new Point2D.Double(0,0))) {
				entities.stream().forEach(
					entity -> {
						entity.setLocation(new Point2D.Double(entity.getLocation().getX()+mouve.getX(),entity.getLocation().getY()+mouve.getY()));
					}
				);
			}
			this.key = -1;
		}
	}
}
