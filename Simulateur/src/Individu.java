
public class Individu extends Entite{
	private Vecteur v;
	
	public Individu(double x, double y) {
		super(x, y);
		v = new Vecteur(x,y);
	}



	@Override
	public void step() {
		// TODO faire le déplacement avec v
		
	}

	@Override
	public void dessine() {
		StdDraw.circle(this.getX(), this.getY(), 1);
		
	}
	
}
