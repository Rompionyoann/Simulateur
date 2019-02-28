package entities;
import Base.Entite;
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
	}

}
