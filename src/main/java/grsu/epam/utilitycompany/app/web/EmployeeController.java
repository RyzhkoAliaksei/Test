package grsu.epam.utilitycompany.app.web;

import java.util.LinkedHashMap;
import java.util.Map;

import grsu.epam.utilitycompany.app.domain.Crew;
import grsu.epam.utilitycompany.app.domain.Employee;
import grsu.epam.utilitycompany.app.domain.Speciality;
import grsu.epam.utilitycompany.app.service.CrewService;
import grsu.epam.utilitycompany.app.service.EmployeeService;
import grsu.epam.utilitycompany.app.service.SpecialityService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("employee")
public class EmployeeController {
	private static final Logger LOG = Logger
			.getLogger(EmployeeController.class);
	@Autowired
	private CrewService crewService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private SpecialityService specialityService;

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String showAll(Model model) {
		model.addAttribute("employees", employeeService.getEmployees());
		return "employee.list";
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "employees/{crewId}", method = RequestMethod.GET)
	public String showEmployeesbyCrew(Model model,
			@PathVariable("crewId") Crew crew) {
		model.addAttribute("employees",
				employeeService.getEmployeesByCrew(crew));
		model.addAttribute("crew", crew);
		return "crew.employees.list";
	}

	// ***********************
	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newEmployeeForm(Model model) {

		model.addAttribute("employee", new Employee());
		model.addAttribute("form_modification", "employee.new");
		model.addAttribute("action", "new");
		specialityMap(model);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("employee.form");
		return mav;
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String newEmployeeSubmit(@Valid @ModelAttribute Employee employee,BindingResult result,
			Model model) {
		String view = "employee.list";
		if (result.hasErrors()) {
			specialityMap(model);
			model.addAttribute("form_modification", "employee.new");
			view = "employee.form";
		} else {
		
			employeeService.saveEmployee(employee);
			model.addAttribute("employees", employeeService.getEmployees());
			LOG.info("Add employee-" + employee.getName());
		
		model.asMap().remove("employee");
		}
		return view;
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editEmployeeForm(@PathVariable("id") Employee employee,
			HttpSession session, Model model) {
		model.addAttribute("employee", employee);
		model.addAttribute("action", employee.getId());
		model.addAttribute("form_modification", "employee.edit");
		specialityMap(model);
		return "employee.form";
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String editEmployeeSubmit(@Valid @ModelAttribute Employee employee,BindingResult result,
			Model model) {
		String view = "employee.list";
		if (result.hasErrors()) {
			specialityMap(model);
			model.addAttribute("form_modification", "employee.edit");
			view = "employee.form";
		} else {
			employeeService.updateEmployee(employee);
			LOG.info("Edit employee-" + employee.getName());
			model.addAttribute("employees", employeeService.getEmployees());
			model.asMap().remove("employee");}
		return view;
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable("id") Employee employee,
			HttpSession session, Model model) {

		employeeService.deleteEmployee(employee);
		LOG.info("delete employee-" + employee.getName());
		model.addAttribute("employees", employeeService.getEmployees());

		return "employee.list";
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "/delete-from-crew/{id}", method = RequestMethod.GET)
	public String deleteEmployeeFromCrew(@PathVariable("id") Employee employee,
			HttpSession session, Model model) {
		long crewId = employee.getCrew().getCrewId();
		employee.getCrew().setCrewId((long) 0);
		employeeService.updateFromCrew(employee);
		model.addAttribute("employees", employeeService
				.getEmployeesByCrew(crewService.getCrewByID(crewId)));
		model.addAttribute("crewId", crewId);
		return "redirect:/employee/employees/{crewId}";
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "/choose-from-crew/{crewId}", method = RequestMethod.GET)
	public String chooseEmployeeFromCrew(HttpSession session, Model model,
			@PathVariable("crewId") Crew crew) {
		specialityMap(model);
		Employee employee = new Employee();
		employee.setCrew(crew);
		model.addAttribute("employee", employee);
		model.addAttribute("action", crew.getCrewId());
		model.addAttribute("form_modification", "choose.speciality");
		return "choose.speciality";
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "/choose-from-crew/{crewId}", method = RequestMethod.POST)
	public String chooseEmployeeFromCrewSubmit(Model model,
			@ModelAttribute Employee employee) {
		model.addAttribute("crewId", employee.getCrew().getCrewId());
		model.addAttribute("employees", employeeService
				.getFreeEmployeesBySpeciality(employee.getSpeciality()));
		return "speciality.employee.free.list";
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "{id}/add-from-crew/{crewId}", method = RequestMethod.GET)
	public String addEmployeeFromCrew(Model model,
			@PathVariable("crewId") Crew crew,
			@PathVariable("id") Employee employee) {
		System.out.println(employee.getName());
		System.out.println(crew.getCrewId());
		long crewId = crew.getCrewId();
		employee.setCrew(crew);
		employeeService.updateFromCrew(employee);
		model.addAttribute("employees",
				employeeService.getEmployeesByCrew(crew));
		model.addAttribute("crewId", crewId);
		return "redirect:/employee/employees/{crewId}";
	}

	private void specialityMap(Model model) {
		Map<Long, String> specialityList = new LinkedHashMap<Long, String>();
		for (final Speciality speciality : specialityService.getSpecialities()) {
			specialityList.put(speciality.getSpecialityId(),
					speciality.getSpecialityName());
		}
		model.addAttribute("specialityList", specialityList);

	}
}
