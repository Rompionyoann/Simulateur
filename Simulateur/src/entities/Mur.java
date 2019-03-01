package entities;

import Base.StdDraw;

public class Mur extends Decor {
	private double largeur;
	private double longueur;

	public Mur(double x, double y, double largeur, double longueur) {
		super(x, y);
		this.largeur = largeur;
		this.longueur = longueur;
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

}
