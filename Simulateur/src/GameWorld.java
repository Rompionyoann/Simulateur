import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 * The class GameWorld that handle the flow of the game
 * @author hugom
 *
 */
public class GameWorld {
	private char derni�reTouche;

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
		case 'm':
			derni�reTouche='m';
			// TODO
			break;
		case 'p':
			derni�reTouche='p';
			// TODO
			break;
		case 'c':
			derni�reTouche='c';
			// TODO
			break;
		case 'i':
			derni�reTouche='i';
			// TODO
			break;
		case 's':
			derni�reTouche='s';
			// TODO
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
