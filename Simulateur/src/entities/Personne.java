package entities;

import java.util.LinkedList;

import java.util.List;

import Base.Entite;
import Base.Position;
import Base.StdDraw;

public class Personne extends Entite {

	private Position sortieLaPlusProche = new Position(-1, -1);
	private Position vecteurSortie;
	private double vitesse = 0.00325;
	private double Norme = 0;
	private double hitRadius=0.01;
	private List<Personne>aProximite= new LinkedList<Personne>();
	private int nbStep;
	private double proximityRadius = hitRadius*2.5;

	public int getNbStep() {
		return nbStep;
	}

	public void setNbStep(int nbStep) {
		this.nbStep = nbStep;
	}

	public double getProximityRadius() {
		return proximityRadius;
	}

	public void aProximiteAdd(Personne P) {
		if (P != null && this.aProximiteLength() <= 7)
			this.aProximite.add(P);
	}

	public void aProximiteRemove(Personne P) {
		if (P != null) {
			double a = Math.abs(this.getX() - P.getX());
			double b = Math.abs(this.getY() - P.getY());
			double d = Math.sqrt(a * a + b * b);
			if (d > this.getProximityRadius()) {
				this.aProximite.remove(P);
			}
		}
	}

	public void aProximiteRemove(int a) {
		this.aProximite.remove(a);
	}

	public int aProximiteLength() {
		return this.aProximite.size();
	}

	public List<Personne> getaProximite() {
		return aProximite;
	}

	public void setaProximite(List<Personne> aProximite) {
		this.aProximite = aProximite;
	}

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
		if (this.aProximite != null) {
			for (Personne p : this.aProximite) {

				double d = Math.random();
				if (p != null) {
					// System.out.println(surLatrajectoire(p));
					if (surLatrajectoire(p)) {
						// System.out.println("ko");
						if (d < 0.5) {
							this.vecteurSortie.setY(0);
							this.vecteurSortie.setX(0);
						} else {
							this.vecteurSortie.setY(0);
							this.vecteurSortie.setX(0);
						}
					}
				}
			}
			for (Personne p : this.aProximite) {
				double d = Math.random();
				if (p != null) {
					if (surLatrajectoire(p)) {
						if (d < 0.5) {
							this.vecteurSortie.setY(0);
							this.vecteurSortie.setX(0);

						} else {
							this.vecteurSortie.setY(0);
							this.vecteurSortie.setX(0);
						}
					}
				}
			}
		}
		if (this.vecteurSortie.getY()!=0 && this.vecteurSortie.getX()!=0) 
			Norme = Math.sqrt(Math.pow(vecteurSortie.getX(), 2) + Math.pow(vecteurSortie.getY(), 2));
		double x = vitesse * (vecteurSortie.getX() / Norme);
		// System.out.println("mon vecteur sortie en x "+ vecteurSortie.getX());
		// System.out.println("ma norme est de " + Norme);
		// System.out.println("j'avance de " + x);
		double y = vitesse * (vecteurSortie.getY() / Norme);
		this.setPosition(new Position(this.getX() + x, this.getY() + y));
	}

	private Boolean surLatrajectoire(Personne p) {
		/*
		 * double c=
		 * Math.sqrt(Math.pow((this.getX()-p.getX()),2)+Math.pow(this.getY()-p.getY(),
		 * 2)); System.out.println("a =" + a); double b=this.proximityRadius; Position
		 * d=new Position((vecteurSortie.getX()/Norme*this.proximityRadius) ,
		 * vecteurSortie.getY()/Norme*this.proximityRadius); System.out.println("d.X ="
		 * + d.getX() + " et " + d.getY()); double a=
		 * Math.sqrt(Math.pow((d.getX()-p.getX()),2)+Math.pow(d.getY()-p.getY(), 2));
		 * System.out.println("c =" + c); double h = Math.sqrt(Math.abs(((Math.pow(c,
		 * 2))-Math.pow(((a*a-b*b+c*c)/(2*a)),2))) ); System.out.println("h =" + h);
		 * //System.out.println(h); return h<=this.hitRadius;
		 */

		double a = this.getProximityRadius();
		double c = Math.sqrt(Math.pow((this.getX() - p.getX()), 2) + Math.pow(this.getY() - p.getY(), 2));
		Position C = new Position(this.getX() + (this.getProximityRadius() * (vecteurSortie.getX() / Norme)),
				this.getY() + (this.getProximityRadius() * (vecteurSortie.getY() / Norme)));
		double b = Math.sqrt(Math.pow((p.getX() - C.getX()), 2) + Math.pow(p.getY() - C.getY(), 2));
		double h = Math.sqrt(Math.abs(((Math.pow(c, 2)) - Math.pow(((a * a - b * b + c * c) / (2 * a)), 2))));
		
		double valP = Math.sqrt(Math.pow(p.getX()-sortieLaPlusProche.getX(),2)+Math.pow(p.getY()-sortieLaPlusProche.getY(),2));
		double valThis = Math.sqrt(Math.pow(this.getX()-sortieLaPlusProche.getX(),2)+Math.pow(this.getY()-sortieLaPlusProche.getY(),2));
		
		return (h <= this.hitRadius && (valThis>valP));
	}

	// TODO Fonction qui repr�sente un individu sur la map
	public void dessine() {
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.filledCircle(this.getX(), this.getY(), hitRadius);
		StdDraw.circle(this.getX(), this.getY(), proximityRadius);
	}

}
