package Base;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import entities.Colonne;
import entities.Mur;
import entities.Personne;
import entities.Sortie;
import entities.piece;

/**
 * The class GameWorld that handle the flow of the game
 * 
 * @author hugom
 *
 */
public class GameWorld {
	private char derniereTouche;
	private boolean gameIsOk = true;
	private List<Position> lSortie;
	private static List<Entite> entites;
	private List<piece> lPiece = new LinkedList<piece>();

	/**
	 * Constructor of the Gameworld
	 */
	public GameWorld() {
		entites = new LinkedList<Entite>();
		lSortie = new LinkedList<Position>();
		Batiment1();
		AnalyseBatiment();
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
			System.out.println("une personne veut etre place");
			derniereTouche = 'p';
			// TODO
			break;
		case 'c':
			derniereTouche = 'c';
			System.out.println("une colonne veut etre place");
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
	 * Gestion des interactions souris avec l'utilisateur (la souris a √©t√©
	 * cliqu√©e)
	 * 
	 * @param x position en x de la souris au moment du clic
	 * @param y position en y de la souris au moment du clic
	 */
	public void processMouseClick(double x, double y) {
		switch (derniereTouche) {
		case 'm':
			entites.add(new Mur(x, y, 0.01, 0.05));
			derniereTouche = '?';
			break;
		case 'p':
			Personne p = new Personne(x, y, 0.01);
			entites.add(p);
			p.setSortieLaPlusProche(trouveSortieLaPlusProche(p));

			derniereTouche = '?';
			break;
		}
	}

	/**
	 * Action for all the entities and elements of the game
	 */
	public void step() {
		for (Entite entite : GameWorld.entites) {
			entite.step();
		}
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
		entites.add(new Mur(0.25, 0.5, 0.5, 0.01));
		entites.add(new Sortie(0.5, 0.995, 0.1, 0.01));
		entites.add(new Colonne(0.5, 0.8));
	}

	public void AnalyseBatiment() {
		List<Mur> murVerticale = new LinkedList<Mur>();
		List<Mur> murHorizontale = new LinkedList<Mur>();
		for (Entite e : entites) {
			if (e instanceof Mur) {
				if (e.getX() > 0.005 && e.getY() > 0.005 && e.getY() < 0.995) {
					if (((Mur) e).getVerticale())
						murVerticale.add((Mur) e);
					else
						murHorizontale.add((Mur) e);
				}
			}
		}
		while (!murVerticale.isEmpty()) {
			double x = 0.005;
			Mur murXpetit = null;
			for (Mur m : murVerticale) {
				if (murXpetit == null || (m.getX() < murXpetit.getX() && murXpetit.getX() > x))
					murXpetit = m;
			}
			if (murXpetit != null) {
				piece p = new piece((murXpetit.getX() - x) / 2, 0.5, (murXpetit.getX() - x), 1);
				// tests
				System.out.println(p.getTaillex());
				lPiece.add(p);
				x = murXpetit.getX();
				murVerticale.remove(murXpetit);
			}
		}
		List<piece> lAjoute = new LinkedList<piece>();
		List<piece> lSupprime = new LinkedList<piece>();
		System.out.println(murHorizontale.size());
		for (piece p : lPiece) {
			for (Mur m : murHorizontale) {
				System.out.println(m.getX());
				System.out.println((p.getX() + (p.getTaillex() / 2)));
				System.out.println((p.getX() - (p.getTaillex() / 2)));
				if ((m.getX() < (p.getX() + (p.getTaillex() / 2))) && (m.getX() > (p.getX() - (p.getTaillex() / 2)))) {
					System.out.println("ayaaaa");
					piece p1 = new piece(p.getX(), m.getY() + ((p.getTailley() - m.getY()) / 2), p.getTaillex(),
							p.getTailley() - m.getY());
					piece p2 = new piece(p.getX(), m.getY() / 2, p.getTaillex(), m.getY());
					lAjoute.add(p1);
					lAjoute.add(p2);
					lSupprime.add(p);
				}
			}
		}
		for (piece s : lSupprime) {
			lPiece.remove(s);
		}
		for (piece a : lAjoute) {
			lPiece.add(a);
		}
	}
//	public void AjoutePersonne(int n) {
//		for (int i=0; i<n; i++) {
//			entite.add(Personne())
//		}
//	}

	/*
	 * faitDesTruc est une fonction qui repertorie toute les sortie d'un batiment
	 * faitDesTruc supprime Ègalement les peronne qui ont passÈ la porte de sortie
	 * du batiment
	 */
	public void faitDesTruc() {
		for (Entite entite : entites) {
			if (entite instanceof Sortie) {
				lSortie.add(new Position(entite.getX(), entite.getY()));
				for (Entite entite2 : entites) {
					if (entite instanceof Personne) {
						if (entite.getX() == entite2.getX() && entite.getY() == entite2.getY())
							entites.remove(entite2);
					}
				}
			}
		}
	}

	public Position trouveSortieLaPlusProche(Personne p) {
		double x = p.getX();
		double y = p.getY();
		double d = -1;
		Position res = new Position(0, 0);
		for (Position s : lSortie) {
			double newD = Math.sqrt(Math.pow(Math.abs(x - s.getX()), 2) + Math.pow(Math.abs(y - s.getY()), 2));
			if (d < 0 || newD < d) {
				d = newD;
				res = s;
			}
		}
		return res;
	}

	public void dessine() {
		Batiment1();
		faitDesTruc();
		// affiche les entites
		for (Entite entite : entites) {
			entite.dessine();
		}
		// a supprimer TESTS
		for (piece p : lPiece) {
			p.dessine();
		}
	}

	public boolean isGameIsOk() {
		return gameIsOk;
	}

	public void setGameIsOk(boolean gameIsOk) {
		this.gameIsOk = gameIsOk;
	}
}
