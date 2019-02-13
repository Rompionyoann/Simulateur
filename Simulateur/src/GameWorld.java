import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 * The class GameWorld that handle the flow of the game
 * @author hugom
 *
 */
public class GameWorld {

	private static List<Entite> entites;

	/**
	 * Constructor of the Gameworld
	 */
	public GameWorld() {
		entites = new LinkedList<Entite>();
	}


	/**
	 * Gestion des interactions clavier avec l'utilisateur
	 * 
	 * @param key Touche pressee par l'utilisateur
	 */
	public void processUserInput(char key) {
		switch (key) {
		case 't':
			// TODO
			break;
		case 'p':
			// TODO
			break;
		case 'n':
			// TODO
			break;
		case 'd':
			// TODO
			break;
		case 'z':
			// TODO
			break;
		case 'a':
			// TODO
			break;

		default:
			break;
		}
	}

	/**
	 * Gestion des interactions souris avec l'utilisateur (la souris a été
	 * cliquée)
	 * 
	 * @param x position en x de la souris au moment du clic
	 * @param y position en y de la souris au moment du clic
	 */
	public void processMouseClick(double x, double y) {
		}
		
	/**
	 * Action for all the entities and elements of the game
	 */
	public void step() {
		for (Entite entite : GameWorld.entites)
			entite.step();
		}

	/**
	 * Draw all entities and elements of the game
	 */
	public void dessine() {
		// affiche les entites
		for (Entite entite : entites)
			entite.dessine();
	}
}
