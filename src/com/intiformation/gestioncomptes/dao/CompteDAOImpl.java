package com.intiformation.gestioncomptes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.gestioncomptes.modele.Client;
import com.intiformation.gestioncomptes.modele.Compte;

public class CompteDAOImpl implements ICompteDAO {

	/**
	 * Récupère la liste des comptes
	 * @return
	 */
	@Override
	public List<Compte> getAllComptes() {

		PreparedStatement ps = null;
		ResultSet resultatRequete = null;

		try {

			ps = this.connection.prepareStatement("SELECT * FROM comptes");

			resultatRequete = ps.executeQuery();

			// Extraction des données

			Compte compte = null;
			List<Compte> listComptes = new ArrayList<>();

			while (resultatRequete.next()) {

				int id_compte = resultatRequete.getInt(1);
				String typeCompte = resultatRequete.getString(2);
				double solde = resultatRequete.getDouble(3);
				double taux = resultatRequete.getDouble(4);
				double decouvert = resultatRequete.getDouble(5);
				int client_id = resultatRequete.getInt(6);

				// 4.3. Ajout des données dans l'objet hotel
				compte = new Compte(id_compte, solde, typeCompte, decouvert, taux, client_id);

				// 4.4. Ajout de l'objet dans la liste des hotels
				listComptes.add(compte);

			} // end while
				// 5. Renvoi de la liste des hotels
			return listComptes;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 6. Fermeture des ressources (Connection, Statement, ResultSet)
			try {
				if (ps != null)
					ps.close();
				if (resultatRequete != null)
					resultatRequete.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * Récupère un compte à partir de son ID
	 * @param pIdCompte
	 * @return
	 */
	
	@Override
	public Compte getCompteById(int pIdCompte) {

		PreparedStatement ps = null;
		ResultSet resultatRequete = null;

		try {

			ps = this.connection.prepareStatement("SELECT * FROM comptes where id_compte=?");

			ps.setInt(1, pIdCompte);

			resultatRequete = ps.executeQuery();

			Compte compte = null;

			resultatRequete.next();

			int id_compte = resultatRequete.getInt(1);
			String typeCompte = resultatRequete.getString(2);
			double solde = resultatRequete.getDouble(3);
			double taux = resultatRequete.getDouble(4);
			double decouvert = resultatRequete.getDouble(5);
			int client_id = resultatRequete.getInt(6);

			compte = new Compte(id_compte, solde, typeCompte, decouvert, taux, client_id);

			return compte;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 6. Fermeture des ressources (Connection, Statement, ResultSet)
			try {
				if (ps != null)
					ps.close();
				if (resultatRequete != null)
					resultatRequete.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Ajoute un compte
	 * @param compte
	 */
	@Override
	public void ajouterCompte(Compte compte) {

		PreparedStatement ps = null;
		try {

			String requeteSQLAdd = "INSERT INTO comptes(id_compte, typeCompte, solde, taux, decouvert, client_id) VALUES(?,?,?,?,?,?)";

			ps = this.connection.prepareStatement(requeteSQLAdd);

			if (compte.getTaux() == 0) {
				compte.setTaux(0.0);
			}
			if (compte.getDecouvert() == 0) {
				compte.setDecouvert(0.0);
			}
			ps.setInt(1, compte.getIdCompte());
			ps.setString(2, compte.getTypeCompte());
			ps.setDouble(3, compte.getSolde());
			ps.setDouble(4, compte.getTaux());
			ps.setDouble(5, compte.getDecouvert());
			ps.setInt(6, compte.getClientId());

			// 3. Exécution de la requête + récup du résultat

			int verifAjout = ps.executeUpdate();

			// renvoi du résultat

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 4. Libération des ressources
				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Modifie un compte
	 * @param compte
	 */
	@Override
	public void modifierCompte(Compte compte) {

		PreparedStatement ps = null;
		try {

			ps = this.connection.prepareStatement(
					"UPDATE comptes SET solde=?, taux = ?, decouvert=?, client_id=? WHERE id_compte=?");

			if (compte.getTaux() == 0) {
				compte.setTaux(0.0);
			}
			if (compte.getDecouvert() == 0) {
				compte.setDecouvert(0.0);
			}

			ps.setDouble(1, compte.getSolde());
			ps.setDouble(2, compte.getTaux());
			ps.setDouble(3, compte.getDecouvert());
			ps.setInt(4, compte.getClientId());
			ps.setInt(5, compte.getIdCompte());

			// 3. Exécution de la requête + récup du résultat

			int verifUpdate = ps.executeUpdate();

			// renvoi du résultat

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 4. Libération des ressources
				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Supprime un compte via son ID
	 * @param pIdCompte
	 */
	@Override
	public void supprimerCompteById(int pIdCompte) {
		PreparedStatement ps = null;

		try {

			ps = this.connection.prepareStatement("DELETE FROM comptes WHERE id_compte=?");

			ps.setInt(1, pIdCompte);
			int DeleteResult = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 6. Fermeture des ressources (Connection, Statement, ResultSet)
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Récupère la liste des compte d'un client via l'ID de ce client
	 * @param pIdClient
	 * @return
	 */
	@Override
	public List<Compte> getCompteByClientID(int pIdClient) {

		PreparedStatement ps = null;
		ResultSet resultatRequete = null;

		try {
			ps = this.connection.prepareStatement("SELECT * FROM comptes WHERE client_id=?");

			ps.setInt(1, pIdClient);

			resultatRequete = ps.executeQuery();

			Compte compte = null;
			List<Compte> listComptes = new ArrayList<>();

			while (resultatRequete.next()) {

				int id_compte = resultatRequete.getInt(1);
				String typeCompte = resultatRequete.getString(2);
				double solde = resultatRequete.getDouble(3);
				double taux = resultatRequete.getDouble(4);
				double decouvert = resultatRequete.getDouble(5);
				int client_id = resultatRequete.getInt(6);

				compte = new Compte(id_compte, solde, typeCompte, decouvert, taux, client_id);

				listComptes.add(compte);

			} // end while

			return listComptes;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (ps != null)
					ps.close();
				if (resultatRequete != null)
					resultatRequete.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;

	}

	/**
	 * Affecte un compe à un client via leurs IDs
	 * @param pIdCcompte
	 * @param pIdClient
	 */
	@Override
	public void affecterClient(int pIdCcompte, int pIdClient) {

		PreparedStatement ps = null;

		try {

			String requeteSqlSetClient = "UPDATE comptes SET client_id=? WHERE id_compte=?";

			ps = this.connection.prepareStatement(requeteSqlSetClient);

			ps.setInt(1, pIdClient);
			ps.setInt(2, pIdCcompte);

			int SetClientResult = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Déposer de l'argent
	 * @param compte
	 * @param montant
	 */
	@Override
	public void deposer(Compte compte, double montant) {

		PreparedStatement ps = null;

		try {

			ps = this.connection.prepareStatement("UPDATE comptes SET solde=? WHERE id_compte=?");

			ps.setDouble(1, (compte.getSolde() + montant));
			ps.setInt(2, compte.getIdCompte());

			int DepositResult = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Retirer de l'argent
	 * @param compte
	 * @param montant
	 */
	@Override
	public void retirer(Compte compte, double montant) {
		PreparedStatement ps = null;

		try {

			ps = this.connection.prepareStatement("UPDATE comptes SET solde=? WHERE id_compte=?");

			ps.setDouble(1, (compte.getSolde() - montant));
			ps.setInt(2, compte.getIdCompte());

			int WithdrawResult = ps.executeUpdate();
			System.out.println(WithdrawResult);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Transferer de l'argent
	 * @param compte
	 * @param montant
	 * @param compteReceveur
	 */
	@Override
	public void transferer(Compte compte, double montant, Compte compteReceveur) {

		PreparedStatement ps = null;

		try {

			ps = this.connection.prepareStatement("UPDATE comptes SET solde=? WHERE id_compte=?");
			ps.setDouble(1, (compte.getSolde() - montant));
			ps.setInt(2, compte.getIdCompte());

			int WithdrawResult1 = ps.executeUpdate();

			ps = this.connection.prepareStatement("UPDATE comptes SET solde=? WHERE id_compte=?");
			ps.setDouble(1, (compteReceveur.getSolde() + montant));
			ps.setInt(2, compteReceveur.getIdCompte());

			int WithdrawResult2 = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
