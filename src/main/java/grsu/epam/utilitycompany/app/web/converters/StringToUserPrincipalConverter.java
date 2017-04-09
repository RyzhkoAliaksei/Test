package grsu.epam.utilitycompany.app.web.converters;

import grsu.epam.utilitycompany.app.domain.UserPrincipal;
import grsu.epam.utilitycompany.app.service.UserPrincipalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToUserPrincipalConverter implements
		Converter<String, UserPrincipal> {

	@Autowired
	private UserPrincipalService userPrincipalService;

	@Override
	public UserPrincipal convert(String userId) {
		return userPrincipalService.getUserByID(new Long(userId));
	}

}