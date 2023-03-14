package fr.atw.dao;

import java.util.List;

import fr.atw.beans.Equipe;
import fr.atw.beans.Etudiant;

public interface EtudiantDao {
	void ajouter(Etudiant etudiant);
	void changerEquipe(Etudiant etudiant, Equipe equipe);
	List<Etudiant> getListeEtudiants();
	List<Etudiant> getListeEtudiantsParEquipe(Equipe equipe);
	void supprimerEquipes();
	void supprimerEquipesAvecId(Equipe equipe);
}
