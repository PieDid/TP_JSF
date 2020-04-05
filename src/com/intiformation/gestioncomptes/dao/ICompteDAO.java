package com.intiformation.gestioncomptes.dao;

import java.sql.Connection;
import java.util.List;

import com.intiformation.gestioncomptes.modele.Client;
import com.intiformation.gestioncomptes.modele.Compte;
import com.intiformation.gestioncomptes.tool.DBConnection;

public interface ICompteDAO {

	// récup de la connexion
	public Connection connection = DBConnection.getInstance();

	// méthodes de la DAO
	
	/**
	 * Récupère la liste des comptes
	 * @return
	 */
	public List<Compte> getAllComptes();
	
	/**
	 * Ajoute un compte
	 * @param compte
	 */
	public boolean ajouterCompte(Compte compte);

	/**
	 * Modifie un compte
	 * @param compte
	 */
	public boolean modifierCompte(Compte compte);

	/**
	 * Supprime un compte via son ID
	 * @param pIdCompte
	 */
	public boolean supprimerCompteById(int pIdCompte);

	/**
	 * Récupère un compte à partir de son ID
	 * @param pIdCompte
	 * @return
	 */
	public Compte getCompteById(int pIdCompte);

	/**
	 * Récupère la liste des compte d'un client via l'ID de ce client
	 * @param pIdClient
	 * @return
	 */
	public List<Compte> getCompteByClientID(int pIdClient);

	
	/**
	 * Affecte un compe à un client via leurs IDs
	 * @param pIdCcompte
	 * @param pIdClient
	 */
	public boolean affecterClient(int pIdCcompte, int pIdClient);

	/**
	 * Déposer de l'argent
	 * @param compte
	 * @param montant
	 */
	public boolean deposer(Compte compte, double montant);

	/**
	 * Retirer de l'argent
	 * @param compte
	 * @param montant
	 */
	public boolean retirer(Compte compte, double montant);

	
	/**
	 * Transferer de l'argent
	 * @param compte
	 * @param montant
	 * @param compteReceveur
	 */
	public boolean transferer(Compte compte, double montant, Compte compteReceveur);

}
