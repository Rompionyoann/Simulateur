package entities;

import java.util.LinkedList;
import java.util.List;

import Base.Entite;
import Base.Position;
import Base.StdDraw;

public class Personne extends Entite {

	private Position sortieLaPlusProche =new Position(-1,-1);
	private Position vecteurSortie;
	private double vitesse = 0.01;
	private double Norme =0;
	private double hitRadius;
	private List<Personne> àProximité= new LinkedList<Personne>();
	private int nbStep;
	private double proximityRadius=hitRadius*10;
	
	
	public int getNbStep() {
		return nbStep;
	}

	public void setNbStep(int nbStep) {
		this.nbStep = nbStep;
	}
	public double getProximityRadius() {
		return proximityRadius;
	}
	
	public void àProximitéAdd(Personne P) {		
		if(P!=null && this.àProximitéLength() <=7 )
		this.àProximité.add(P);
	}
	public void àProximitéRemove(Personne P) {
		if(P!=null) {
		double a = Math.abs(this.getX()-P.getX());
		double b= Math.abs(this.getY()-P.getY());
		double d=Math.sqrt(a*a+b*b);
		if(d>this.getProximityRadius()) {
			this.àProximité.remove(P);
		}
	}
	}
	public void àProximitéRemove(int a) {
		this.àProximité.remove(a);
		}
	
	public int àProximitéLength() {
		return this.àProximité.size();
	}
	

	public List<Personne> getàProximité() {
		return àProximité;
	}

	public void setàProximité(List<Personne> àProximité) {
		this.àProximité = àProximité;
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
			if(this.àProximité != null) {
			for(Personne p: this.àProximité) {
				double d=Math.random();
				if(p!=null) {				
				if(surLatrajectoire(p)) {				
					if(d<0.5) {
						this.vecteurSortie.setY(0);
						this.vecteurSortie.setX(0);
				}else {
					this.vecteurSortie.setY(0);
					this.vecteurSortie.setX(0);
				}
				}
			}
			}
			for(Personne p: this.àProximité) {
				double d=Math.random();
				if(p!=null) {
				if(surLatrajectoire(p)) {				
					if(d<0.5) {
					this.vecteurSortie.setY(0);
						this.vecteurSortie.setX(0);
					
				}else {
					this.vecteurSortie.setY(0);
					this.vecteurSortie.setX(0);
				}
				}
			}
			}
			}
		double x =vitesse * (vecteurSortie.getX()/Norme);
//		System.out.println("mon vecteur sortie en x "+ vecteurSortie.getX());
//		System.out.println("ma norme est de  " + Norme);
//		System.out.println("j'avance de " + x);
		double y = vitesse * (vecteurSortie.getY()/Norme);
		this.setPosition(new Position(this.getX() + x, this.getY() + y));
	}
	private Boolean surLatrajectoire(Personne p) {
		double a= Math.sqrt(Math.pow((this.getX()-p.getX()),2)+Math.pow(this.getY()-p.getY(), 2));
		double b=this.proximityRadius;
		Position d=new Position((vecteurSortie.getX()*this.proximityRadius) , vecteurSortie.getY()*this.proximityRadius);
		double c= Math.sqrt(Math.pow((d.getX()-p.getX()),2)+Math.pow(d.getY()-p.getY(), 2));
		double h = Math.sqrt((Math.pow(c, 2))-((a*a-b*b+c*c)/(2*a)));
		return h<=this.hitRadius;
	}
	// TODO Fonction qui repr�sente un individu sur la map
	public void dessine() {
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.filledCircle(this.getX(), this.getY(), hitRadius);
	}

}
