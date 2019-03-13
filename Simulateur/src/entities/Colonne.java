package entities;
import Base.Entite;
import Base.StdDraw;
public class Colonne extends Decor {


	public Colonne(double x, double y) {
		super(x, y);
	}

	@Override
	public boolean franchissable() {
		return false;
	}

	@Override
	public void dessine() {
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledCircle(this.getX(), this.getY(), 0.1);
	}

	@Override
	public void step() {
	}

}
