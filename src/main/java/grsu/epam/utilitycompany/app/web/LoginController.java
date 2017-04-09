package grsu.epam.utilitycompany.app.web;

import grsu.epam.utilitycompany.app.domain.UserPrincipal;
import grsu.epam.utilitycompany.app.service.UserPrincipalService;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class LoginController {
	@Autowired
	private UserPrincipalService userService;

	@PreAuthorize("permitAll")
	@RequestMapping(value = { "", "/", "/login" }, method = { RequestMethod.GET })
	public ModelAndView login(HttpSession session,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		model.setViewName("login");

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
			session.invalidate();
		} else if (isUserSessionFound(session)) {
			model.setViewName("profile");
		}

		return model;
	}

	@RequestMapping(value = "/access-denied")
	public String accessDenied(HttpSession session) {
		return "access-denied";
	}

	private boolean isUserSessionFound(HttpSession session) {
		return session.getAttribute("user") instanceof UserPrincipal;
	}

}
