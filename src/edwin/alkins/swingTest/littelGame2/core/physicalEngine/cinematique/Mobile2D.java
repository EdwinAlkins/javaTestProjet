package edwin.alkins.swingTest.littelGame2.core.physicalEngine.cinematique;

import java.awt.geom.Point2D;

import edwin.alkins.swingTest.littelGame2.core.physicalEngine.cinematique.Vecteur.Coordonee;

public class Mobile2D {
		private double v;
		private double a;
		private Vecteur vectP, vectV, vectA;
		
		public Mobile2D(double v, double a, Vecteur vectP, Vecteur vectV, Vecteur vectA){
			this.v = v;
			this.a = a;
			this.vectV = vectV;
			this.vectP = vectP;
			this.vectA = vectA;
		}
		
		private void calculeVitesse(double delta) {
			double vx = -0.5d*vectA.get(Coordonee.X)*Math.pow(delta, 2)+v*Math.cos(Math.toRadians(a))*delta;//+vectV.get(Coordonee.X);
			double vy = -0.5d*vectA.get(Coordonee.Y)*Math.pow(delta, 2)+v*Math.sin(Math.toRadians(a))*delta;//+vectV.get(Coordonee.Y);
			this.vectV = new Vecteur(vx, vy);
		}
		
		public void actualiser(double delta) {
			calculeVitesse(delta);
			//Vecteur tmp = new Vecteur(vectP);
			vectP.ajouter(this.vectV);
			//a = vectP.getAngle(tmp);
		}
		
		public void setV(double v) {
			this.v = v;
		}
		public void setVectP(Vecteur v) {
			this.vectP.set(v);
		}
		public void setAngle(double a) {
			this.a = a;
		}
		public Point2D getPosition() {
			return new Point2D.Double(vectP.get(Coordonee.X),vectP.get(Coordonee.Y));
		}

		@Override
		public String toString() {
			return "Mobile2D [v=" + v + ", a=" + a + ", vectP=" + vectP + ", vectV=" + vectV + ", vectA=" + vectA + "]";
		}
	}