package Base;
/**
 * Class for Position
 * @author hugom
 *
 */
public class Position {
	/**
	 * The position in the x axis
	 */
	private double positionX;
	/**
	 * The position in the y axis
	 */
	private double positionY;
	/**
	 * Constructor for a position
	 * @param x
	 * @param y
	 * @return the position x,y
	 */
	public Position(double x, double y){
		positionX=x;
		positionY=y;
	}
	
	public double getX(){
		return positionX;
	}
	
	public double getY(){
		return positionY;
	}
	/**
	 * @param p
	 * @return if the position is equals to the position p
	 */
	public boolean equals(Position p){
		return (this.positionX==p.positionX && this.positionY==p.positionY);
	}

	public void setY(double y) {
		// TODO Auto-generated method stub
		this.positionY = y;
	}

	public void setX(double x) {
		// TODO Auto-generated method stub
		this.positionX = x;
	}
	
	public String toString(){
		return null;
	}
}
