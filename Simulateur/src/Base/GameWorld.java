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
		// murs contour b‚timent
		entites.add(new Mur(0.005, 0.5, 0.005, 0.5));
		entites.add(new Mur(0.995, 0.5, 0.005, 0.5));
		entites.add(new Mur(0.5, 0.005, 0.5, 0.005));
		entites.add(new Mur(0.2375, 0.995, 0.2375, 0.005));
		entites.add(new Mur(0.7625, 0.995, 0.2375, 0.005));
		entites.add(new Sortie(0.5, 0.995, 0.025, 0.005));
		// mur additionnels
		entites.add(new Mur(0.15, 0.3, 0.15, 0.005));
		entites.add(new Mur(0.9, 0.8, 0.1, 0.005));
		entites.add(new Mur(0.3, 0.6, 0.3, 0.005));
		entites.add(new Mur(0.5, 0.05, 0.005, 0.05));
		entites.add(new Mur(0.3, 0.85, 0.005, 0.15));
		// colonnes
//		entites.add(new Colonne(0.5, 0.8));
	}

	public void AnalyseBatiment() {
		List<Mur> murVerticale = new LinkedList<Mur>();
		List<Mur> murHorizontale = new LinkedList<Mur>();
		List<Sortie> ls = new LinkedList<Sortie>();
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
		for (Mur m : murVerticale) {
			double lo = (1 - (2 * m.getLongueur())) / 2;
			if (m.getY() + m.getLongueur() > 0.95) {
				ls.add(new Sortie(m.getX(), lo, m.getLargeur(), lo));
			} else {
				ls.add(new Sortie(m.getX(), 2 * m.getLongueur() + lo, m.getLargeur(), lo));
			}
		}
		for (Mur m : murHorizontale) {
			double la = (1 - (2 * m.getLargeur())) / 2;
			if (m.getX() + m.getLargeur() > 0.95) {
				ls.add(new Sortie(la, m.getY(), 2*la, m.getLongueur()));
			} else {
				ls.add(new Sortie(2 * m.getLargeur() + la, m.getY(), 2*la, m.getLongueur()));
			}
		}

		double x = 0;
		while (!murVerticale.isEmpty()) {
			Mur murXpetit = null;
			for (Mur m : murVerticale) {
				if (murXpetit == null || (m.getX() < murXpetit.getX() && murXpetit.getX() > x))
					murXpetit = m;
			}
			if (murXpetit != null) {
				piece p = new piece((murXpetit.getX() - x) / 2 + x, 0.5, (murXpetit.getX() - x) / 2, 0.5);
				lPiece.add(p);
				x = murXpetit.getX();
				murVerticale.remove(murXpetit);
			}
		}
		List<piece> lAjoute = new LinkedList<piece>();
		List<piece> lSupprime = new LinkedList<piece>();
		for (piece p : lPiece) {
			for (Mur m : murHorizontale) {
				if (((m.getX() < (p.getX() + p.getTaillex())) && (m.getX() > (p.getX() - p.getTaillex())))
						|| ((m.getX() - m.getLargeur() < (p.getX() + p.getTaillex())
								&& m.getX() - m.getLargeur() > (p.getX() - p.getTaillex())))
						|| ((m.getX() + m.getLargeur() < (p.getX() + p.getTaillex()))
								&& m.getX() + m.getLargeur() > (p.getX() - p.getTaillex()))
						|| ((p.getX() < (m.getX() + m.getLargeur()) && p.getX() > (m.getX() - m.getLargeur())))) {

					piece p1 = new piece(p.getX(), (p.getHauteur() + m.getY()) / 2, p.getTaillex(),
							(p.getHauteur() - m.getY()) / 2);
					piece p2 = new piece(p.getX(), m.getY() / 2, p.getTaillex(), m.getY() / 2);
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
		lSupprime.clear();
		lAjoute.clear();
		for (piece p1 : lPiece) {
			// TODO: a supprimer
			for (Sortie s : ls) {
				p1.getSortie().add(s);
			}
			for (piece p2 : lPiece) {
				if ((p1.getX() == p2.getX()) && (p1.getY() - p1.getTailley() < p2.getY() + p2.getTailley())
						&& (p1.getY() + p1.getTailley() > p2.getY() + p2.getTailley())
						&& (p1.getY() + p1.getTailley() != p2.getY() - p2.getTailley())
						&& (p1.getY() - p1.getTailley() != p2.getY() - p2.getTailley())) {
					double hauteur = (p2.getY() + p2.getTailley()) - (p1.getY() - p1.getTailley());
					double yp3 = ((p2.getY() + p2.getTailley()) + (p1.getY() - p1.getTailley())) / 2;
					System.out.println(yp3);
					piece p3 = new piece(p1.getX(), yp3, p1.getTaillex(), hauteur / 2);
					lAjoute.add(p3);
					lSupprime.add(p1);
					lSupprime.add(p2);
				}
			}
		}
		for (piece s : lSupprime) {
			lPiece.remove(s);
		}
		for (piece a : lAjoute) {
			lPiece.add(a);
		}
		for (piece p1 : lPiece) {
			boolean b1 = false;
			boolean b2 = false;
			for (piece p2 : lPiece) {
				if (p1.getX() == p2.getX() && p1 != p2) {
					if (p1.getY() - p1.getTailley() == p2.getY() - p2.getTailley())
						b1 = true;
					if (p1.getY() + p1.getTailley() == p2.getY() + p2.getTailley())
						b2 = true;
				}
				System.out.println(p1.getY() + p1.getTailley());
				System.out.println(p1.getY() - p1.getTailley());
			}
			if (b1 && b2)
				lSupprime.add(p1);
		}
		for (piece s : lSupprime) {
			lPiece.remove(s);
		}
//		for (piece p : lPiece) {
//			Position hG = new Position(p.getX() - p.getLargeur(), p.getY() + p.getHauteur());
//			Position hD = new Position(p.getX() + p.getLargeur(), p.getY() + p.getHauteur());
//			Position bG = new Position(p.getX() - p.getLargeur(), p.getY() - p.getHauteur());
//			Position bD = new Position(p.getX() + p.getLargeur(), p.getY() - p.getHauteur());
//			if (posApartientMur(hG) == null && posApartientMur(hD) == null)
//				p.getSortie().add(new Sortie(p.getX(), hG.getY(), p.getLargeur(), 0.005));
//			if (posApartientMur(bG) == null && posApartientMur(bD) == null)
//				p.getSortie().add(new Sortie(p.getX(), bG.getY(), p.getLargeur(), 0.005));
////			if (posApartientMur(hG)==null && posApartientMur(bG)==null) 
////				p.setSortie(new Sortie((bG.getX()+bD.getX())/2,bG.getY(),bD.getX()-bG.getX(),0.005));
//		}
	}

	public List<Sortie> posApartientMur(Position pos) {
		List<Sortie> l = new LinkedList<Sortie>();
		for (Entite e : entites) {
			if (e instanceof Mur) {
				if (e.getX() > 0.005 && e.getX() < 0.995 && e.getY() > 0.005 && e.getY() < 0.995) {

				}
			}
		}
		return l;
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
