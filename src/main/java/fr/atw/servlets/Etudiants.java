package fr.atw.servlets;

import java.io.IOException;
import java.util.List;

import fr.atw.beans.Equipe;
import fr.atw.beans.Etudiant;
import fr.atw.dao.DaoFactory;
import fr.atw.dao.EquipeDao;
import fr.atw.dao.EtudiantDao;
import fr.atw.formulaires.FormulaireInsertionEtudiant;
import fr.atw.outils.EnregistreurFichier;
import fr.atw.outils.GenerateurEquipes;
import fr.atw.outils.LecteurCSV;
import fr.atw.formulaires.FormulaireModificationEquipe;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

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
			String erreurInsertionEtudiant = formulaireInsertionEtudiant.verifierEtudiant(this.etudiantDao, request, this.etudiantDao.getListeEtudiants().size()+1);
			
			if(erreurInsertionEtudiant != "") {
				request.setAttribute("erreurInsertionEtudiant", erreurInsertionEtudiant);
			}
			request.setAttribute("listeEtudiants", this.etudiantDao.getListeEtudiants());
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/etudiants.jsp").forward(request, response);
		} else if(request.getParameter("submitNbEquipes") != null) {
			if(request.getParameter("nbEquipe") != "")
			{
				int diff = Integer.parseInt(request.getParameter("nbEquipe")) - this.equipeDao.getListeEquipe().size();
				
				if(diff > 0 ) {
					for(int i = 0; i<diff; i++) {
						Equipe equipe = new Equipe(this.equipeDao.getListeEquipe().size()+1, "Equipe "+Integer.toString(this.equipeDao.getListeEquipe().size()+1));
						equipe.setEtudiants(this.etudiantDao.getListeEtudiantsParEquipe(equipe));
						this.equipeDao.ajouter(equipe);
					}
				} else if (diff < 0){
					for(int i=0; i< Math.abs(diff); i++) {
						//this.listeEquipes.get(this.listeEquipes.size()-1).viderEquipe(); TODO IN BDD
						this.etudiantDao.supprimerEquipesAvecId(this.equipeDao.getListeEquipe().get(this.equipeDao.getListeEquipe().size()-1));
						this.equipeDao.supprimer(this.equipeDao.getListeEquipe().get(this.equipeDao.getListeEquipe().size()-1));
					}	
				}

			}
			request.setAttribute("listeEtudiants", this.etudiantDao.getListeEtudiants());
			request.setAttribute("listeEquipes", this.equipeDao.getListeEquipe());
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/equipes.jsp").forward(request, response);
		} else if(request.getParameter("submitEquipesAleatoire") != null) {
			String erreurGenererEquipes = "";
			if(this.equipeDao.getListeEquipe().size() <= 0) {
				erreurGenererEquipes = "Aucunes équipes existantes.";
			}
			else if(this.etudiantDao.getListeEtudiants().size() >= this.equipeDao.getListeEquipe().size()) {
				GenerateurEquipes generateurEquipes = new GenerateurEquipes(this.etudiantDao.getListeEtudiants(), this.equipeDao.getListeEquipe(), this.etudiantDao);
				generateurEquipes.genererEquipesAleatoire();
			} else {
				erreurGenererEquipes = "Il y a moins d'étudiants que d'équipes, génération impossible.";
			}
			request.setAttribute("erreurGenererEquipes", erreurGenererEquipes);
			request.setAttribute("listeEtudiants", this.etudiantDao.getListeEtudiants());
			request.setAttribute("listeEquipes", this.equipeDao.getListeEquipe());
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/equipes.jsp").forward(request, response);
		} else if (request.getParameter("validerEquipe") != null || request.getParameter("supprimerEtudiant") != null) {
			FormulaireModificationEquipe formulaireModificationEquipe = new FormulaireModificationEquipe();
			formulaireModificationEquipe.modifierEquipe(this.equipeDao, this.etudiantDao, request);

			request.setAttribute("listeEquipes", this.equipeDao.getListeEquipe());
			request.setAttribute("listeEtudiants", this.etudiantDao.getListeEtudiants());
			this.getServletContext().getRequestDispatcher("/WEB-INF/equipes.jsp").forward(request, response);
		} else {
			Part part = request.getPart("fichier");
			
			String path = this.getServletContext().getRealPath("/WEB-INF/ressources");
			EnregistreurFichier enregistreur = new EnregistreurFichier(part, path);
			String erreurImportCsv = "";
			if(!enregistreur.getNomFichier().isEmpty()) {
				enregistreur.ecrireFichier();
				LecteurCSV lecteurCsv = new LecteurCSV(path + "\\" + enregistreur.getNomFichier());
				List<List<String>> output = lecteurCsv.getOutput();
				for(int i=0; i<output.size(); i++) {
					if(output.get(i).size() == 5) {
						erreurImportCsv = "Fichier CSV correctement importé !";
						this.etudiantDao.ajouter(new Etudiant(this.etudiantDao.getListeEtudiants().size()+1, output.get(i).get(0), output.get(i).get(1), output.get(i).get(2), output.get(i).get(3), output.get(i).get(4)));
					}
					else {
						erreurImportCsv = "Format de CSV incompatible";
						break;
					}
				}
			} else {
				erreurImportCsv = "Veuillez importer un fichier CSV";
			}
			request.setAttribute("erreurImportCsv", erreurImportCsv);
			this.getServletContext().getRequestDispatcher("/WEB-INF/etudiants.jsp").forward(request, response);

		}

		
	}

}
