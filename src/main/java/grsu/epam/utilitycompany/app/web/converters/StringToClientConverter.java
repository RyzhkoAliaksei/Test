package grsu.epam.utilitycompany.app.web.converters;

import grsu.epam.utilitycompany.app.domain.Client;
import grsu.epam.utilitycompany.app.service.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToClientConverter implements Converter<String, Client> {

	@Autowired
	private ClientService clientService;

	@Override
	public Client convert(String clientId) {

		return clientService.getClientByID(new Long(clientId));
	}

}