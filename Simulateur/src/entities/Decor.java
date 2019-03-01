package entities;
import Base.Entite;
public abstract class Decor extends Entite {
	
	public Decor(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	public abstract boolean franchissable();
	
	public abstract void step();

	@Override
	public abstract void dessine();
}

