package grsu.epam.utilitycompany.app.web.converters;

import grsu.epam.utilitycompany.app.domain.Employee;
import grsu.epam.utilitycompany.app.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToEmployeeConverter implements Converter<String, Employee> {

	@Autowired
	private EmployeeService employeeService;

	@Override
	public Employee convert(String Id) {

		return employeeService.getEmployeeByID(new Long(Id));
	}

}