package com.intiformation.gestioncomptes.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.intiformation.gestioncomptes.dao.ClientDAOImpl;
import com.intiformation.gestioncomptes.dao.CompteDAOImpl;
import com.intiformation.gestioncomptes.dao.IClientDAO;
import com.intiformation.gestioncomptes.dao.ICompteDAO;
import com.intiformation.gestioncomptes.modele.Client;
import com.intiformation.gestioncomptes.modele.Compte;

@ManagedBean(name = "gestionCompte")
@SessionScoped
public class GestionCompteBean implements Serializable {

	/* _____________________ props __________________________ */

	// -> liste des comptes pour alimenter la table la page accueil_comptes.xhtml et des clients pour ChangerClient et Ajouter
	List<Compte> listeCompteBDD;
	List<Client> listeClientBDD;
	List<Compte> listeClientConseillerBDD;
	// -> prop compte pour l'ajout et l'�dition
	private Compte compte;

	// -> prop montant pour le d�pot, retrait , virement
	private double montant;

	// -> prop id compteReceveur pour le virement
	private int idcompteReceveur;
	
	// -> dao du compte et des clients
	ICompteDAO compteDAO;
	IClientDAO clientDAO;

	/* _____________________ ctors __________________________ */

	/**
	 * ctor sans parametre pour instancier le bean, qui instancie aussi la DAO.
	 */
	public GestionCompteBean() {
		compteDAO = new CompteDAOImpl();
		clientDAO = new ClientDAOImpl();
	} // end constructor

	/* ____________________ m�thodes ________________________ */

	/**
	 * 
	 * @return la liste de tous les comptes
	 */
	public List<Compte> getAllComptes() {
		return compteDAO.getAllComptes();
	} // end getAllComptes()
	
	public List<Client> getAllClients() {
		return clientDAO.getAllClients();
	} // end getAllComptes()

	public List<Compte> getComptesConseiller(int pIdConseiller){
		return compteDAO.getAllCompteByIdConseiller(pIdConseiller);
	}
	
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
		if (cp == null) {
			cp = (UIParameter) event.getComponent().findComponent("changerClientID");
		}

		// 2. r�cup de la valeur du param�tre => l'id du livre � editer
		int compteID = (int) cp.getValue();

		// 3. Affectation du compte choisi � la prop 'compte' du managedbean
		setCompte(compteDAO.getCompteById(compteID));

	} // end choisirCompte()

	public void initialiserCompte(ActionEvent event) {

		Compte nouveauCompte = new Compte();

		// Par d�faut, le compte est de type epargne (car dans ajouter_compte, le
		// radiobutton activ" par d�faut est �pargne)
		nouveauCompte.setTypeCompte("Epargne");

		// affectation d'un objet compte vide � la propri�t� 'Compte'
		setCompte(nouveauCompte);

	} // end initialiserCompte()

	public void modifierCompte(ActionEvent event) {
		FacesContext contextJsf = FacesContext.getCurrentInstance();

		if (compteDAO.modifierCompte(compte)) {

			// Modification OK

			// Message vers la Vue
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modification r�ussie",
					" - Les informations du compte sont � jour.");

			contextJsf.addMessage(null, message);

		} else {

			// Modification NOT OK

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Modification �chou�e",
					" - Les informations du compte n'ont pas �t� modifi�es.");

			contextJsf.addMessage(null, message);

		}
	} // end modifierCompte

	public void ajouterCompte(ActionEvent event) {
		FacesContext contextJsf = FacesContext.getCurrentInstance();

		if (compteDAO.ajouterCompte(compte)) {

			// Ajout OK

			// Message vers la Vue
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ajout du compte r�ussi",
					" - Le compte a �t� ajout�.");

			contextJsf.addMessage(null, message);

		} else {

			// Modification NOT OK

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Ajout du compte �chou�",
					" - Le compte n'a pas �t� enregistr�.");

			contextJsf.addMessage(null, message);

		}
	} // end ajouterCompte

	public void changerClientCompte(ActionEvent event) {

		FacesContext contextJsf = FacesContext.getCurrentInstance();
		if (compteDAO.affecterClient(compte.getIdCompte(), compte.getClientId())) {

			// Ajout OK

			// Message vers la Vue
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "L'affectation du client r�ussi",
					" - Le client a bien �t� modifi�.");

			contextJsf.addMessage(null, message);

		} else {

			// Modification NOT OK

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "L'affectation du client �chou�",
					" - Le client n'a pas pu �tre modifi�.");

			contextJsf.addMessage(null, message);

		}

	} // end changerClientCompte()

	public void deposer(ActionEvent event) {
		FacesContext contextJsf = FacesContext.getCurrentInstance();
		if (compteDAO.deposer(compte, montant)) {

			// Ajout OK

			// Message vers la Vue
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "D�pot r�ussi",
					" - le solde du compte a �t� modifi�.");

			contextJsf.addMessage(null, message);

		} else {

			// Modification NOT OK

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "D�pot �chou�",
					" -le solde du compte est rest� intact");
			contextJsf.addMessage(null, message);

		}
	}

	public void retirer(ActionEvent event) {
		FacesContext contextJsf = FacesContext.getCurrentInstance();

		if ((compte.getSolde() - montant) < -compte.getDecouvert()) {

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Retrait annul�",
					" - le solde du compte apr�s op�ration aurait exc�d� votre d�couvert autoris�.");
			contextJsf.addMessage(null, message);
		} else {
			if (compteDAO.retirer(compte, montant)) {

				// Ajout OK

				// Message vers la Vue
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Retrait r�ussi",
						" - le solde du compte a �t� modifi�.");

				contextJsf.addMessage(null, message);

			} else {

				// Modification NOT OK

				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Retrait �chou�",
						" -le solde du compte est rest� intact");
				contextJsf.addMessage(null, message);

			}
		}
	}
	
	public void virement(ActionEvent event) {
		
		FacesContext contextJsf = FacesContext.getCurrentInstance();

		Compte compteReceveur = compteDAO.getCompteById(idcompteReceveur);
		
		if ((compte.getSolde() - montant) < -compte.getDecouvert()) {

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Virement annul�",
					" - le solde du compte apr�s op�ration aurait exc�d� votre d�couvert autoris�.");
			contextJsf.addMessage(null, message);
		} else {
			if (compteDAO.transferer(compte, montant, compteReceveur)) {

				// Ajout OK

				// Message vers la Vue
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Virement r�ussi",
						" - les soldes des comptes ont �t� modifi�s.");

				contextJsf.addMessage(null, message);

			} else {

				// Modification NOT OK

				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Virement �chou�",
						" -les soldes des comptes sont rest�s intacts");
				contextJsf.addMessage(null, message);

			}
		}
		
	}

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

	/**
	 * @return the montant
	 */
	public double getMontant() {
		return montant;
	}

	/**
	 * @param montant the montant to set
	 */
	public void setMontant(double montant) {
		this.montant = montant;
	}

	/**
	 * @return the idcompteReceveur
	 */
	public int getIdcompteReceveur() {
		return idcompteReceveur;
	}

	/**
	 * @param idcompteReceveur the idcompteReceveur to set
	 */
	public void setIdcompteReceveur(int idcompteReceveur) {
		this.idcompteReceveur = idcompteReceveur;
	}
	
	

}
