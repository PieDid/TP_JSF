package com.intiformation.gestioncomptes.modele;

import java.io.Serializable;

/**
 * Modèle de données JavaBean pour le conseiller
 * @author IN-DF-024
 *
 */

public class Conseiller implements Serializable {
	
	/* Propriétés */
	
	private int idConseiller;
	private String nom;
	private String prenom;
	private String mail;
	private String motDePasse;
	
	/* Constructeurs */
	
	public Conseiller() {
		super();
	}

	public Conseiller(String nom, String prenom, String mail, String motDePasse) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.motDePasse = motDePasse;
	}
	
	public Conseiller(int idConseiller, String nom, String prenom, String mail, String motDePasse) {
		super();
		this.idConseiller = idConseiller;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.motDePasse = motDePasse;
	}

	
	/* Getters et Setters */
	


	public int getIdConseiller() {
		return idConseiller;
	}

	public void setIdConseiller(int idConseiller) {
		this.idConseiller = idConseiller;
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
	/* Méthodes */


	@Override
	public String toString() {
		return "Conseiller [idConseiller=" + idConseiller + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", motDePasse="
				+ motDePasse + "]";
	}
	
}//end class

