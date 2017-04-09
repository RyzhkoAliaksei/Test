package grsu.epam.utilitycompany.app.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "crew")
public class Crew {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CREW_ID")
	private Long crewId;

	@NotEmpty
	@Size(min=1, max=45)
	@Column(name = "CREW_NAME")
	private String crewName;

	@OneToMany(targetEntity = Employee.class, mappedBy = "crew", fetch = FetchType.LAZY)
	private List<Employee> employees;

	@OneToMany(targetEntity = Demand.class, mappedBy = "crew", fetch = FetchType.EAGER)
	private List<Demand> demands;

	public List<Demand> getDemands() {
		return demands;
	}

	public void setDemands(List<Demand> demands) {
		this.demands = demands;
	}

	public Crew() {
	}

	public Crew(Long crewId, String crewName, List<Employee> employees) {
		this.crewId = crewId;
		this.crewName = crewName;
		this.employees = employees;
	}

	public Crew(String crewName) {
		this.crewName = crewName;
	}

	public Long getCrewId() {
		return crewId;
	}

	public void setCrewId(Long idCrew) {
		this.crewId = idCrew;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public String getCrewName() {
		return crewName;
	}

	public void setCrewName(String crewName) {
		this.crewName = crewName;
	}

}
