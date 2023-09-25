package fr.atw.formulaires;

import fr.atw.beans.Etudiant;
import fr.atw.dao.EtudiantDao;
import jakarta.servlet.http.HttpServletRequest;

public class FormulaireInsertionEtudiant {
	private Etudiant etudiant;

	public String verifierEtudiant(EtudiantDao etudiantDao, HttpServletRequest request, int id) {
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String genre = request.getParameter("genre");
		String sitePrecedent = request.getParameter("sitePrecedent");
		String formationPrecedente = request.getParameter("formationPrecedente");
		
		String  message = "";
		if(nom == "") {
			message = "Le nom ne peut pas être vide";
		} else if (prenom == "") {
			message = "Le prenom ne peut pas être vide";
		} else {
			Etudiant etudiant = new Etudiant(id, nom, prenom, genre, sitePrecedent, formationPrecedente);
			etudiantDao.ajouter(etudiant);	
		}
		
		return message;

	}
	
	public Etudiant getEtudiant() {
		return etudiant;
	}
	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}
	
	


}
