package edwin.alkins.swingTest.littelGame2.core.action;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractAction {

	protected Point2D target;
	protected Point2D currentPoint;
	protected double currentAngle;
	
	public AbstractAction() {
	}
	
	public Map<String,Object> getDataAction(){
		Map<String,Object> data = new LinkedHashMap<String, Object>();
		for(Field field:this.getClass().getDeclaredFields()) {
			try {
				data.put(field.getName(),field.get(this));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return Collections.unmodifiableMap(data);
	}
	public void setTarget(Point p) {
		this.target = p;
	}
	public void mouveToTarget(Point2D point2d, double velocity, long timePass) {
		Point2D p = point2d;
		if(this.target == null) {
			this.currentPoint = point2d;
		}
		else {
			Point2D vecteur_direction = new Point2D.Double(target.getX()-point2d.getX(), target.getY()-point2d.getY());
			double distance = Math.sqrt(Math.pow(target.getY()-point2d.getY(), 2) + Math.pow(target.getX()-point2d.getX(), 2));
			double v = velocity*timePass*0.01;
			double tx = vecteur_direction.getX()/distance*v;
			double ty = vecteur_direction.getY()/distance*v;
			if(Math.floor(distance) != 0) {
				this.currentAngle = getAngle(point2d,target);
				p = new Point2D.Double(point2d.getX()+tx,point2d.getY()+ty);
			} else {
				p = this.target;
			}
			this.currentPoint = p;
		}
	}
	public double getAngle(Point2D centerPt, Point2D targetPt) {
	    double theta = Math.atan2(targetPt.getY() - centerPt.getY(),targetPt.getX() - centerPt.getX());
	    theta += Math.PI/2.0;
		double angle = Math.toDegrees(theta);
		return angle;
	}
	public Point2D getPoint() {
		return this.currentPoint;
	}
	public double getAngle() {
		return this.currentAngle;
	}
	public Point2D getTarget() {
		return this.target;
	}
}
