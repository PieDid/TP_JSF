package com.intiformation.gestioncomptes.managedbean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.intiformation.gestioncomptes.dao.ConseillerDAOImpl;
import com.intiformation.gestioncomptes.dao.IConseillerDAO;

@ManagedBean(name = "authentificationBean")
@SessionScoped
public class AuthentificationBean implements Serializable {

	/* _______________________ props ___________________________ */

	// les propiétés pour la connection
	private String login;
	private String password;
	private String idConseiller;

	// la DAO du conseiller
	public IConseillerDAO conseillerDAO;

	/* _______________________ ctors ___________________________ */

	/**
	 * ctor vide qui initialise la DAO
	 */
	public AuthentificationBean() {
		this.conseillerDAO = new ConseillerDAOImpl();
	}

	/* ______________________ méthodes _________________________ */

	/**
	 * Méthode qui permet au conseiller de se connecter
	 * 
	 * @return
	 */
	public String connecter() {

		// context de JSF pour récupérer la session, la créer et envoyer des messages
		FacesContext contextJSF = FacesContext.getCurrentInstance();

		// 1. verif si le conseiller existe dans la bdd
		if (conseillerDAO.isConseillerExists(login, password)) {

			// ------------------- l'utilisateur existe ---------------------//

			/* création de la session pour l'utilisateur => création d'un id de session */
			HttpSession session = (HttpSession) contextJSF.getExternalContext().getSession(true);

			/* sauvegarde du login dans la session */
			session.setAttribute("user_login", login);
			
			/* redirection (navigation) vers la page accueil.xhtml */
			return "accueil.xhtml";

		} else {

			// ---------------- l'utilisateur n'existe pas ------------------//
			/**
			 * Envoi d'un message vers la vue via la classe FacesMessage et le context
			 */

			// def du message via FacesMessage
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec de connexion",
					"Identifiant ou Mot de passe invalide");

			// envoi du message vers la vue via le context de JSF

			/**
			 * addMessage(String, FacesMessage) : - String : soit l'id du composant (
			 * message pour ce composant) ou null (message pour l'ensemble de la page )
			 */
			contextJSF.addMessage(null, message);

			/* redirection (navigation) vers la page du formulaire authentification.xhtml */
			return "authentification.xhtml";

		} // end else

	} // end connecter()

	public String deconnecter() {

		// context de JSF
		FacesContext contextJSF = FacesContext.getCurrentInstance();

		// recup de la session de l'utilisateur
		HttpSession session = (HttpSession) contextJSF.getExternalContext().getSession(false);

		// déconnection
		session.invalidate();

		/* redirection (navigation) vers la page du formulaire authentification.xhtml */
		return "authentification.xhtml";

	} // end deconnecter()
	
	/* __________________ getters/setters __________________________ */

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the idConseiller
	 */
	public String getIdConseiller() {
		return idConseiller;
	}

	/**
	 * @param idConseiller the idConseiller to set
	 */
	public void setIdConseiller(String idConseiller) {
		this.idConseiller = idConseiller;
	}

	
	
}
