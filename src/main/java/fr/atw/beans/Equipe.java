package fr.atw.beans;

import java.util.ArrayList;
import java.util.List;

public class Equipe {
	private String nom;
	private int numero;
	private List<Etudiant> etudiants;
	
	public Equipe(int numero, String nom) {
		this.nom = nom;
		this.numero = numero;
		this.etudiants = new ArrayList<Etudiant>();
	}

	public String getNom() {
		return nom;
	}

	public int getNumero() {
		return numero;
	}

	public List<Etudiant> getEtudiants() {
		return etudiants;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}
	
	public void ajouterEtudiant(Etudiant etudiant) {
		if(!etudiants.contains(etudiant)) {
			etudiants.add(etudiant);
			etudiant.setNumeroEquipe(this.numero);
		}
	}
	
	public void supprimerEtudiant(Etudiant etudiant) {
		if(etudiants.contains(etudiant) && etudiant.getNumeroEquipe() == this.numero) {
			etudiants.remove(etudiant);
			etudiant.setNumeroEquipe(-1);
		}
	}
	
	public void viderEquipe() {
		for(Etudiant e : etudiants) {
			e.setNumeroEquipe(-1);
		}
		this.etudiants.clear();
	}
	
	public Etudiant getEtudiant(int id) {
		for(int i=0; i<this.etudiants.size(); i++) {
			if(this.etudiants.get(i).getId() == id) {
				return this.etudiants.get(i);
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		String string = "";
		string += this.nom + "\n";
		for(int i=0; i<this.etudiants.size(); i++) {
			string += this.etudiants.get(i).toString() + "\n";
		}
		string += "--------------------";
		return string;
	}
}
