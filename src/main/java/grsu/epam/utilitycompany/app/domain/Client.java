package grsu.epam.utilitycompany.app.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "client")
@PrimaryKeyJoinColumn(name = "USER_ID")
public class Client extends UserPrincipal {

	@NotEmpty
	@Column(name = "ADDRESS")
	private String address;
	@OneToMany(targetEntity = Demand.class, mappedBy = "client", fetch = FetchType.EAGER)
	private List<Demand> demands;

	public Client() {
	}

	public Client(String login, String password, String name, String surname) {
		super(login, password, name, surname, Role.CLIENT);

	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Demand> getDemands() {
		return demands;
	}

	public void setDemands(List<Demand> demands) {
		this.demands = demands;
	}

}
