package grsu.epam.utilitycompany.app.web.converters;

import grsu.epam.utilitycompany.app.domain.Demand;

import grsu.epam.utilitycompany.app.service.DemandService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToDemandConverter implements Converter<String, Demand> {

	@Autowired
	private DemandService demandService;

	@Override
	public Demand convert(String demandId) {

		return demandService.getDemandByID(new Long(demandId));
	}

}