package entities;

public class Map {
	private int taillex;
	private int tailley;
	private Personne[] ensPers;
	private Sortie[] ensSortie;
	private Flux[] ensFlux;
	
	Map(int taillex, int tailley, Personne[] ensPers ){
		
	}
	public double getTaillex() {
		return taillex;
	}
	public void setTaillex(int taillex) {
		this.taillex =  taillex;
	}
	public double getTailley() {
		return tailley;
	}
	public void setTailley(int tailley) {
		this.tailley = tailley;
	}
	public Personne[] getEnsPers() {
		return ensPers;
	}
	
	public void setEnsPers(Personne[] ensPers) {
		this.ensPers = ensPers;
	}
	
	public Sortie[] getEnsSortie() {
		return ensSortie;
	}
	public void setEnsSortie(Sortie[] ensSortie) {
		this.ensSortie = ensSortie;
	}
	public Flux[] getEnsFlux() {
		return ensFlux;
	}
	
	//TODO 
	public void setEnsFlux() {
	}
	
	
	
}
