package fr.atw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.atw.beans.Equipe;

public class EquipeDaoImpl implements EquipeDao {
	
	private DaoFactory daoFactory;
	
	public EquipeDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void ajouter(Equipe equipe) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO Equipe VALUES (?, ?)");
			
			preparedStatement.setInt(1, equipe.getNumero());
			preparedStatement.setString(2, equipe.getNom());
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
	public List<Equipe> getListeEquipe() {
		List<Equipe> listeEquipes = new ArrayList<Equipe>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultat = null;
		
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			resultat = statement.executeQuery("SELECT * FROM Equipe");
			
			while (resultat.next()) {
				int numero = resultat.getInt("numero");
				String nom = resultat.getString("nom");
				
				Equipe equipe = new Equipe(numero, nom);
				
				listeEquipes.add(equipe);
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
		return listeEquipes;
	}

	@Override
	public void supprimer(Equipe equipe) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM Equipe WHERE numero = ?");
			
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

	@Override
	public void changerNom(Equipe equipe, String nom) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("UPDATE Equipe SET nom = ? WHERE Equipe.numero = ?");
			
			preparedStatement.setString(1, nom);
			preparedStatement.setInt(2, equipe.getNumero());
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
	public List<List<String>> getEquipesCsv() {
		List<List<String>> listeEquipesCsv = new ArrayList<List<String>>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultat = null;
		
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			resultat = statement.executeQuery("SELECT e.nom as nomEquipe, u.nom as nomUtilisateur, u.prenom, u.genre, u.formationPrecedente, u.sitePrecedent "
					+ "							FROM Etudiant u, Equipe e"
					+ "							WHERE u.numeroEquipe = e.numero"
					+ "							ORDER BY e.numero");
			
			while (resultat.next()) {
				List<String> ligne = new ArrayList<String>();
				
				ligne.add(resultat.getString("nomEquipe"));
				ligne.add(resultat.getString("nomUtilisateur"));
				ligne.add(resultat.getString("prenom"));
				ligne.add(resultat.getString("genre"));
				ligne.add(resultat.getString("formationPrecedente"));
				ligne.add(resultat.getString("sitePrecedent"));
				
				listeEquipesCsv.add(ligne);
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
		return listeEquipesCsv;
	}

}
