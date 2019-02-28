package Base;

import Base.Position;

public abstract class Entite {
	
	// la position de l'entite
	protected Position position;
	

	/*
	 * Constructeur d'entité sans hitbox
	 */
	public Entite(double x, double y) {
		position = new Position(x, y);
	}
	


	public double getX() {
		return position.getX();
	}
	
	public double getY() {
		return this.position.getY();
	}
	public void setPosition(Position p){
		this.position = p;
	}
	
	// met a jour l'entite : déplacement, effectuer une action
	public abstract void step();
	
	// dessine l'entite, aux bonnes coordonnees
	public abstract void dessine();
	

}
