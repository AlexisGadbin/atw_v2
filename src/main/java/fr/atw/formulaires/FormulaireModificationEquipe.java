package fr.atw.formulaires;

import java.util.List;

import fr.atw.beans.Equipe;
import fr.atw.beans.Etudiant;
import fr.atw.dao.EquipeDao;
import fr.atw.dao.EtudiantDao;
import jakarta.servlet.http.HttpServletRequest;

public class FormulaireModificationEquipe {
	Equipe equipe;
	Etudiant etudiant;
	EquipeDao equipeDao;
	List<Etudiant> listeEtudiants;
	EtudiantDao etudiantDao;
	
	public void modifierEquipe(EquipeDao equipeDao, EtudiantDao etudiantDao, HttpServletRequest requete) {
		this.listeEtudiants = etudiantDao.getListeEtudiants();
		this.equipeDao = equipeDao;
		this.etudiantDao = etudiantDao;

		
		int numeroEquipe = Integer.parseInt(requete.getParameter("numeroEquipe"));
		String nomEquipe = requete.getParameter("nomEquipe");
		String nouvelEtudiant = requete.getParameter("ajouterEtudiantEquipe");
		String idEtudiantASupprimer = requete.getParameter("supprimerEtudiant");
		
		for(Equipe e : equipeDao.getListeEquipe()) {
			if(numeroEquipe == e.getNumero()) {
				this.equipe = e;
			}
		}
		if(idEtudiantASupprimer != null) {
			for(Etudiant e : etudiantDao.getListeEtudiants()) {
				if(e.getId() == Integer.parseInt(idEtudiantASupprimer) ) {
					this.etudiant = e;
				}
			}
		}

		
		
		if(idEtudiantASupprimer != null) {
			if(this.etudiant != null) {
				this.etudiantDao.changerEquipe(this.etudiant, -1);
			}
		} else if (!nouvelEtudiant.equals("null")) {
			for(Etudiant e : etudiantDao.getListeEtudiants()) {
				if(e.getId() == Integer.parseInt(nouvelEtudiant) ) {
					this.etudiant = e;
				}
			}
			this.etudiantDao.changerEquipe(this.etudiant, numeroEquipe);
		} else if (!nomEquipe.equals(this.equipe.getNom())) {
			this.equipeDao.changerNom(equipe, nomEquipe);
		} 
	}

}
