package entities;
import Base.Entite;

public abstract class Personne extends Entite {
	
	private double vitesse;
	private double[] vecteurVit= new double[2];
	private double hitRadius;
	private Personne[] proximit�=new Personne[6];
	
	Personne(double x , double y, double vitesse, double[] vecteurVit, double hitRadius) {
		super(x,y);
		this.vitesse=vitesse;
		this.vecteurVit= vecteurVit;	
		this.hitRadius=hitRadius;
	}
	
	public double getVitesse() {
		return vitesse;
	}

	public void setVitesse(double vitesse) {
		this.vitesse = vitesse;
	}

	public double[] getVecteurVit() {
		return vecteurVit;
	}

	public void setVecteurVit(double[] vecteurVit) {
		this.vecteurVit = vecteurVit;
	}

	public double getHitRadius() {
		return hitRadius;
	}

	public void setHitRadius(double hitRadius) {
		this.hitRadius = hitRadius;
	}

	//TODO Fonction Modifiant les valeurs de X et Y en fonction de l'executions de chaque step 
	public abstract void step();
	
	//TODO Fonction qui repr�sente un individu sur la map
	public abstract void dessine();
	
	//TODO d�finition de la fonction proximit�
	public abstract Personne[] proxi();
}
