package grsu.epam.utilitycompany.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class UserPrincipal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long userId;
	
	@NotEmpty
	@Column(name = "NAME")
	private String name;
	
	@NotEmpty
	@Column(name = "SURNAME")
	private String surname;
	
	@NotEmpty
	@Column(name = "LOGIN")
	private String login;

	@NotEmpty
	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "role")
	@Enumerated(EnumType.ORDINAL)
	private Role role;

	public UserPrincipal(String login, String password, String name,
			String surname, Role role) {
		this.login = login;
		this.password = password;
		this.role = role;
		this.name = name;
		this.surname = surname;
	}

	public UserPrincipal() {
		// TODO Auto-generated constructor stub
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
