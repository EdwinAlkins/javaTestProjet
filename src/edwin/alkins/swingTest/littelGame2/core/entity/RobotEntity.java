package edwin.alkins.swingTest.littelGame2.core.entity;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import edwin.alkins.swingTest.littelGame2.core.entity.model.EntityModel;
import edwin.alkins.swingTest.littelGame2.core.entity.model.Model;
import edwin.alkins.swingTest.littelGame2.core.entity.shape.Ellipse2DFill;
import edwin.alkins.swingTest.littelGame2.core.entity.shape.Rectangle2DFill;
import edwin.alkins.swingTest.littelGame2.core.inventory.Inventory;
import edwin.alkins.swingTest.littelGame2.core.physicalEngine.cinematique.Mobile2D;
import edwin.alkins.swingTest.littelGame2.core.physicalEngine.cinematique.Vecteur;
import edwin.alkins.swingTest.littelGame2.core.physicalEngine.cinematique.Vecteur.Coordonee;

/**
 * ^super..*.add.new.(.*)Fill(.*).setDrawnBorder.*.setFillColor.(Color.*)\)\);.*
 * g2d.setColor\(\3\);\ng2d.fill\(new \1\.Double\2\);
 * 
 * @author wnauroy
 *
 */
public class RobotEntity extends Entity {

	protected Inventory inventory;
	private Mobile2D m;
	public static int id = -1;

	public RobotEntity() {
		super(id);
		this.inventory = new Inventory(10);
		m = new Mobile2D(0.1, 0, new Vecteur(0, 0), new Vecteur(0, 0), new Vecteur(0, 0));
	}

	public static void initialize() {
		if(id == -1) {
			Model m = new Model(new Rectangle2D.Double(0, 0, 32d, 26d));
			m.addRectangle2D(new Rectangle2DFill(2d, 0d, 2d, 10d).setDrawnBorder(false).setFillColor(Color.black));
			m.addRectangle2D(new Rectangle2DFill(0d, 10d, 6d, 12d).setDrawnBorder(false).setFillColor(Color.red));
			m.addRectangle2D(new Rectangle2DFill(6d, 6d, 20d, 20d).setDrawnBorder(false).setFillColor(Color.blue));
			m.addRectangle2D(new Rectangle2DFill(14d, 14d, 4d, 12d).setDrawnBorder(false).setFillColor(Color.red));
			m.addRectangle2D(new Rectangle2DFill(26d, 10d, 6d, 12d).setDrawnBorder(false).setFillColor(Color.red));
			m.addRectangle2D(new Rectangle2DFill(28d, 0d, 2d, 10d).setDrawnBorder(false).setFillColor(Color.black));

			m.addEllipse2D(new Ellipse2DFill(10d, 8d, 4d, 4d).setDrawnBorder(false).setFillColor(Color.green));
			m.addEllipse2D(new Ellipse2DFill(18d, 8d, 4d, 4d).setDrawnBorder(false).setFillColor(Color.green));
			m.render();
			m.updateShape();
			EntityModel instance = EntityModel.getInstance();
			id = instance.getNewId();
			instance.linkModel(id, m);
		}
	}

	@Override
	public void update(long timePass) {
		if(action.getTarget() != null) {
	        double tmpAngle = getAngle(getLocationCenter(),action.getTarget());
	        m.setVectP(new Vecteur(getLocationCenter().getX(), getLocationCenter().getY()));
	        m.setAngle(tmpAngle);
			m.actualiser(timePass);
	        Point2D p = m.getPosition();
	        double d1 = p.distance(getLocationCenter());
	        double d2 = p.distance(action.getTarget());
	        if(d1>=d2) {
	        	p = action.getTarget();
	        	action.setTarget(null);
	        }
	        setAngle(convertAngleToGame(tmpAngle));
	        setLocationCenter(p);
		}
	}
	
	public double convertAngleToGame(double angle) {
		angle = Math.PI/2.0 + Math.toRadians(angle);
		return Math.toDegrees(angle);
	}
	
	public double getAngle(Point2D centerPt, Point2D targetPt) {
	    double theta = Math.atan2(targetPt.getY() - centerPt.getY(),targetPt.getX() - centerPt.getX());
		return Math.toDegrees(theta);
	}
}
