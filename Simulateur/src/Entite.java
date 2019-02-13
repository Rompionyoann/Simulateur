/**
 * An abstract class who represent an Entite in the game
 * @author hugom
 */
public abstract class Entite {
	
	/**
	 * Position of the entite
	 */
	protected Position position;
	
	/**
	 * Constructor of the entite
	 * @param x
	 * @param y
	 * @return An entite at the position x,y
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

	public abstract void step();
	
	public abstract void dessine();
	

}
