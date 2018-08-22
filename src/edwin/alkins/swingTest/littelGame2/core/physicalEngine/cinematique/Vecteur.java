package edwin.alkins.swingTest.littelGame2.core.physicalEngine.cinematique;

public class Vecteur {
		private double x, y, z;

		public enum Coordonee{
			X,Y,Z;
		}
		public Vecteur(double x, double y){
			this.x=x;
			this.y=y;
			this.z=0;
		}
		public Vecteur(double x, double y, double z){
			this.x=x;
			this.y=y;
			this.z=z;
		}
		public Vecteur(Vecteur v){
			set(v);
		}
		public void ajouter(double x, double y, double z) {
			this.x+=x;
			this.y+=y;
			this.z+=z;
		}
		void ajouter(Vecteur v){
			this.x+=v.x;
			this.y+=v.y;
			this.z+=v.z;
		}
		public double get(Coordonee c) {
			if(c.equals(Coordonee.X))
				return this.x;
			else if(c.equals(Coordonee.Y))
				return this.y;
			return this.z;
		}
		public void set(Coordonee c,double d){
			if(c.equals(Coordonee.X))
				this.x = d;
			else if(c.equals(Coordonee.Y))
				this.y = d;
			this.z = d;
		}
		public void set(Vecteur v) {
			this.x=v.x;
			this.y=v.y;
			this.z=v.z;
		}
		public double getAngle(Vecteur v) {
			return (Math.atan2((v.y-y),(v.x-x))+Math.PI/2d) * (180d/Math.PI);
		}
		@Override
		public String toString() {
			return "Vecteur [x=" + x + ", y=" + y + ((z==0)?(""):(", z=" + z)) + "]";
		}
	}