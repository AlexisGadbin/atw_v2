package fr.atw.dao;

import java.util.List;

import fr.atw.beans.Equipe;
import fr.atw.beans.Etudiant;

public interface EtudiantDao {
	void ajouter(Etudiant etudiant);
	void changerEquipe(Etudiant etudiant, int numeroEquipe);
	List<Etudiant> getListeEtudiants();
	List<Etudiant> getListeEtudiantsEquipe(Equipe equipe);
	void viderToutesLesEquipes();
	void viderEquipe(Equipe equipe);
}
