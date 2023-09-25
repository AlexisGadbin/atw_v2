package fr.atw.dao;

import java.util.List;

import fr.atw.beans.Equipe;

public interface EquipeDao {
	void ajouter(Equipe equipe);
	void supprimer(Equipe equipe);
	void changerNom(Equipe equipe, String nom);
	List<Equipe> getListeEquipe();
	List<List<String>> getEquipesCsv();
}
