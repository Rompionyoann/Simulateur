package entities;

import java.util.LinkedList;
import java.util.List;
import Base.Entite;
import Base.Position;
import Base.StdDraw;

public class piece extends Entite {
	private double taillex;
	private double tailley;
	private double hauteur;
	private double largeur;
	private List<Sortie> sortie;
	private Sortie vraiSortie;

	public Sortie getVraiSortie() {
		return vraiSortie;
	}

	public void setVraiSortie(Sortie vraiSortie) {
		this.vraiSortie = vraiSortie;
	}

	public List<Sortie> getSortie() {
		return sortie;
	}

	public piece(double x, double y, double taillex, double tailley) {
		super(x, y);
		this.taillex = taillex;
		this.tailley = tailley;
		hauteur = 2 * tailley;
		largeur = 2 * taillex;
		sortie = new LinkedList<Sortie>();
	}

	public double getTaillex() {
		return taillex;
	}

	public void setTaillex(int taillex) {
		this.taillex = taillex;
	}

	public double getTailley() {
		return tailley;
	}

	public void setTailley(int tailley) {
		this.tailley = tailley;
	}

	@Override
	public void step() {
	}

	@Override
	public void dessine() {
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledEllipse(this.getX(), this.getY(), taillex, tailley);
		if (vraiSortie != null) {
			System.out.println(vraiSortie.getX()+" "+vraiSortie.getY());
			StdDraw.setPenColor(StdDraw.ORANGE);
			StdDraw.filledRectangle(vraiSortie.getX(), vraiSortie.getY(), vraiSortie.getLongueurHori(),
					vraiSortie.getLongueurVert());
		}
	}

	public double getHauteur() {
		return hauteur;
	}

	public void setHauteur(double hauteur) {
		this.hauteur = hauteur;
	}

	public double getLargeur() {
		return largeur;
	}

	public void setLargeur(double largeur) {
		this.largeur = largeur;
	}

	public void setTaillex(double taillex) {
		this.taillex = taillex;
	}

	public void setTailley(double tailley) {
		this.tailley = tailley;
	}

}
