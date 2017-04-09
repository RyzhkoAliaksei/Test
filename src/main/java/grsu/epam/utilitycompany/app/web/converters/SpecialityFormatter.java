package grsu.epam.utilitycompany.app.web.converters;

import grsu.epam.utilitycompany.app.domain.Speciality;
import grsu.epam.utilitycompany.app.service.SpecialityService;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class SpecialityFormatter implements Formatter<Speciality> {

	@Autowired
	private SpecialityService specialityService;

	@Override
	public Speciality parse(String specialityId, Locale locale)
			throws ParseException {
		Speciality speciality = specialityService.getSpecialityByID(Long
				.parseLong(specialityId));
		return speciality;
	}

	@Override
	public String print(Speciality s, Locale arg1) {

		return s.getSpecialityId().toString();
	};
}