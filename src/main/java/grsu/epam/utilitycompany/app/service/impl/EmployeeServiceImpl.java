package grsu.epam.utilitycompany.app.service.impl;

import java.util.List;

import grsu.epam.utilitycompany.app.domain.Crew;
import grsu.epam.utilitycompany.app.domain.Employee;
import grsu.epam.utilitycompany.app.domain.Speciality;
import grsu.epam.utilitycompany.app.domain.Task;
import grsu.epam.utilitycompany.app.repository.EmployeeDao;
import grsu.epam.utilitycompany.app.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeRepository;

	@Override
	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);

	}

	@Override
	public void deleteEmployee(Employee employee) {
		employeeRepository.delete(employee);

	}

	@Override
	public Employee getEmployeeByID(Long employeeId) {
		return employeeRepository.findById(employeeId);
	}

	@Override
	public List<Employee> getEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		return employees;
	}

	@Override
	public void updateEmployee(Employee employee) {
		employeeRepository.update(employee);

	}

	@Override
	public List<Employee> getEmployeesByCrew(Crew crew) {
		List<Employee> employees = employeeRepository.findEmployeesByCrew(crew);		
		return employees;
	}

	@Override
	public void updateFromCrew(Employee employee) {
		employeeRepository.updateFromCrew(employee);
		
	}

	@Override
	public List<Employee> getFreeEmployeesBySpeciality(Speciality speciality) {
		List<Employee> employees = employeeRepository.findEmployeesByCrew(speciality);		
		return employees;
	}
}
