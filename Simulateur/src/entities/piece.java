package entities;
import java.util.List;
import Base.Entite;
import Base.Position;
import Base.StdDraw;

public class piece extends Entite{
	private double taillex;
	private double tailley;
	private List<Position> lSortie;	
	
	public piece(double x, double y,double taillex, double tailley){
		super(x,y);
		this.taillex = taillex;
		this.tailley = tailley;
	}
	public double getTaillex() {
		return taillex;
	}
	public void setTaillex(int taillex) {
		this.taillex =  taillex;
	}
	public double getTailley() {
		return tailley;
	}
	public void setTailley(int tailley) {
		this.tailley = tailley;
	}
	public List<Position> getlSortie() {
		return lSortie;
	}
	public void setlSortie(List<Position> lSortie) {
		this.lSortie = lSortie;
	}
	@Override
	public void step() {
	}
	@Override
	public void dessine() {
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledCircle(this.getX(), this.getY(), 0.01);
	}
	
}
