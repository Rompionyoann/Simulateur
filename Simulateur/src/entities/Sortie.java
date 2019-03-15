package entities;

import Base.Entite;
import Base.StdDraw;

public class Sortie extends Entite {

	private double longueurVert;
	private double longueurHori;
	private boolean verticale;

	public boolean isVertical() {
		return verticale;
	}

	public void setVertical(boolean vertical) {
		this.verticale = vertical;
	}

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

	// Initialisation d'une sortie de notre champ, X et Y correspondant a la
	// position et Longueur a la longueur.
	// Longueur Vert et Hori ne peuvent pas etre toutes les deux différentes de 0
	public Sortie(double x, double y, double longueurHori, double longueurVert) {
		super(x, y);
		this.longueurVert = longueurVert;
		this.longueurHori = longueurHori;
		if (longueurHori>longueurVert) verticale = false;
		else verticale =true;
	}

	// fonction qui réalise le dessin de la sortie
	public void dessine() {
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledRectangle(this.getX(), this.getY(), longueurHori, longueurVert);
	}

	@Override
	public void step() {
	}

}
