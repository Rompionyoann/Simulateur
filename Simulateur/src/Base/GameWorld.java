package Base;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
	private List<Sortie> lSortie;
	private static List<Entite> entites;
	private List<piece> lPiece = new LinkedList<piece>();
	private List<Personne> listPersonne = new LinkedList<Personne>();
	private double taillePersonne = 0.01;
	private Boolean pausePlay =false;
	private Instant debut;
	private long timer;
	private Boolean over=true;
	
	public long getTimer() {
		return timer;
	}

	public void setTimer(long timer) {
		this.timer = timer;
	}

	public Boolean getPausePlay() {
		return pausePlay;
	}

	public void setPausePlay(Boolean pausePlay) {
		this.pausePlay = pausePlay;
	}

	/**
	 * Constructor of the Gameworld
	 */
	public GameWorld() {
		entites = new LinkedList<Entite>();
		lSortie = new LinkedList<Sortie>();
		Batiment1();
		for (Entite e : entites) {
			if (e instanceof Sortie) {
				
				lSortie.add((Sortie) e);
			}
		}
		//this.spawnPersonne(250);
		AnalyseBatiment();
	}

	/**
	 * Gestion des interactions clavier avec l'utilisateur
	 * 
	 * @param key
	 *            Touche pressee par l'utilisateur
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
			System.out.println("play/pause");
			this.setPausePlay(!this.pausePlay);
			this.setDebut(Instant.now());
			break;
		case 's':
			derniereTouche = 's';
			// TODO
		default:
			break;
		}
	}

	public Instant getDebut() {
		return debut;
	}

	public void setDebut(Instant debut) {
		this.debut = debut;
	}

	/**
	 * Gestion des interactions souris avec l'utilisateur (la souris a été cliquée)
	 * 
	 * @param x
	 *            position en x de la souris au moment du clic
	 * @param y
	 *            position en y de la souris au moment du clic
	 */
	public void processMouseClick(double x, double y) {
		switch (derniereTouche) {
		case 'm':
			entites.add(new Mur(x, y, 0.01, 0.05));
			derniereTouche = '?';
			break;
		case 'p':
			Personne p = new Personne(x, y, taillePersonne);
			entites.add(p);
			p.setSortieLaPlusProche(trouveSortieLaPlusProche(p));
			listPersonne.add(p);
			derniereTouche = '?';
			break;
		}
	}

	/**
	 * Action for all the entities and elements of the game
	 */
	public void step() {
		//System.out.println(listPersonne.size());
		faitDesTruc();
		for (Entite entite : GameWorld.entites) {
			if (entite instanceof Personne) {
				((Personne) entite).setNbStep(((Personne) entite).getNbStep() + 1);
				if (((Personne) entite).getNbStep() % 1 == 0) {
					aProximitedunePersonne((Personne) entite);
				}
			}
			entite.step();
		}
		
		if(over && this.gameIsOver()) {
			this.setTimer((Instant.now().minus(debut.toEpochMilli(),  ChronoUnit.MILLIS)).toEpochMilli());
			System.out.println(timer);
			over = false;
		}
	}

	public Boolean getOver() {
		return over;
	}

	public void setOver(Boolean over) {
		this.over = over;
	}

	public double getTaillePersonne() {
		return taillePersonne;
	}

	public void setTaillePersonne(double taillePersonne) {
		this.taillePersonne = taillePersonne;
	}

	/**
	 * Draw all entities and elements of the game
	 */
	public void Batiment1() {
		// murs contour b�timent
		entites.add(new Mur(0.005, 0.5, 0.005, 0.5));
		entites.add(new Mur(0.995, 0.5, 0.005, 0.5));
		entites.add(new Mur(0.5, 0.005, 0.5, 0.005));
		entites.add(new Mur(0.5, 0.995, 0.5, 0.005));
		entites.add(new Sortie(0.7, 0.995, 0.025, 0.005));
		entites.add(new Sortie(0.995, 0.2, 0.005, 0.025));
		// mur additionnels
		entites.add(new Mur(0.3, 0.3, 0.3, 0.005));
		entites.add(new Mur(0.8, 0.8, 0.2, 0.005));
		entites.add(new Mur(0.4, 0.6, 0.4, 0.005));
		entites.add(new Mur(0.15, 0.7, 0.15, 0.005));
		//entites.add(new Mur(0.15, 0.9, 0.15, 0.005));
		entites.add(new Mur(0.6, 0.1, 0.005, 0.1));
		entites.add(new Mur(0.5, 0.85, 0.005, 0.15));
		// colonnes
		// entites.add(new Colonne(0.5, 0.8));
	}

	public void Batiment2() {
		this.setTaillePersonne(0.05);
		// murs contour b�timent
		entites.add(new Mur(0.005, 0.5, 0.005, 0.5));
		entites.add(new Mur(0.995, 0.5, 0.005, 0.5));
		entites.add(new Mur(0.5, 0.005, 0.5, 0.005));
		entites.add(new Mur(0.5, 0.995, 0.5, 0.005));
		entites.add(new Sortie(0.6, 0.995, 0.025, 0.005));
		
	}
	
	public void Batiment3() {
		// murs contour b�timent
		entites.add(new Mur(0.005, 0.5, 0.005, 0.5));
		entites.add(new Mur(0.995, 0.5, 0.005, 0.5));
		entites.add(new Mur(0.5, 0.005, 0.5, 0.005));
		entites.add(new Mur(0.2375, 0.995, 0.2375, 0.005));
		entites.add(new Mur(0.7625, 0.995, 0.2375, 0.005));
		entites.add(new Sortie(0.5, 0.995, 0.025, 0.005));
		// mur additionnels
//		entites.add(new Mur(0.45, 0.1, 0.45, 0.005));
		entites.add(new Mur(0.6, 0.3, 0.4, 0.005));
//		entites.add(new Mur(0.45, 0.3, 0.45, 0.005));
//		entites.add(new Mur(0.55, 0.4, 0.45, 0.005));
		entites.add(new Mur(0.3, 0.6, 0.3, 0.005));
//		entites.add(new Mur(0.55, 0.6, 0.45, 0.005));
//		entites.add(new Mur(0.45, 0.7, 0.45, 0.005));
//		entites.add(new Mur(0.6, 0.8, 0.4, 0.005));
		entites.add(new Mur(0.2, 0.9, 0.005, 0.1));
		entites.add(new Mur(0.8, 0.95, 0.005, 0.05));
		
	}
	public void Batiment4() {
		// murs contour b�timent
		entites.add(new Mur(0.005, 0.5, 0.005, 0.5));
		entites.add(new Mur(0.995, 0.5, 0.005, 0.5));
		entites.add(new Mur(0.5, 0.005, 0.5, 0.005));
		entites.add(new Mur(0.2375, 0.995, 0.2375, 0.005));
		entites.add(new Mur(0.7625, 0.995, 0.2375, 0.005));
		entites.add(new Sortie(0.005, 0.5, 0.005, 0.025));
		// mur additionnels

		entites.add(new Mur(0.9, 0.6, 0.005, 0.4));
		entites.add(new Mur(0.8, 0.4, 0.005, 0.4));
		entites.add(new Mur(0.7, 0.6, 0.005, 0.4));
		entites.add(new Mur(0.6, 0.4, 0.005, 0.4));
		entites.add(new Mur(0.5, 0.6, 0.005, 0.4));
		entites.add(new Mur(0.4, 0.4, 0.005, 0.4));
		entites.add(new Mur(0.3, 0.6, 0.005, 0.4));
		entites.add(new Mur(0.2, 0.4, 0.005, 0.4));
		
		
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
				ls.add(new Sortie(la, m.getY(), la, m.getLongueur()));
			} else {
				ls.add(new Sortie(2 * m.getLargeur() + la, m.getY(), la, m.getLongueur()));
			}
		}
		Map<Sortie, List<Double>> map = new LinkedHashMap<Sortie, List<Double>>();
		for (Sortie s : ls) {
			List<Double> l = new LinkedList<Double>();
			map.put(s, l);
			if (!s.isVertical()) {
				for (Mur mur : murVerticale) {
					if (mur.getX() < s.getX() + s.getLongueurHori() && mur.getX() > s.getX() - s.getLongueurHori()
							&& mur.getX() < 0.995) {
						map.get(s).add(mur.getX());
					}
				}
			} else {
				for (Mur mur : murHorizontale) {
					if (mur.getY() < s.getY() + s.getLongueurVert() && mur.getY() > s.getY() - s.getLongueurVert()
							&& mur.getX() < 0.995) {
						map.get(s).add(mur.getY());
					}
				}
			}
		}
		for (Sortie s : map.keySet()) {
			double minValPrec;
			if (s.isVertical()) {
				minValPrec = s.getY() - s.getLongueurVert();
			} else {
				minValPrec = s.getX() - s.getLongueurHori();
			}
			while (!map.get(s).isEmpty()) {
				double minVal = 0;
				for (double val : map.get(s)) {
					if (val < minVal || minVal == 0) {
						minVal = val;
					}
				}
				if (s.isVertical()) {
					ls.add(new Sortie(s.getX(), (minVal + minValPrec) / 2, s.getLongueurHori(),
							(minVal - minValPrec) / 2));
				} else {
					ls.add(new Sortie((minVal + minValPrec) / 2, s.getY(), (minVal - minValPrec) / 2,
							s.getLongueurVert()));
				}
				minValPrec = minVal;
				map.get(s).remove(minVal);
			}
			if (s.isVertical()) {
				ls.add(new Sortie(s.getX(), (s.getY() + s.getLongueurVert() + minValPrec) / 2, s.getLongueurHori(),
						(s.getY() + s.getLongueurVert() - minValPrec) / 2));
			} else {
				ls.add(new Sortie((s.getX() + s.getLongueurHori() + minValPrec) / 2, s.getY(),
						(s.getX() + s.getLongueurHori() - minValPrec) / 2, s.getLongueurVert()));
			}
			ls.remove(s);
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
			for (piece p2 : lPiece) {
				if ((p1.getX() == p2.getX()) && (p1.getY() - p1.getTailley() < p2.getY() + p2.getTailley())
						&& (p1.getY() + p1.getTailley() > p2.getY() + p2.getTailley())
						&& (p1.getY() + p1.getTailley() != p2.getY() - p2.getTailley())
						&& (p1.getY() - p1.getTailley() != p2.getY() - p2.getTailley())) {
					double hauteur = (p2.getY() + p2.getTailley()) - (p1.getY() - p1.getTailley());
					double yp3 = ((p2.getY() + p2.getTailley()) + (p1.getY() - p1.getTailley())) / 2;
					
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
			}
			if (b1 && b2)
				lSupprime.add(p1);
		}
		for (piece s : lSupprime) {
			lPiece.remove(s);
		}
		lSupprime.clear();
		for (piece p : lPiece) {
			for (Sortie s : ls) {
				if (posApartientPiece(new Position(s.getX(), s.getY()), p))
					p.getSortie().add(s);
			}
			for (Sortie s : lSortie) {
				if (posApartientPiece(new Position(s.getX(), s.getY()), p))
					p.getSortie().add(s);
			}
			List<Sortie> sortiesupp = new LinkedList<Sortie>();
			for (Sortie s :p.getSortie()) {
				if (s.getX()>p.getX()-p.getTaillex()+0.05 && s.getX()<p.getX()+p.getTaillex()-0.05 && s.getY()>p.getY()-p.getTailley()+0.05 && s.getY()<p.getY()+p.getTailley()-0.05)
					sortiesupp.add(s);
			}
			for (Sortie s: sortiesupp) {
				p.getSortie().remove(s);
			}
			sortiesupp =new LinkedList<Sortie>();
			
			
			double distanceSortie = -1;
			
			Sortie vraiSortie = null;
			for (Sortie s : p.getSortie()) {
				for (Sortie p2 : lSortie) {
					double val = Math.sqrt(
							Math.pow(Math.abs(p2.getX() - s.getX()), 2) + Math.pow(Math.abs(p2.getY() - s.getY()), 2));
					if (val < distanceSortie || vraiSortie == null) {
						vraiSortie = s;
						distanceSortie = val;
					}
				}
			}
			p.setVraiSortie(vraiSortie);
		}
	}

	public Boolean posApartientPiece(Position pos, piece p) {
		return (pos.getX() > p.getX() - p.getTaillex() - 0.005 && pos.getX() < p.getX() + p.getTaillex() + 0.005
				&& pos.getY() > p.getY() - p.getTailley() - 0.005 && pos.getY() < p.getY() + p.getTailley() + 0.005);

	}
	// public void AjoutePersonne(int n) {
	// for (int i=0; i<n; i++) {
	// entite.add(Personne())
	// }
	// }

	/*
	 * faitDesTruc est une fonction qui repertorie toute les sortie d'un batiment
	 * faitDesTruc supprime �galement les peronne qui ont pass� la porte de sortie
	 * du batiment
	 */
	public void faitDesTruc() {
		List<Entite> supp = new LinkedList<Entite>();
		for (Entite entite : entites) {
			if (entite instanceof Sortie) {
				for (Entite entite2 : entites) {
					if (entite2 instanceof Personne) {
						if (entite2.getX() < entite.getX() + ((Sortie) entite).getLongueurHori()
								&& entite2.getX() > entite.getX() - ((Sortie) entite).getLongueurHori()
								&& entite2.getY() < entite.getY() + ((Sortie) entite).getLongueurVert() + 0.005
								&& entite2.getY() > entite.getY() - ((Sortie) entite).getLongueurVert() - 0.005)
							supp.add(entite2);
					}
				}
			}
			if (entite instanceof Personne) {
				((Personne) entite).setSortieLaPlusProche(trouveSortiePiece(((Personne) entite)));

			}
		}
		for (Entite e : supp) {
			entites.remove(e);
			listPersonne.remove((Personne)e);
		}
	}

	public Position trouveSortiePiece(Personne pos) {
		Position result = new Position(0, 0);
		double pluspetiteVal = -1;
		for (piece p : lPiece) {
			for (Sortie s : lSortie) {
				double val = Math.sqrt(Math.pow(Math.abs(p.getVraiSortie().getX() - s.getX()), 2)
						+ Math.pow(Math.abs(p.getVraiSortie().getY() - s.getY()), 2));
				if ((pluspetiteVal < 0 || val < pluspetiteVal) && pos.getX() > p.getX() - p.getTaillex() - 0.005
						&& pos.getX() < p.getX() + p.getTaillex() + 0.005
						&& pos.getY() > p.getY() - p.getTailley() - 0.005
						&& pos.getY() < p.getY() + p.getTailley() + 0.005) {
					result = new Position(p.getVraiSortie().getX(), p.getVraiSortie().getY());
					pluspetiteVal = val;
				}
			}
		}
		return result;
	}

	public void aProximitedunePersonne(Personne p) {
		if (p.getaProximite() != null) {
			p.setaProximite(new LinkedList<Personne>());

		}
		if (this.listPersonne != null) {
			for (Entite c : this.entites) {
				if (c instanceof Personne && c != p) {
					double a = Math.abs(p.getX() - c.getX());
					double b = Math.abs(p.getY() - c.getY());
					double d = Math.sqrt(a * a + b * b);
					// System.out.println(d);
					// System.out.println(p.getProximityRadius());
					if (d <= p.getProximityRadius()) {
						// System.out.println(d);
						
						p.aProximiteAdd((Personne) c);
					}
					p.aProximiteRemove((Personne) c);
				}
			}
		}

	}

	public Position trouveSortieLaPlusProche(Personne p) {
		double x = p.getX();
		double y = p.getY();
		double d = -1;
		Position res = new Position(0, 0);
		for (Sortie s : lSortie) {
			double newD = Math.sqrt(Math.pow(Math.abs(x - s.getX()), 2) + Math.pow(Math.abs(y - s.getY()), 2));
			if (d < 0 || newD < d) {
				d = newD;
				res = new Position(s.getX(), s.getY());
			}
		}
		return res;
	}

	public void dessine() {
		// affiche les entites
		for (Entite entite : entites) {
			entite.dessine();
		}
		// a supprimer TESTS
		// for (piece p : lPiece) {
		// p.dessine();
		// }
	}

	public boolean isGameIsOk() {
		return gameIsOk;
	}

	public void setGameIsOk(boolean gameIsOk) {
		this.gameIsOk = gameIsOk;
	}
	public void spawnPersonne(int n) {
		for(int i=0; i<=n; i++) {
			double x = Math.random();
			double y =Math.random();
			Personne nouvelle=new Personne(x,y,0.01);
			
			this.entites.add(nouvelle);
		}
	}
	public void clearAllPersonne() {
		this.listPersonne.clear();
			
		}
	
	public Boolean gameIsOver() {
		Boolean ilrestedumonde=false;
		for(Entite e:this.entites) {
			if(e instanceof Personne)
				ilrestedumonde=true;
		}
		if(!ilrestedumonde) {
			
			return true;
		}else {
			return false;
		}
		}
}
