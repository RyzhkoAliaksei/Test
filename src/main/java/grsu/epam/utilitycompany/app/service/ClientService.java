package grsu.epam.utilitycompany.app.service;

import grsu.epam.utilitycompany.app.domain.Client;

import java.util.List;



public interface ClientService {

	void saveClient(Client client);
	
	void deleteClient(Client client);
	
	Client getClientByID(Long clientId);
	
	List<Client> getClients();

	void updateClient(Client client);
}
