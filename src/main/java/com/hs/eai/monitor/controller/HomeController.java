package com.hs.eai.monitor.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the index view to render by returning its name.
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String showIndex(HttpServletRequest  request ,Model model, Principal principal) {
		logger.info("Showing index page..");
		
		String username = principal.getName();
		request.getSession().setAttribute("username", username);
		logger.debug("username: " + username);
		model.addAttribute("username", username);
		return "home";
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showHome(Model model, Principal principal) {

		return "home";
	}

	@RequestMapping(value = "/invalidSession", method = RequestMethod.GET)
	public String redirectHome() {
		return "redirect:/home.html";
	}

}
