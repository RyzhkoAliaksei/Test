package grsu.epam.utilitycompany.app.repository;

import grsu.epam.utilitycompany.app.domain.UserPrincipal;
import grsu.epam.utilitycompany.app.repository.base.GenericDao;

public interface UserPrincipalDao extends GenericDao<UserPrincipal, Long> {
	
	UserPrincipal findByCredentials(String login, String password);

	UserPrincipal findByLogin(String login);
}
