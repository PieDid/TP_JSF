package com.intiformation.gestioncomptes.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.intiformation.gestioncomptes.dao.CompteDAOImpl;
import com.intiformation.gestioncomptes.dao.ICompteDAO;
import com.intiformation.gestioncomptes.modele.Compte;

@ManagedBean(name = "gestionCompte")
@SessionScoped
public class GestionCompteBean implements Serializable {

	/* _____________________ props __________________________ */

	// -> liste des comptes pour alimenter la table la page accueil_ceomptes.xhtml
	List<Compte> listeCompteBDD;

	// -> prop compte pour l'ajout et l'�dition
	private Compte compte;

	// -> dao du compte
	ICompteDAO compteDAO;

	/* _____________________ ctors __________________________ */

	/**
	 * ctor sans parametre pour instancier le bean, qui instancie aussi la DAO.
	 */
	public GestionCompteBean() {
		compteDAO = new CompteDAOImpl();
	} // end constructor

	/* ____________________ m�thodes ________________________ */

	/**
	 * 
	 * @return la liste de tous les comptes
	 */
	public List<Compte> getAllComptes() {
		return compteDAO.getAllComptes();
	} // end getAllComptes()

	
	public void supprimerCompte(ActionEvent event) {

		UIParameter cp = (UIParameter) event.getComponent().findComponent("deleteID");

		int Idcompte = (int) cp.getValue();

		FacesContext contextJSF = FacesContext.getCurrentInstance();

		if (compteDAO.supprimerCompteById(Idcompte)) {

			// ----------- suppression OK --------------//

			/* envoi d'un message vers la vue via le context */
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Suppression R�ussie.",
					"Le compte a �t� supprim� avec succ�s"));

		} else {

			// --------- suppression NOT OK ------------//

			/* envoi d'un message vers la vue via le context */
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Suppression Echou�e.",
					"La suppression du compte a �chou�e"));

		} // end else

	} // end supprimerCompte()

	public void choisirCompte(ActionEvent event) {

		// 1. r�cup du param pass� dans le composant au click du lien 'modifier'
		UIParameter cp = (UIParameter) event.getComponent().findComponent("choixID");

		// 2. r�cup de la valeur du param�tre => l'id du livre � editer
		int compteID = (int) cp.getValue();

		// 3. Affectation du compte choisi � la prop 'compte' du managedbean
		setCompte(compteDAO.getCompteById(compteID));

	} // end choisirCompte()

	public void initialiserCompte(ActionEvent event) {
		
		//affectation d'un objet compte vide � la propri�t� 'Compte'
		setCompte(new Compte());
		
	} // end initialiserCompte()
	
	
	public void modifierCompte(ActionEvent event) {
		FacesContext contextJsf = FacesContext.getCurrentInstance();
		
		if (compteDAO.modifierCompte(compte)) {
			
			// Modification OK
			
			// Message vers la Vue
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, 
													"Modification r�ussie",
													" - Les informations du compte sont � jour.");
			
			contextJsf.addMessage(null, message);
			
		}else {
			
				// Modification NOT OK
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, 
													"Modification �chou�e",
													" - Les informations du compte n'ont pas �t� modifi�es.");

			contextJsf.addMessage(null, message);			
			
		}
	} // end modifierCompte
	
	public void ajouterCompte(ActionEvent event) {
		FacesContext contextJsf = FacesContext.getCurrentInstance();
		
		if (compteDAO.ajouterCompte(compte)) {
			
			// Ajout OK
			
			// Message vers la Vue
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, 
													"Ajout du compte r�ussi",
													" - Le compte a �t� ajout�.");
			
			contextJsf.addMessage(null, message);
			
		}else {
			
				// Modification NOT OK
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, 
													"Ajout du compte �chou�",
													" - Le compte n'a pas �t� enregistr�.");

			contextJsf.addMessage(null, message);			
			
		}
	} // end ajouterCompte
	
	
	/* ________________ getters/setters _____________________ */

	/**
	 * @return the compte
	 */
	public Compte getCompte() {
		return compte;
	}

	/**
	 * @param compte the compte to set
	 */
	public void setCompte(Compte compte) {
		this.compte = compte;
	}

}
