package grsu.epam.utilitycompany.app.service;

import grsu.epam.utilitycompany.app.domain.Crew;
import grsu.epam.utilitycompany.app.domain.Employee;
import grsu.epam.utilitycompany.app.domain.Speciality;

import java.util.List;

public interface EmployeeService {

	void saveEmployee(Employee employee);
	
	void deleteEmployee(Employee employee);
	
	Employee getEmployeeByID(Long employeeId);
	
	List<Employee> getEmployees();

	void updateEmployee(Employee employee);

	List<Employee> getEmployeesByCrew(Crew crew);

	void updateFromCrew(Employee employee);

	List<Employee> getFreeEmployeesBySpeciality(Speciality speciality);
}