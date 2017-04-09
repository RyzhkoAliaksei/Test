package grsu.epam.utilitycompany.app.web.converters;

import grsu.epam.utilitycompany.app.domain.Speciality;
import grsu.epam.utilitycompany.app.service.SpecialityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToSpecialityConverter implements
		Converter<String, Speciality> {

	@Autowired
	private SpecialityService specialityService;

	@Override
	public Speciality convert(String specialityId) {

		return specialityService.getSpecialityByID(new Long(specialityId));
	}

}
