package grsu.epam.utilitycompany.app.web.converters;

import grsu.epam.utilitycompany.app.domain.Demand;
import grsu.epam.utilitycompany.app.service.DemandService;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class DemandFormatter implements Formatter<Demand> {

	@Autowired
	private DemandService demandService;

	@Override
	public Demand parse(String demandId, Locale locale) throws ParseException {
		Demand demand = null;
		demand = demandService.getDemandByID(Long.parseLong(demandId));
		return demand;
	}

	public String print(Demand d, Locale locale) {
		return d.getDemandId().toString();
	};
}
