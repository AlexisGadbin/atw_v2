package fr.atw.outils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.atw.beans.Equipe;
import fr.atw.beans.Etudiant;
import fr.atw.dao.EtudiantDao;

public class GenerateurEquipes {
	private int nbEquipes;
	private EtudiantDao etudiantDao;
	private List<Etudiant> etudiants;
	private List<Equipe> equipes;
	
	public GenerateurEquipes(List<Etudiant> etudiants, List<Equipe> equipes, EtudiantDao etudiantDao) {
		this.nbEquipes = equipes.size();
		this.etudiants = etudiants;
		this.equipes = equipes;
		this.etudiantDao = etudiantDao;
	}

	public List<Equipe> getEquipes() {
		return equipes;
	}

	public void setNbEquipes(int nbEquipes) {
		this.nbEquipes = nbEquipes;
	}

	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}
	
	public void genererEquipesAleatoire()
	{
		int nbEtudiantsEquipe = this.etudiants.size() / this.nbEquipes;
		int nbEtudiantsRestant = this.etudiants.size() % this.nbEquipes;
		
		ArrayList<Integer> random = new ArrayList<Integer>();
		
		this.etudiantDao.supprimerEquipes();
		
//		for(int i=0; i < this.nbEquipes; i++)
//		{
//			for(int j=0; j < nbEtudiantsEquipe; j++)
//			{
//				Random r = new Random();
//				int randomInt = r.nextInt(this.etudiants.size());
//				while(random.contains(randomInt)) {
//					randomInt = r.nextInt(this.etudiants.size());
//				}
//				
//				random.add(randomInt);
//				
//				Etudiant etudiant = this.etudiants.get(randomInt);
//				this.etudiantDao.changerEquipe(etudiant, i);
//				//this.equipes.get(i).ajouterEtudiant(etudiant);
//			}
//		}
		
		for(Equipe equipe : this.equipes) {
			for(int i=0; i<nbEtudiantsEquipe; i++) {
				Random r = new Random();
				int randomInt = r.nextInt(this.etudiants.size());
				while(random.contains(randomInt)) {
					randomInt = r.nextInt(this.etudiants.size());
				}
				random.add(randomInt);
				
				Etudiant etudiant = this.etudiants.get(randomInt);
				this.etudiantDao.changerEquipe(etudiant, equipe);
				equipe.ajouterEtudiant(etudiant);
				
			}
		}
		
		for(int i=0; i < nbEtudiantsRestant; i++) {
			for(int j=0; j < this.etudiants.size(); j++) {
				if(!random.contains(j)) {
					this.etudiantDao.changerEquipe(this.etudiants.get(j), this.equipes.get(i));
					this.equipes.get(i).ajouterEtudiant(this.etudiants.get(j));
					this.etudiants.get(j).setNumeroEquipe(i+1);
					random.add(j);
					break;
				}
			}	
		}
		
	}
	
	
	

}
