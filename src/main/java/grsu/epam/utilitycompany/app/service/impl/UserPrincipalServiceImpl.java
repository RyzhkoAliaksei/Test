package grsu.epam.utilitycompany.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import grsu.epam.utilitycompany.app.domain.UserPrincipal;
import grsu.epam.utilitycompany.app.repository.UserPrincipalDao;
import grsu.epam.utilitycompany.app.service.UserPrincipalService;

@Service
@Transactional
public class UserPrincipalServiceImpl implements UserPrincipalService {
	@Autowired
	private UserPrincipalDao userRepository;

	@Override
	public void saveUser(UserPrincipal user) {
		userRepository.save(user);

	}

	@Override
	public void deleteUser(UserPrincipal user) {
		userRepository.delete(user);

	}

	@Override
	public UserPrincipal getUserByID(Long userId) {
		return userRepository.findById(userId);
	}

	@Override
	public List<UserPrincipal> getUsers() {
		List<UserPrincipal> users = userRepository.findAll();		
		return users;
	}

	@Override
	public void updateUser(UserPrincipal user) {
		userRepository.update(user);

	}

	@Override
	public UserPrincipal loadUserByCredentials(String username, String password) {
		UserPrincipal user = userRepository.findByCredentials(username, password);
		return user;
	}

	@Override
	public void logoutUser(Long userId) {
	}

	@Override
	public UserPrincipal loadUserDetailsByLogin(String login) {
		return userRepository.findByLogin(login);
	}

}

