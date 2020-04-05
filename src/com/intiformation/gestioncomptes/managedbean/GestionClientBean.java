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
import com.intiformation.gestioncomptes.dao.IClientDAO;
import com.intiformation.gestioncomptes.modele.Client;

@ManagedBean(name="gestionClient")
@SessionScoped

public class GestionClientBean implements Serializable  {

	/* Propri�t�s */
	
	List<Client> listeClientBdd;
	private Client client;
	IClientDAO clientDao;
	
	/* Constructeurs */
	
	public GestionClientBean() {
		
		clientDao = new ClientDAOImpl ();
		
	}
	
	
	/* M�thodes */
	
	public List<Client> getAllClients() {
		
		listeClientBdd = clientDao.getAllClients();
		
		return listeClientBdd;
		
	}//end Get All
	
	
	public void addClient(ActionEvent event) {
		
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		if (clientDao.addClient(client)) {
			
			// Ajout OK
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, 
												"Ajout r�ussi",
												" - Nouveau client ajout� � la liste des clients.");
		
		contextJSF.addMessage(null, message);
		
	}else {
		
			// Ajout NOT OK
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, 
												"Ajout �chou�",
												" - Pas de nouveau client ajout�.");

		contextJSF.addMessage(null, message);			
		
	}
		
	}//end Add


	public void setClient(ActionEvent event) {
		
		Client newClient = new Client();
		
		setClient(newClient);
		
		
	}//end Set
	
	public void deleteClientById(ActionEvent event) {
		
		UIParameter cp = (UIParameter) event.getComponent().findComponent("deleteID");
		
		int IdClient = (int) cp.getValue();
		
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		if (clientDao.deleteClientById(IdClient)) {
		
			// Suppression OK

			contextJSF.addMessage(null, new FacesMessage("Le client a �t� supprim� avec succ�s"));
			
		}else {
			
			// Suppression NOT OK
	
			contextJSF.addMessage(null, new FacesMessage("La suppression du client a �chou�"));
			
		}//end else
		
	}//end Delete
	
	
	public void getClientById(ActionEvent event) {
		
		UIParameter cp = (UIParameter) event.getComponent().findComponent("getID");
		
		int IdClient = (int) cp.getValue();
		
		Client clientGet = clientDao.getClientById(IdClient);
		
		setClient(clientGet);
		
		
	}//end Get One

	
	public void updateClient(ActionEvent event) {
		
		FacesContext contextJsf = FacesContext.getCurrentInstance();
		
		if (clientDao.updateClient(client)) {
			
				// Modification OK
			
			// Message vers la Vue
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, 
													"Modification r�ussie",
													" - Les informations du client sont � jours.");
			
			contextJsf.addMessage(null, message);
			
		}else {
			
				// Modification NOT OK
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, 
													"Modification �chou�e",
													" - Les informations du client n'ont pas �t� modifi�s .");

			contextJsf.addMessage(null, message);			
			
		}
		
	}//end Update
	
	
	
	/* Getters et Setters */

	private void setClient(Client clientGet) {
		
	}
	
	
	
	
}//end class