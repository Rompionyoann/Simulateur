package Base;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import entities.Colonne;
import entities.Mur;
import entities.Personne;
import entities.Sortie;

/**
 * The class GameWorld that handle the flow of the game
 * 
 * @author hugom
 *
 */
public class GameWorld {
	private char derniereTouche;
	private boolean gameIsOk = true;

	private class pos {
		double x;
		double y;

		public pos(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public double getX() {
			return x;
		}

		public void setX(double x) {
			this.x = x;
		}

		public double getY() {
			return y;
		}

		public void setY(double y) {
			this.y = y;
		}
		
	}

	private List<pos> lSortie = new LinkedList<pos>();

	private static List<Entite> entites;

	/**
	 * Constructor of the Gameworld
	 */
	public GameWorld() {
		entites = new LinkedList<Entite>();
		lSortie = new LinkedList<pos>();
	}

	/**
	 * Gestion des interactions clavier avec l'utilisateur
	 * 
	 * @param key Touche pressee par l'utilisateur
	 */
	public void processUserInput(char key) {
		switch (key) {
		case 'm':
			System.out.println("un mur veut etre place");
			derniereTouche = 'm';
			// TODO
			break;
		case 'p':
			derniereTouche = 'p';
			// TODO
			break;
		case 'c':
			derniereTouche = 'c';
			// TODO
			break;
		case 'i':
			derniereTouche = 'i';
			// TODO
			break;
		case 's':
			derniereTouche = 's';
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
		switch (derniereTouche) {
		case 'm':
			entites.add(new Mur(x, y, 0.01, 0.05));
			System.out.println(entites);
			derniereTouche = '?';
			break;
		}
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
	public void Batiment1() {
		entites.add(new Mur(0.005, 0.5, 0.01, 1));
		entites.add(new Mur(0.995, 0.5, 0.01, 1));
		entites.add(new Mur(0.5, 0.005, 1, 0.01));
		entites.add(new Mur(0.225, 0.995, 0.45, 0.01));
		entites.add(new Mur(0.825, 0.995, 0.45, 0.01));
		entites.add(new Sortie(0.5, 0.995, 0.1, 0.01));
		entites.add(new Colonne(0.5, 0.8));
	}

//	public void AjoutePersonne(int n) {
//		for (int i=0; i<n; i++) {
//			entite.add(Personne())
//		}
//	}

	public void trouveSortie() {
		for (Entite entite : entites) {
			if (entite instanceof Sortie)
				lSortie.add(new pos(entite.getX(), entite.getY()));
		}
	}

	public void trouveSortieLaPlusProche(Personne p) {
		double x = p.getX();
		double y = p.getY();
		double d;
		for (pos s : lSortie) {
			double newD = Math.Sqrt(Math.pow(Math.abs(x-s.getX()),2)+Math.pow(Math.abs(y-s.getY())));
			if(d==null||newD<d) d
		}
	}

	public void dessine() {
		Batiment1();
		// affiche les entites
		for (Entite entite : entites) {
			entite.dessine();
		}
	}

	public boolean isGameIsOk() {
		return gameIsOk;
	}

	public void setGameIsOk(boolean gameIsOk) {
		this.gameIsOk = gameIsOk;
	}
}
