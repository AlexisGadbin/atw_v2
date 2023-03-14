package fr.atw.formulaires;

import java.util.List;

import fr.atw.beans.Equipe;
import fr.atw.beans.Etudiant;
import fr.atw.dao.EquipeDao;
import jakarta.servlet.http.HttpServletRequest;

public class FormulaireModificationEquipe {
	Equipe equipe;
	EquipeDao equipeDao;
	List<Etudiant> listeEtudiants;
	
	public void modifierEquipe(EquipeDao equipeDao, List<Etudiant> listeEtudiants, HttpServletRequest requete) {
		this.listeEtudiants = listeEtudiants;
		this.equipeDao = equipeDao;

		
		int numeroEquipe = Integer.parseInt(requete.getParameter("numeroEquipe"));
		String nomEquipe = requete.getParameter("nomEquipe");
		String nouvelEtudiant = requete.getParameter("ajouterEtudiantEquipe");
		String idEtudiantASupprimer = requete.getParameter("supprimerEtudiant");
		
		for(Equipe e : equipeDao.getListeEquipe()) {
			if(numeroEquipe == e.getNumero()) {
				this.equipe = e;
			}
		}
		
		System.out.println(nouvelEtudiant);
		
		if(idEtudiantASupprimer != null) {
//			Etudiant etudiant = this.equipe.getEtudiant(Integer.parseInt(idEtudiantASupprimer));
//			if(etudiant != null) {
//				this.equipe.supprimerEtudiant(etudiant);
//			}
		} else if (!nouvelEtudiant.equals("null")) {
//			System.out.println("ca rentre");
//			for(Etudiant e : this.listeEtudiants) {
//				if(e.getId() == Integer.parseInt(nouvelEtudiant)) {
//					this.equipe.ajouterEtudiant(e);
//				}
//			}
		} else if (!nomEquipe.equals(this.equipe.getNom())) {
			this.equipeDao.changerNom(equipe, nomEquipe);
		} 
	}

}
