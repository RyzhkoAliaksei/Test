package grsu.epam.utilitycompany.app.web.converters;

import grsu.epam.utilitycompany.app.domain.Crew;
import grsu.epam.utilitycompany.app.service.CrewService;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class CrewFormatter implements Formatter<Crew> {

	@Autowired
	private CrewService crewService;

	@Override
	public Crew parse(String crewId, Locale locale) throws ParseException {
		Crew crew = crewService.getCrewByID(Long.parseLong(crewId));

		return crew;
	}

	public String print(Crew c, Locale locale) {
		return c.getCrewId().toString();
	};
}
