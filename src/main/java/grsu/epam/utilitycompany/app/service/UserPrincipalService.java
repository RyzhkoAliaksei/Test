package grsu.epam.utilitycompany.app.service;

import grsu.epam.utilitycompany.app.domain.UserPrincipal;

import java.util.List;

public interface UserPrincipalService {
	
	void saveUser(UserPrincipal user);
	
	void deleteUser(UserPrincipal user);
	
	UserPrincipal getUserByID(Long userId);
	
	List<UserPrincipal> getUsers();

	void updateUser(UserPrincipal user);

	UserPrincipal loadUserByCredentials(String username, String password);

	UserPrincipal loadUserDetailsByLogin(String login);

	void logoutUser(Long userId);
}
