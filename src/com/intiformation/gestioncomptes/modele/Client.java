package com.intiformation.gestioncomptes.modele;

import java.io.Serializable;

/**
 * Mod�le de donn�es pour le client
 * @author IN-DF-024
 *
 */

public class Client implements Serializable {

	/* Propri�t�s */
	
	private int idClient;
	private String nom;
	private String prenom;
	private String adresse;
	private String codePostal;
	private String ville;
	private String telephone;
	private int conseillerId;
	
	/* Constructeurs */
	
	public Client() {

	}

	public Client(String nom, String prenom, String adresse, String codePostal, String ville, String telephone,
			int idConseiller) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.telephone = telephone;
		this.conseillerId = idConseiller;
	}

	public Client(int idClient, String nom, String prenom, String adresse, String codePostal, String ville,
			String telephone, int idConseiller) {
		this.idClient = idClient;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.telephone = telephone;
		this.conseillerId = idConseiller;
	}

	
	public Client(int id_client, String nom, String prenom, String adresse, String codePostal, String ville,
			String telephone) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.telephone = telephone;
	}
	
	/* Getters et Setters */

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getIdConseiller() {
		return conseillerId;
	}

	public void setIdConseiller(int idConseiller) {
		this.conseillerId = idConseiller;
	}
	
	/* M�thodes */
	
	@Override
	public String toString() {
		return "Client [idClient=" + idClient + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse
				+ ", codePostal=" + codePostal + ", ville=" + ville + ", telephone=" + telephone + ", conseiller="+ conseillerId+"]";
	}
	
	
	
}//end class
