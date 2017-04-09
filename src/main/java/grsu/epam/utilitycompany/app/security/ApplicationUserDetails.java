package grsu.epam.utilitycompany.app.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import grsu.epam.utilitycompany.app.domain.UserPrincipal;

import java.util.Collection;

public class ApplicationUserDetails extends User {

	private static final long serialVersionUID = 98078L;
	private UserPrincipal userPrincipal;

	public ApplicationUserDetails(String username, String password,
			boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {

		super(username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, authorities);
	}

	public ApplicationUserDetails(UserPrincipal user,
			Collection<GrantedAuthority> authorities) {
		super(user.getLogin(), user.getPassword(), authorities);
		this.userPrincipal = user;
	}

	public UserPrincipal getAccount() {
		return userPrincipal;
	}

	public void setAccount(UserPrincipal userPrincipal) {
		this.userPrincipal = userPrincipal;
	}
}
