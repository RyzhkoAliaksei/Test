package grsu.epam.utilitycompany.app.web;

import grsu.epam.utilitycompany.app.domain.Dispatcher;
import grsu.epam.utilitycompany.app.domain.Role;
import grsu.epam.utilitycompany.app.service.DispatcherService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("dispatcher")
public class DispatcherController {

	@Autowired
	private DispatcherService dispatcherService;

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String showAll(Model model) {
		model.addAttribute("dispatchers", dispatcherService.getDispatchers());
		return "dispatcher.list";
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newDispatcherForm(Model model) {

		model.addAttribute("dispatcher", new Dispatcher());
		model.addAttribute("action", "new");

		ModelAndView mav = new ModelAndView();
		mav.setViewName("dispatcher.form");
		return mav;
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String newDispatcherSubmit(
			@Valid @ModelAttribute Dispatcher dispatcher, BindingResult result,
			Model model) {
		String view = "dispatcher.list";
		if (result.hasErrors()) {
			view = "dispatcher.form";
		} else {

			if (dispatcher != null) {
				dispatcher.setRole(Role.DISPATCHER);
				PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String encodedPassword = passwordEncoder.encode(dispatcher
						.getPassword());
				dispatcher.setPassword(encodedPassword);
				dispatcherService.saveDispatcher(dispatcher);
				model.addAttribute("dispatchers",
						dispatcherService.getDispatchers());
			}
			model.asMap().remove("dispatcher");
		}
		return view;
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "/edit/{userId}", method = RequestMethod.GET)
	public String editDispatcherForm(
			@PathVariable("userId") Dispatcher dispatcher, HttpSession session,
			Model model) {
		model.addAttribute("dispatcher", dispatcher);
		model.addAttribute("action", dispatcher.getUserId());

		return "dispatcher.form";
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "/edit/{userId}", method = RequestMethod.POST)
	public String editDispatcherSubmit(@ModelAttribute Dispatcher dispatcher,
			Model model) {
		dispatcherService.updateDispatcher(dispatcher);
		model.addAttribute("dispatchers", dispatcherService.getDispatchers());
		model.asMap().remove("dispatcher");
		return "dispatcher.list";
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_DISPATCHER')")
	@RequestMapping(value = "/delete/{userId}", method = RequestMethod.GET)
	public String deleteSpeciality(
			@PathVariable("userId") Dispatcher dispatcher, HttpSession session,
			Model model) {

		dispatcherService.deleteDispatcher(dispatcher);
		model.addAttribute("dispatchers", dispatcherService.getDispatchers());

		return "dispatcher.list";
	}
}
