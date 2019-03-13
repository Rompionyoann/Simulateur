package entities;
import Base.Entite;
public abstract class Flux extends Entite {
	
	private double[] vecteur= new double[2];
	
	Flux(double x, double y, double[] vecteur) {
		super(x,y);
		this.vecteur=vecteur;
	}
	
	public double[] getVecteur() {
		return vecteur;
	}

	//TODO
	public void setVecteur(piece z) {
	}

	
}
