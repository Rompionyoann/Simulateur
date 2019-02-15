
public class Mur extends Decor{

	public Mur(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void dessine() {
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.rectangle(this.getX(), this.getY(), 1, 2);
	}

	@Override
	public boolean franchissable() {
		return false;
	}

}
