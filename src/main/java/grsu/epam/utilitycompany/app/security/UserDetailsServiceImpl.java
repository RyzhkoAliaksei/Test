package grsu.epam.utilitycompany.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import grsu.epam.utilitycompany.app.domain.UserPrincipal;
import grsu.epam.utilitycompany.app.service.UserPrincipalService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserPrincipalService userService;

	@Override
	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {

		UserPrincipal user = userService.loadUserDetailsByLogin(login);

		if (user == null) {
			throw (new UsernameNotFoundException("User " + login + " not found"));
		}

		ApplicationUserDetails appUserDetails = new ApplicationUserDetails(
				user, getAuthorities(user));

		return appUserDetails;
	}

	protected Collection<GrantedAuthority> getAuthorities(UserPrincipal user) {

		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
		authList.add(new SimpleGrantedAuthority("ROLE_"
				+ user.getRole().toString()));
		System.out.println(user.getRole().toString());
		return authList;
	}
}
