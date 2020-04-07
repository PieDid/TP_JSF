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

@ManagedBean(name="gestionClient")
@SessionScoped

public class GestionClientBean implements Serializable  {

	/* Propriétés */
	
	List<Client> listeClientBdd;
	List<Compte> listeCompteBdd;
	
	private Client client;
	IClientDAO clientDao;
	ICompteDAO compteDao;
	
	/* Constructeurs */
	
	public GestionClientBean() {
		
		clientDao = new ClientDAOImpl ();
		compteDao = new CompteDAOImpl ();
		
	}
	
	
	
	/* Méthodes */
	
	public List<Client> getAllClients() {
		
		listeClientBdd = clientDao.getAllClients();
		
		return listeClientBdd;
		
	}//end Get All
	
	
	public void addClient(ActionEvent event) {
		
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		if (clientDao.addClient(client)) {
			
			// Ajout OK
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, 
												"Ajout réussi",
												" - Nouveau client ajouté à la liste des clients.");
		
		contextJSF.addMessage(null, message);
		
	}else {
		
			// Ajout NOT OK
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, 
												"Ajout échoué",
												" - Pas de nouveau client ajouté.");

		contextJSF.addMessage(null, message);			
		
	}
		
	}//end Add


	public void initClient(ActionEvent event) {
		
		Client newClient = new Client();
		
		setClient(newClient);
		
		
	}//end init
	
	public void deleteClientById(ActionEvent event) {
		
		UIParameter cp = (UIParameter) event.getComponent().findComponent("deleteID");
		
		int IdClient = (int) cp.getValue();
		
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		if (clientDao.deleteClientById(IdClient)) {
		
			// Suppression OK

			contextJSF.addMessage(null, new FacesMessage("Le client a été supprimé avec succès"));
			
		}else {
			
			// Suppression NOT OK
	
			contextJSF.addMessage(null, new FacesMessage("La suppression du client a échoué"));
			
		}//end else
		
	}//end Delete
	
	
	public void getClientById(ActionEvent event) {
		
		UIParameter cp = (UIParameter) event.getComponent().findComponent("updateID");
		
		int IdClient = (int) cp.getValue();
		
		Client clientGet = clientDao.getClientById(IdClient);
		
		setClient(clientGet);
		
		
	}//end Get One
	
	
	public void getComptesByClientId(ActionEvent event) {
		
		UIParameter cp = (UIParameter) event.getComponent().findComponent("ComptesID");
		
		int IdClient = (int) cp.getValue();
		
		List<Compte> listeComptesByClient = compteDao.getCompteByClientID(IdClient);
		
		setListeCompteBdd(listeComptesByClient) ; 
		
		
	}

	
	public void updateClient(ActionEvent event) {
		
		FacesContext contextJsf = FacesContext.getCurrentInstance();
		
		if (clientDao.updateClient(client)) {
			
				// Modification OK
			
			// Message vers la Vue
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, 
													"Modification réussie",
													" - Les informations du client sont à jours.");
			
			contextJsf.addMessage(null, message);
			
		}else {
			
				// Modification NOT OK
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, 
													"Modification échouée",
													" - Les informations du client n'ont pas été modifiés .");

			contextJsf.addMessage(null, message);			
			
		}
		
	}//end Update

	
	/* Getters et Setters */

	/**
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}


	/**
	 * @param client the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}



	public List<Client> getListeClientBdd() {
		return listeClientBdd;
	}



	public void setListeClientBdd(List<Client> listeClientBdd) {
		this.listeClientBdd = listeClientBdd;
	}



	public List<Compte> getListeCompteBdd() {
		return listeCompteBdd;
	}



	public void setListeCompteBdd(List<Compte> listeCompteBdd) {
		this.listeCompteBdd = listeCompteBdd;
	}



	public IClientDAO getClientDao() {
		return clientDao;
	}



	public void setClientDao(IClientDAO clientDao) {
		this.clientDao = clientDao;
	}



	public ICompteDAO getCompteDao() {
		return compteDao;
	}



	public void setCompteDao(ICompteDAO compteDao) {
		this.compteDao = compteDao;
	}
	
	
	
	

	
	
	
	
}//end class
