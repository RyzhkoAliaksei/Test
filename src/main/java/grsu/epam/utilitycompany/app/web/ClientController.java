package grsu.epam.utilitycompany.app.web;

import javax.servlet.http.HttpSession;


import javax.validation.Valid;

import grsu.epam.utilitycompany.app.domain.Client;
import grsu.epam.utilitycompany.app.domain.Role;
import grsu.epam.utilitycompany.app.service.ClientService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/client")
public class ClientController {
	private static final Logger LOG = Logger.getLogger(ClientController.class);
	@Autowired
	private ClientService clientService;

	@PreAuthorize("permitAll")
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerUser(Model model) {

		Client user = new Client();
		model.addAttribute("client", user);
		model.addAttribute("action", "register");

		return "register";
	}

	@PreAuthorize("permitAll")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String newClientSubmit(@Valid @ModelAttribute Client client,BindingResult result,
			Model model, HttpSession session) {
		String view = "login";
		if (result.hasErrors()) {
			view = "register";
		} else {
		
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(client.getPassword());
			client.setRole(Role.CLIENT);
			client.setPassword(encodedPassword);
			clientService.saveClient(client);
			LOG.info("Register client-" + client.getLogin());
		}
		return view;
	}

}
