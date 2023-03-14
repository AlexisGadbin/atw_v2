package fr.atw.servlets;

import java.io.IOException;

import fr.atw.beans.Equipe;
import fr.atw.dao.DaoFactory;
import fr.atw.dao.EquipeDao;
import fr.atw.dao.EtudiantDao;
import fr.atw.formulaires.FormulaireInsertionEtudiant;
import fr.atw.formulaires.FormulaireModificationEquipe;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Etudiants extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private EtudiantDao etudiantDao;
	private EquipeDao equipeDao;
	
	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.etudiantDao = daoFactory.getEtudiantDao();
		this.equipeDao = daoFactory.getEquipeDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("listeEtudiants", this.etudiantDao.getListeEtudiants());
		request.setAttribute("listeEquipes", this.equipeDao.getListeEquipe());
		String page;
		
        if (request.getParameterMap().containsKey("page")) {
        	page = request.getParameter("page");
        } else {
        	page = "";
        }
		
		switch(page) {
			case "etudiants":
				this.getServletContext().getRequestDispatcher("/WEB-INF/etudiants.jsp").forward(request, response);
				break;
			case "equipes":
				this.getServletContext().getRequestDispatcher("/WEB-INF/equipes.jsp").include(request, response);
				break;
			default:
				this.getServletContext().getRequestDispatcher("/WEB-INF/etudiants.jsp").forward(request, response);
				break;
		} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("ajouterEtudiant") != null) {
			FormulaireInsertionEtudiant formulaireInsertionEtudiant = new FormulaireInsertionEtudiant();
			formulaireInsertionEtudiant.verifierEtudiant(this.etudiantDao, request, this.etudiantDao.getListeEtudiants().size()+1);
			
			request.setAttribute("listeEtudiants", this.etudiantDao.getListeEtudiants());
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/etudiants.jsp").forward(request, response);
		} else if(request.getParameter("submitNbEquipes") != null) {
			if(request.getParameter("nbEquipe") != "")
			{
				int diff = Integer.parseInt(request.getParameter("nbEquipe")) - this.equipeDao.getListeEquipe().size();
				
				if(diff > 0 ) {
					for(int i = 0; i<diff; i++) {
						Equipe equipe = new Equipe(this.equipeDao.getListeEquipe().size()+1, "Equipe "+Integer.toString(this.equipeDao.getListeEquipe().size()+1));
						this.equipeDao.ajouter(equipe);
					}
				} else if (diff < 0){
					for(int i=0; i< Math.abs(diff); i++) {
						//this.listeEquipes.get(this.listeEquipes.size()-1).viderEquipe(); TODO IN BDD
						this.equipeDao.supprimer(this.equipeDao.getListeEquipe().get(this.equipeDao.getListeEquipe().size()-1));
					}	
				}

			}
			request.setAttribute("listeEtudiants", this.etudiantDao.getListeEtudiants());
			request.setAttribute("listeEquipes", this.equipeDao.getListeEquipe());
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/equipes.jsp").forward(request, response);
		} else if (request.getParameter("validerEquipe") != null) {
			FormulaireModificationEquipe formulaireModificationEquipe = new FormulaireModificationEquipe();
			formulaireModificationEquipe.modifierEquipe(this.equipeDao, this.etudiantDao.getListeEtudiants(), request);
			
			request.setAttribute("listeEquipes", this.equipeDao.getListeEquipe());
			this.getServletContext().getRequestDispatcher("/WEB-INF/equipes.jsp").forward(request, response);
		}

		
	}

}