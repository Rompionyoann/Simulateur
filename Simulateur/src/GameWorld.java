import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 * The class GameWorld that handle the flow of the game
 * @author hugom
 *
 */
public class GameWorld {
	private char dernièreTouche;

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
			dernièreTouche='m';
			// TODO
			break;
		case 'p':
			dernièreTouche='p';
			// TODO
			break;
		case 'c':
			dernièreTouche='c';
			// TODO
			break;
		case 'i':
			dernièreTouche='i';
			// TODO
			break;
		case 's':
			dernièreTouche='s';
			// TODO
		default:
			break;
		}
	}

	/**
	 * Gestion des interactions souris avec l'utilisateur (la souris a Ã©tÃ©
	 * cliquÃ©e)
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
