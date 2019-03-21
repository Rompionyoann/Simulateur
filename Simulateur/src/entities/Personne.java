package entities;

import Base.Entite;
import Base.Position;
import Base.StdDraw;

public class Personne extends Entite {

	private Position sortieLaPlusProche =new Position(-1,-1);
	private Position vecteurSortie;
	private double vitesse = 0.01;
	private double Norme =0;
	private double hitRadius;

	public double getVitesse() {
		return vitesse;
	}

	public void setVitesse(double vitesse) {
		this.vitesse = vitesse;
	}

	public Position getSortieLaPlusProche() {
		return sortieLaPlusProche;
	}

	public void setSortieLaPlusProche(Position sortieLaPlusProche) {
		this.sortieLaPlusProche = sortieLaPlusProche;
	}

	public Personne(double x, double y, double hitRadius) {
		super(x, y);
		this.hitRadius = hitRadius;
	}

	public double getHitRadius() {
		return hitRadius;
	}

	public void setHitRadius(double hitRadius) {
		this.hitRadius = hitRadius;
	}

	// TODO Fonction Modifiant les valeurs de X et Y en fonction de l'executions de
	// chaque step
	public void step() {
			vecteurSortie = new Position((sortieLaPlusProche.getX() - this.getX()),
					(sortieLaPlusProche.getY() - this.getY()));
			Norme = Math.sqrt(Math.pow(vecteurSortie.getX(), 2) + Math.pow(vecteurSortie.getY(), 2));

		double x =vitesse * (vecteurSortie.getX()/Norme);
//		System.out.println("mon vecteur sortie en x "+ vecteurSortie.getX());
//		System.out.println("ma norme est de  " + Norme);
//		System.out.println("j'avance de " + x);
		double y = vitesse * (vecteurSortie.getY()/Norme);
		this.setPosition(new Position(this.getX() + x, this.getY() + y));
	}

	// TODO Fonction qui représente un individu sur la map
	public void dessine() {
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.filledCircle(this.getX(), this.getY(), hitRadius);
	}

}
