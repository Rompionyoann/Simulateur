package entities;

import Base.StdDraw;

public class Mur extends Decor {
	private double largeur;
	private double longueur;
	private Boolean verticale;

	public Mur(double x, double y, double largeur, double longueur) {
		super(x, y);
		this.largeur = largeur;
		this.longueur = longueur;
		if (largeur>longueur) verticale = false;
		else verticale =true;
	}

	@Override
	public void dessine() {
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledRectangle(this.getX(), this.getY(), largeur, longueur);
	}

	@Override
	public boolean franchissable() {
		return false;
	}

	@Override
	public void step() {
	}

	public double getLargeur() {
		return largeur;
	}

	public void setLargeur(double largeur) {
		this.largeur = largeur;
	}

	public double getLongueur() {
		return longueur;
	}

	public void setLongueur(double longueur) {
		this.longueur = longueur;
	}

	public Boolean getVerticale() {
		return verticale;
	}

	public void setVerticale(Boolean verticale) {
		this.verticale = verticale;
	}

}
