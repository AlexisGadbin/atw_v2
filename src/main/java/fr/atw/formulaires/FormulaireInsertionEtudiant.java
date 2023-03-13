package fr.atw.formulaires;

import fr.atw.beans.Etudiant;
import fr.atw.dao.EtudiantDao;
import jakarta.servlet.http.HttpServletRequest;

public class FormulaireInsertionEtudiant {
	private Etudiant etudiant;

	public void verifierEtudiant(EtudiantDao etudiantDao, HttpServletRequest request, int id) {
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String genre = request.getParameter("genre");
		String sitePrecedent = request.getParameter("sitePrecedent");
		String formationPrecedente = request.getParameter("formationPrecedente");
		
		Etudiant etudiant = new Etudiant(id, nom, prenom, genre, sitePrecedent, formationPrecedente);
		etudiantDao.ajouter(etudiant);
	}
	
	public Etudiant getEtudiant() {
		return etudiant;
	}
	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}
	
	


}
