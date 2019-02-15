
public class Vecteur {
	private vect v;
	
	private class vect {
		double x;
		double y;
		public vect(double x,double y) {
			this.x=x;
			this.y=y;
		}
	}
	
	public Vecteur(double x, double y) {
		this.v = new vect(x,y);
	}
	
}
