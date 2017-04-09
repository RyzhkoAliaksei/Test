package grsu.epam.utilitycompany.app.web;

import grsu.epam.utilitycompany.app.domain.UserPrincipal;
import grsu.epam.utilitycompany.app.security.ApplicationUserDetails;
import grsu.epam.utilitycompany.app.service.UserPrincipalService;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/profile")
public class ProfileController {
	private static final Logger LOG = Logger
			.getLogger(ProfileController.class);

	@Autowired
	private UserPrincipalService userService;

	@PreAuthorize("isFullyAuthenticated() and hasAnyRole('ROLE_DISPATCHER','ROLE_CLIENT')")
	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public String showUserProfile(Authentication authentication,
			HttpSession session, Model model) {

		ApplicationUserDetails appUser = (ApplicationUserDetails) authentication
				.getPrincipal();
		UserPrincipal user = appUser.getAccount();
		session.setAttribute("user", user);

		return "profile";
	}

	@PreAuthorize("isFullyAuthenticated() and hasAnyRole('ROLE_DISPATCHER','ROLE_CLIENT')")
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public ModelAndView showUserProfileByID(Authentication authentication,
			@PathVariable String userId,
			@PathVariable("userId") UserPrincipal userModelTest,
			HttpSession session, Model model) {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("profile");
		mav.addObject("userModelTest", userModelTest);

		Object userObject = session.getAttribute("user");
		if ((userObject != null) && (userObject instanceof UserPrincipal)) {
			UserPrincipal user = (UserPrincipal) userObject;

			mav.addObject("user.name", user.getName());
			LOG.info("Found an user with ID: " + user.getUserId());
			
		}

		return mav;
	}
}