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
@Table(name = "speciality")
public class Speciality {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SPECIALITY_ID")
	private Long specialityId;

	@NotEmpty
	@Size(min=1, max=45)
	@Column(name = "SPECIALITY_NAME")
	private String specialityName;

	@OneToMany(targetEntity = Employee.class, mappedBy = "speciality", fetch = FetchType.EAGER)
	private List<Employee> employees;

	public Speciality(Long specialityId, String specialityName) {
		this.specialityId = specialityId;
		this.specialityName = specialityName;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Speciality() {
		// TODO Auto-generated constructor stub
	}

	public Speciality(Long specialityId) {
		this.specialityId = specialityId;
	}

	public Long getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(Long specialityId) {
		this.specialityId = specialityId;
	}

	public String getSpecialityName() {
		return specialityName;
	}

	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}
}
