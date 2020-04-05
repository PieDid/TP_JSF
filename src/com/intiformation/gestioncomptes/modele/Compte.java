package com.intiformation.gestioncomptes.modele;

import java.io.Serializable;

/**
 * Modèle de données pour le Compte
 * @author IN-DF-024
 *
 */

public class Compte implements Serializable {
	
	/* Propriétés */
	
	private int idCompte;
	private String typeCompte;
	private double solde;
	private double taux;
	private double decouvert;
	private int clientId;
	
	/* Constructeurs */
	
	public Compte() {
		super();
	}
	
	public Compte(int idCompte, double solde, String typeCompte) {
		super();
		this.idCompte = idCompte;
		this.solde = solde;
		this.typeCompte = typeCompte;
	}

	public Compte(int idCompte, double solde, String typeCompte, double decouvert, double taux, int clientId) {
		super();
		this.idCompte = idCompte;
		this.solde = solde;
		this.typeCompte = typeCompte;
		this.decouvert = decouvert;
		this.taux = taux;
		this.clientId = clientId;

	}
	
	public Compte(double solde, String typeCompte, double decouvert, double taux, int clientId) {
		super();
		this.solde = solde;
		this.typeCompte = typeCompte;
		this.clientId = clientId;
		this.decouvert = decouvert;
		this.taux = taux;
	}
	
	/* Getters et Setters */
	

	public int getIdCompte() {
		return idCompte;
	}
	public void setIdCompte(int idCompte) {
		this.idCompte = idCompte;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	public String getTypeCompte() {
		return typeCompte;
	}
	public void setTypeCompte(String typeCompte) {
		this.typeCompte = typeCompte;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	
	public double getDecouvert() {
		return decouvert;
	}
	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}
	public double getTaux() {
		return taux;
	}
	public void setTaux(double taux) {
		this.taux = taux;
	}

	
	/* Méthodes */
	
	@Override
	public String toString() {
		return "Compte [idCompte=" + idCompte + ", solde=" + solde + ", typeCompte=" + typeCompte + ", decouvert=\" + decouvert + \", taux=\" + taux + \", clientID="
				+ clientId + "]";
	}

	
}//end class

