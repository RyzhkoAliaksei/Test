package grsu.epam.utilitycompany.app.web;

import grsu.epam.utilitycompany.app.domain.Crew;
import grsu.epam.utilitycompany.app.service.CrewService;

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
@RequestMapping("crew")
public class CrewController {
	private static final Logger LOG = Logger.getLogger(CrewController.class);
	@Autowired
	private CrewService crewService;

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String showAll(Model model) {
		model.addAttribute("crews", crewService.getCrews());
		return "crew.list";
	}

	// ***********************
	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newCrewForm(Model model) {

		model.addAttribute("crew", new Crew());
		model.addAttribute("action", "new");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("crew.form");
		return mav;
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String newCrewSubmit(@Valid @ModelAttribute Crew crew,
			BindingResult result, Model model, HttpSession session) {
		String view = "crew.list";
		if (result.hasErrors()) {
			view = "crew.form";
		} else {
				crewService.saveCrew(crew);
				LOG.info("Add crew-" + crew.getCrewName());
				model.addAttribute("crews", crewService.getCrews());
				model.asMap().remove("crew");
		}

		return view;
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "/edit/{crewId}", method = RequestMethod.GET)
	public String editCrewForm(@PathVariable("crewId") Crew crew,
			HttpSession session, Model model) {
		model.addAttribute("crew", crew);
		model.addAttribute("action", crew.getCrewId());
		return "crew.form";
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "/edit/{crewId}", method = RequestMethod.POST)
	public String editCrewSubmit(@Valid @ModelAttribute Crew crew,BindingResult result, Model model,
			HttpSession session) {
		String view = "crew.list";
		if (result.hasErrors()) {
			view = "crew.form";
		} else {
			crewService.updateCrew(crew);
			model.addAttribute("crews", crewService.getCrews());
			LOG.info("Modify crew-" + crew.getCrewName());
		}
		model.asMap().remove("crew");
		return view;
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "/delete/{CrewId}", method = RequestMethod.GET)
	public String deleteCrew(@PathVariable("CrewId") Crew crew,
			HttpSession session, Model model) {

		crewService.deleteCrew(crew);
		model.addAttribute("crews", crewService.getCrews());

		return "crew.list";
	}

}
