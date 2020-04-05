package com.intiformation.gestioncomptes.dao;

import java.sql.Connection;
import java.util.List;

import com.intiformation.gestioncomptes.modele.Client;
import com.intiformation.gestioncomptes.modele.Compte;
import com.intiformation.gestioncomptes.tool.DBConnection;

public interface ICompteDAO {

	// r�cup de la connexion
	public Connection connection = DBConnection.getInstance();

	// m�thodes de la DAO
	
	/**
	 * R�cup�re la liste des comptes
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
	 * R�cup�re un compte � partir de son ID
	 * @param pIdCompte
	 * @return
	 */
	public Compte getCompteById(int pIdCompte);

	/**
	 * R�cup�re la liste des compte d'un client via l'ID de ce client
	 * @param pIdClient
	 * @return
	 */
	public List<Compte> getCompteByClientID(int pIdClient);

	
	/**
	 * Affecte un compe � un client via leurs IDs
	 * @param pIdCcompte
	 * @param pIdClient
	 */
	public boolean affecterClient(int pIdCcompte, int pIdClient);

	/**
	 * D�poser de l'argent
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
