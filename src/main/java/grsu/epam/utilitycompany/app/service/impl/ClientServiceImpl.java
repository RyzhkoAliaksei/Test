package grsu.epam.utilitycompany.app.service.impl;

import grsu.epam.utilitycompany.app.domain.Client;
import grsu.epam.utilitycompany.app.repository.ClientDao;
import grsu.epam.utilitycompany.app.service.ClientService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	private ClientDao clientRepository;

	@Override
	public void saveClient(Client client) {
		clientRepository.save(client);
	}
	
	@Override
	public void deleteClient(Client client) {
		clientRepository.delete(client);
	}
	
	@Override
	public void updateClient(Client client) {
		clientRepository.update(client);
	}

	@Override
	public Client getClientByID(Long clientId) {
		
		return clientRepository.findById(clientId);
	}
	
	@Override
	public List<Client> getClients() {
		
		List<Client> clients = clientRepository.findAll();		
		return clients;
	}

}
