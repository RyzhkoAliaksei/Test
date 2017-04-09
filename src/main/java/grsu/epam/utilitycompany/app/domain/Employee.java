package grsu.epam.utilitycompany.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMPLOYEE_ID")
	private Long id;
	
	@NotEmpty
	@Size(min=1, max=45)
	@Column(name = "NAME")
	private String name;

	@NotEmpty
	@Size(min=1, max=45)
	@Column(name = "SURNAME")
	private String surname;

	@ManyToOne(targetEntity = Crew.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "Crew_ID")
	private Crew crew;

	@ManyToOne(targetEntity = Speciality.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "SPECIALITY_ID")
	private Speciality speciality;

	public Employee() {

	}

	public Employee(Long id, String name, String surname, Speciality speciality) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.speciality = speciality;
	}

	public Employee(String name, String surname, Speciality speciality) {
		this.name = name;
		this.surname = surname;
		this.speciality = speciality;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;

	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;

	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;

	}

	public Speciality getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}

	public Crew getCrew() {
		return crew;
	}

	public void setCrew(Crew crew) {
		this.crew = crew;
	}

}
