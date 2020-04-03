package com.intiformation.gestioncomptes.modele;

import java.io.Serializable;

public class CompteCourant extends Compte implements Serializable {

	/* Propriétés */
	
	private double decouvert = 100;

	/* Constructeurs */
	
	public CompteCourant(int idCompte, double solde, double decouvert) {
		super(idCompte, solde, "courant");
	}

	/* Getters et Setters */
	
	public double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}

}//end class
