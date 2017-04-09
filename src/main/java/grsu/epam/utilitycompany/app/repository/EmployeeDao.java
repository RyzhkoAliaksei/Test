package grsu.epam.utilitycompany.app.repository;

import java.util.List;

import grsu.epam.utilitycompany.app.domain.Crew;
import grsu.epam.utilitycompany.app.domain.Employee;
import grsu.epam.utilitycompany.app.domain.Speciality;
import grsu.epam.utilitycompany.app.repository.base.GenericDao;

public interface EmployeeDao extends GenericDao<Employee, Long> {

	List<Employee> findEmployeesByCrew(Crew crew);

	void updateFromCrew(Employee employee);

	List<Employee> findEmployeesByCrew(Speciality speciality);

}
