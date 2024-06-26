package fr.atw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.atw.beans.Equipe;
import fr.atw.beans.Etudiant;

public class EtudiantDaoImpl implements EtudiantDao{

	private DaoFactory daoFactory;
	
	public EtudiantDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void ajouter(Etudiant etudiant) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO Etudiant VALUES (?, ?, ?, ?, ?, ?, ?)");
			
			preparedStatement.setInt(1, etudiant.getId());
			preparedStatement.setString(2, etudiant.getNom());
			preparedStatement.setString(3, etudiant.getPrenom());
			preparedStatement.setString(4, etudiant.getGenre());
			preparedStatement.setString(5, etudiant.getFormationPrecedente());
			preparedStatement.setString(6, etudiant.getSitePrecedent());
			preparedStatement.setString(7, null);
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e1) {
			try {
				if(connection != null) {
					connection.rollback();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		} 
		finally {
			try {
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Etudiant> getListeEtudiants() {
		List<Etudiant> listeEtudiants = new ArrayList<Etudiant>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultat = null;
		
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			resultat = statement.executeQuery("SELECT * FROM Etudiant");
			
			while (resultat.next()) {
				int id = resultat.getInt("id");
				String nom = resultat.getString("nom");
				String prenom = resultat.getString("prenom");
				String genre = resultat.getString("genre");
				String formationPrecedente = resultat.getString("formationPrecedente");
				String sitePrecedent = resultat.getString("sitePrecedent");
				int numeroEquipe = resultat.getInt("numeroEquipe");

				
				Etudiant etudiant = new Etudiant(id, nom, prenom, genre, formationPrecedente, sitePrecedent);
				if(numeroEquipe > 0) {
					etudiant.setNumeroEquipe(numeroEquipe);
				}
				
				listeEtudiants.add(etudiant);
			}
		} catch ( SQLException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listeEtudiants;
	}

	@Override
	public void changerEquipe(Etudiant etudiant, int numeroEquipe) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String numEquipe = numeroEquipe == -1 ? null : Integer.toString(numeroEquipe);
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("UPDATE Etudiant SET numeroEquipe = ? WHERE id = ?");
			
			preparedStatement.setString(1, numEquipe);
			preparedStatement.setInt(2, etudiant.getId());

			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e1) {
			try {
				if(connection != null) {
					connection.rollback();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		} 
		finally {
			try {
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public List<Etudiant> getListeEtudiantsEquipe(Equipe equipe) {
		List<Etudiant> listeEtudiants = new ArrayList<Etudiant>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultat = null;
		
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			resultat = statement.executeQuery("SELECT * FROM Etudiant WHERE numeroEquipe = " + equipe.getNumero());
			
			while (resultat.next()) {
				int id = resultat.getInt("id");
				String nom = resultat.getString("nom");
				String prenom = resultat.getString("prenom");
				String genre = resultat.getString("genre");
				String formationPrecedente = resultat.getString("formationPrecedente");
				String sitePrecedent = resultat.getString("sitePrecedent");
				
				Etudiant etudiant = new Etudiant(id, nom, prenom, genre, formationPrecedente, sitePrecedent);
				
				listeEtudiants.add(etudiant);
			}
		} catch ( SQLException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listeEtudiants;
	}

	@Override
	public void viderToutesLesEquipes() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("UPDATE Etudiant SET numeroEquipe = null WHERE 1=1");
			

			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e1) {
			try {
				if(connection != null) {
					connection.rollback();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		} 
		finally {
			try {
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void viderEquipe(Equipe equipe) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("UPDATE Etudiant SET numeroEquipe = null WHERE numeroEquipe=?");
			preparedStatement.setInt(1, equipe.getNumero());
			

			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e1) {
			try {
				if(connection != null) {
					connection.rollback();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		} 
		finally {
			try {
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
