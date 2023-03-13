package fr.atw.dao;

import java.util.List;

import fr.atw.beans.Etudiant;

public interface EtudiantDao {
	void ajouter(Etudiant etudiant);
	List<Etudiant> getListeEtudiants();
}
