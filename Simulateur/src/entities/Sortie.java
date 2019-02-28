package entities;
import Base.Entite;

public abstract class Sortie extends Entite{
	
	private double longueurVert;
	private double longueurHori;
	
	public double getLongueurVert() {
		return longueurVert;
	}

	public void setLongueurVert(double longueurVert) {
		this.longueurVert = longueurVert;
	}

	public double getLongueurHori() {
		return longueurHori;
	}

	public void setLongueurHori(double longueurHori) {
		this.longueurHori = longueurHori;
	}

	//Initialisation d'une sortie de notre champ, X et Y correspondant a la position et Longueur a la longueur.
	//Longueur Vert et Hori ne peuvent pas etre toutes les deux différentes de 0
	public Sortie(double x, double y, double longueurVert, double LongueurHori) {
		super(x,y);
		this.longueurVert=longueurVert;
		this.longueurHori=longueurHori;
	}
	
	//fonction qui réalise le dessin de la sortie
	abstract public void dessine();
	
}
