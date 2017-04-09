package grsu.epam.utilitycompany.app.web;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import grsu.epam.utilitycompany.app.domain.Client;
import grsu.epam.utilitycompany.app.domain.Crew;
import grsu.epam.utilitycompany.app.domain.Demand;
import grsu.epam.utilitycompany.app.domain.Status;
import grsu.epam.utilitycompany.app.service.CrewService;
import grsu.epam.utilitycompany.app.service.DemandService;
import grsu.epam.utilitycompany.app.service.TaskService;
import grsu.epam.utilitycompany.app.service.exception.DateIsEmptyException;
import grsu.epam.utilitycompany.app.service.exception.IncorrectEditTimeException;
import grsu.epam.utilitycompany.app.service.exception.IncorrectPeriodTimeException;

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
@RequestMapping("demand")
public class DemandController {
	private static final Logger LOG = Logger.getLogger(DemandController.class);
	@Autowired
	private DemandService demandService;
	@Autowired
	private CrewService crewService;
	@Autowired
	private TaskService taskService;

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String showAll(Model model) {
		model.addAttribute("demands", demandService.getDemands());
		return "demand.dispatcher.list";
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "/choose/status", method = RequestMethod.GET)
	public String chooseStatus(Model model) {
		Demand demand = new Demand();
		model.addAttribute("demand", demand);
		model.addAttribute("action", "status");
		return "choose.status";
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "/choose/status", method = RequestMethod.POST)
	public String chooseStatusSubmit(Model model, @ModelAttribute Demand demand) {
		Status status = demand.getStatus();
		model.addAttribute("demands", demandService.getDemandsByStatus(status));
		return "demand.dispatcher.list";
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "/choose/surname", method = RequestMethod.GET)
	public String chooseSurname(Model model) {
		Client client = new Client();
		model.addAttribute("client", client);
		model.addAttribute("action", "surname");
		return "choose.surname";
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "/choose/surname", method = RequestMethod.POST)
	public String chooseSurnameSubmit(Model model, @ModelAttribute Client client) {
		String surname = client.getSurname();
		model.addAttribute("demands",
				demandService.getDemandsBySurname(surname));
		return "demand.dispatcher.list";
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_CLIENT')")
	@RequestMapping(value = "/demands", method = RequestMethod.GET)
	public String showDemandsbyClient(Model model, HttpSession session) {
		Client client = (Client) session.getAttribute("user");
		model.addAttribute("demands", demandService.getDemandsByClient(client));
		return "demand.list";
	}

	// ***********************
	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_CLIENT')")
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newDemandForm(Model model) {

		model.addAttribute("demand", new Demand());
		model.addAttribute("action", "new");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("demand.form");
		return mav;
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_CLIENT')")
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String newDemandSubmit(@Valid @ModelAttribute Demand demand,
			BindingResult result, Model model, HttpSession session) {
		String view = "demand.list";
		if (result.hasErrors()) {
			view = "demand.form";
		} else {
			Client client = (Client) session.getAttribute("user");
			demand.setClient(client);
			demand.setStatus(Status.accept);;
			demandService.saveDemand(demand);
			LOG.info("Add demand-" + demand.getDemandId());
			model.addAttribute("demands",
					demandService.getDemandsByClient(client));
			model.asMap().remove("demand");
		}
		return view;
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_CLIENT')")
	@RequestMapping(value = "/edit/{demandId}", method = RequestMethod.GET)
	public String editDemandForm(@PathVariable("demandId") Demand demand,
			HttpSession session, Model model) {
		model.addAttribute("demand", demand);
		model.addAttribute("action", demand.getDemandId());
		return "demand.form";
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_CLIENT')")
	@RequestMapping(value = "/edit/{demandId}", method = RequestMethod.POST)
	public String editDemandSubmit(@Valid @ModelAttribute Demand demand,
			BindingResult result, Model model, HttpSession session) {
		String view = "demand.list";
		if (result.hasErrors()) {
			view = "demand.form";
		} else {
			Client client = (Client) session.getAttribute("user");
			demand.setClient(client);
			demandService.updateDemand(demand);
			model.addAttribute("demands",
					demandService.getDemandsByClient(client));
			model.asMap().remove("demand");
		}
		return view;
	}

	// dispatcher status
	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "/edit-status/{demandId}", method = RequestMethod.GET)
	public String editStatusForm(@PathVariable("demandId") Demand demand,
			HttpSession session, Model model) {
		model.addAttribute("demand", demand);
		model.addAttribute("action", demand.getDemandId());
		return "demand.form.dispatcher";
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "/edit-status/{demandId}", method = RequestMethod.POST)
	public String editStatusSubmit(@ModelAttribute Demand demand, Model model,
			HttpSession session) {
		String view = "demand.dispatcher.list";
		demandService.updateStatus(demand);
		model.addAttribute("demands", demandService.getDemands());
		model.asMap().remove("demand");
		return view;
	}

	// dispatcher time
	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "/edit-time/{demandId}", method = RequestMethod.GET)
	public String editTimeForm(@PathVariable("demandId") Demand demand,
			HttpSession session, Model model) {
		model.addAttribute("demand", demand);
		model.addAttribute("action", demand.getDemandId());
		return "demand.form.time.dispatcher";
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "/edit-time/{demandId}", method = RequestMethod.POST)
	public String editTimeSubmit(@Valid @ModelAttribute Demand demand,
			BindingResult result, Model model, HttpSession session) {
		String view = "demand.dispatcher.list";
		if (result.hasErrors()) {
			model.addAttribute("demand", demand);
			view = "demand.form.time.dispatcher";
		} else {

			try {
				view = "demand.dispatcher.list";
				demandService.updateTime(demand);
				model.addAttribute("demands", demandService.getDemands());
								
			} catch (IncorrectPeriodTimeException e) {
				model.addAttribute("message","start date has to be less than a date of the end");
				view = "error";
			} catch (IncorrectEditTimeException e) {
				model.addAttribute("message","at first remove crew then edit time");
				view = "error";
			}
		}
		return view;
	}

	// choose crew

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "/choose-crew/{demandId}", method = RequestMethod.GET)
	public String chooseCrewSubmit(Model model,
			@PathVariable("demandId") Demand demand) {
		model.addAttribute("demandId", demand.getDemandId());
		String view = "free.crew.list";
		try {
			model.addAttribute("crews", crewService.getFreeCrews(demand));
		} catch (DateIsEmptyException e) {
			model.addAttribute("message","at first enter time period");
			view = "error";
		}
		
		return view;
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "/{demandId}/add-free-crew/{crewId}", method = RequestMethod.GET)
	public String addCrewFromDemand(Model model,
			@PathVariable("crewId") Crew crew,
			@PathVariable("demandId") Demand demand) {
		demand.setCrew(crew);
		demandService.updateCrew(demand);

		model.addAttribute("demands", demandService.getDemands());
		return "demand.dispatcher.list";
	}

	/*
	 * delete crew
	 */
	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "/delete-crew/{demandId}", method = RequestMethod.GET)
	public String deleteCrewFromDemand(Model model,
			@PathVariable("demandId") Demand demand) {
		demandService.deleteCrew(demand);
		model.addAttribute("demands", demandService.getDemands());
		return "redirect:/demand";
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_CLIENT')")
	@RequestMapping(value = "/delete/{DemandId}", method = RequestMethod.GET)
	public String deleteDemand(@PathVariable("DemandId") Demand demand,
			HttpSession session, Model model) {
		demandService.deleteDemand(demand);
		Client client = (Client) session.getAttribute("user");
		demand.setClient(client);
		model.addAttribute("demands", demandService.getDemandsByClient(client));
		return "demand.list";
	}

}
