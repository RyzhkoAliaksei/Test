package grsu.epam.utilitycompany.app.web.converters;

import grsu.epam.utilitycompany.app.domain.Crew;
import grsu.epam.utilitycompany.app.service.CrewService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class StringToCrewConverter implements Converter<String, Crew> {
 
    @Autowired
	private CrewService crewService;
    
    @Override
    public Crew convert(String demandId) {
       
    	return crewService.getCrewByID(new Long(demandId));
    }
 
}