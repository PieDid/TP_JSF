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

	// -> prop compte pour l'ajout et l'édition
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

	/* ____________________ méthodes ________________________ */

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
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Suppression Réussie.",
					"Le compte a été supprimé avec succès"));

		} else {

			// --------- suppression NOT OK ------------//

			/* envoi d'un message vers la vue via le context */
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Suppression Echouée.",
					"La suppression du compte a échouée"));

		} // end else

	} // end supprimerCompte()

	public void choisirCompte(ActionEvent event) {

		// 1. récup du param passé dans le composant au click du lien 'modifier'
		UIParameter cp = (UIParameter) event.getComponent().findComponent("choixID");

		// 2. récup de la valeur du paramètre => l'id du livre à editer
		int compteID = (int) cp.getValue();

		// 3. Affectation du compte choisi à la prop 'compte' du managedbean
		setCompte(compteDAO.getCompteById(compteID));

	} // end choisirCompte()

	public void initialiserCompte(ActionEvent event) {
		
		//affectation d'un objet compte vide à la propriété 'Compte'
		setCompte(new Compte());
		
	} // end initialiserCompte()
	
	
	public void modifierCompte(ActionEvent event) {
		FacesContext contextJsf = FacesContext.getCurrentInstance();
		
		if (compteDAO.modifierCompte(compte)) {
			
			// Modification OK
			
			// Message vers la Vue
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, 
													"Modification réussie",
													" - Les informations du compte sont à jour.");
			
			contextJsf.addMessage(null, message);
			
		}else {
			
				// Modification NOT OK
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, 
													"Modification échouée",
													" - Les informations du compte n'ont pas été modifiées.");

			contextJsf.addMessage(null, message);			
			
		}
	} // end modifierCompte
	
	public void ajouterCompte(ActionEvent event) {
		FacesContext contextJsf = FacesContext.getCurrentInstance();
		
		if (compteDAO.ajouterCompte(compte)) {
			
			// Ajout OK
			
			// Message vers la Vue
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, 
													"Ajout du compte réussi",
													" - Le compte a été ajouté.");
			
			contextJsf.addMessage(null, message);
			
		}else {
			
				// Modification NOT OK
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, 
													"Ajout du compte échoué",
													" - Le compte n'a pas été enregistré.");

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
