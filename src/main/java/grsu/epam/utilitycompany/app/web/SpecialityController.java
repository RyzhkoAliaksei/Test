package grsu.epam.utilitycompany.app.web;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import grsu.epam.utilitycompany.app.domain.Speciality;
import grsu.epam.utilitycompany.app.service.SpecialityService;

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
@RequestMapping("speciality")
public class SpecialityController {
	private static final Logger LOG = Logger
			.getLogger(SpecialityController.class);
	@Autowired
	private SpecialityService specialityService;

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String showAll(Model model) {
		model.addAttribute("specialities", specialityService.getSpecialities());
		return "speciality.list";
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newSpecialityForm(Model model) {

		model.addAttribute("speciality", new Speciality());
		model.addAttribute("action", "new");
		model.addAttribute("form_modification", "speciality.new");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("speciality.form");
		return mav;
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String newSpecialitySubmit(
			@Valid @ModelAttribute Speciality speciality, BindingResult result,
			Model model) {
		String view = "speciality.list";
		if (result.hasErrors()) {
			model.addAttribute("form_modification", "speciality.new");
			view = "speciality.form";
		} else {

			specialityService.saveSpeciality(speciality);
			model.addAttribute("specialities",
					specialityService.getSpecialities());
			LOG.info("Add speciality-" + speciality.getSpecialityName());

			model.asMap().remove("speciality");
		}
		return view;
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "/edit/{specialityId}", method = RequestMethod.GET)
	public String editSpecialityForm(
			@PathVariable("specialityId") Speciality speciality,
			HttpSession session, Model model) {
		model.addAttribute("speciality", speciality);
		model.addAttribute("action", speciality.getSpecialityId());
		model.addAttribute("form_modification", "speciality.edit");
		return "speciality.form";
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "/edit/{specialityId}", method = RequestMethod.POST)
	public String editSpecialitySubmit(
			@Valid @ModelAttribute Speciality speciality, BindingResult result,
			Model model) {
		String view = "speciality.list";
		if (result.hasErrors()) {
			model.addAttribute("form_modification", "speciality.edit");
			view = "speciality.form";
		} else {

			specialityService.updateSpeciality(speciality);
			model.addAttribute("specialities",
					specialityService.getSpecialities());
			LOG.info("Modify speciality-" + speciality.getSpecialityName());
			model.asMap().remove("speciality");
		}
		return view;
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "/delete/{specialityId}", method = RequestMethod.GET)
	public String deleteSpeciality(
			@PathVariable("specialityId") Speciality speciality,
			HttpSession session, Model model) {
		specialityService.deleteSpeciality(speciality);
		LOG.info("delete speciality-" + speciality.getSpecialityName());
		model.addAttribute("specialities", specialityService.getSpecialities());

		return "speciality.list";
	}
}
