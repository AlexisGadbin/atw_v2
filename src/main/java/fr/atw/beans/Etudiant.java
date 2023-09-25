package fr.atw.beans;

public class Etudiant {
	private int id;
	private String nom;
	private String prenom;
	private String genre;
	private String sitePrecedent;
	private String formationPrecedente;
	private int numeroEquipe;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Etudiant(int id, String nom, String prenom, String genre, String sitePrecedent, String formationPrecedente) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.genre = genre;
		this.sitePrecedent = sitePrecedent;
		this.formationPrecedente = formationPrecedente;
		this.numeroEquipe = -1;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String getSitePrecedent() {
		return sitePrecedent;
	}
	public void setSitePrecedent(String sitePrecedent) {
		this.sitePrecedent = sitePrecedent;
	}
	
	public String getFormationPrecedente() {
		return formationPrecedente;
	}
	public void setFormationPrecedente(String formationPrecedente) {
		this.formationPrecedente = formationPrecedente;
	}

	public int getNumeroEquipe() {
		return numeroEquipe;
	}

	public void setNumeroEquipe(int numeroEquipe) {
		this.numeroEquipe = numeroEquipe;
	}
	
	@Override
	public String toString() {
		return this.nom.toUpperCase() + " " + this.prenom + " Equipe " + this.numeroEquipe;
	}
	
	
	
}
