package grsu.epam.utilitycompany.app.web.converters;

import grsu.epam.utilitycompany.app.domain.Dispatcher;
import grsu.epam.utilitycompany.app.service.DispatcherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToDispatcherConverter implements
		Converter<String, Dispatcher> {

	@Autowired
	private DispatcherService dispatcherService;

	@Override
	public Dispatcher convert(String dispatcherId) {

		return dispatcherService.getDispatcherByID(new Long(dispatcherId));
	}

}