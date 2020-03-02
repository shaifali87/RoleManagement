package com.roleManagement.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.roleManagement.Entity.Message;
import com.roleManagement.Entity.User;
import com.roleManagement.Repository.MessageRepository;
import com.roleManagement.Repository.UserRepository;
import com.roleManagement.service.MessageService;
import com.roleManagement.service.SecurityService;
import com.roleManagement.service.UserService;
import com.roleManagement.validator.UserValidator;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MessageRepository messageRepository;

	@Autowired(required=true)
	private MessageService messageService;

	@RequestMapping("/user")
	public String user() {
		return "user";
	}

	@RequestMapping("/admin")
	public String admin() {
		return "admin";
	}

	@RequestMapping(value = { "/" })
	public String home() {
		return "home";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("userForm", new User());

		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "registration";
		}

		userService.save(userForm);

		securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

		return "redirect:/welcome";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password are invalid.");

		if (logout != null)
			model.addAttribute("message", "You have logged out successfully.");

		return "login";
	}

	@RequestMapping(value = { "/welcome" }, method = RequestMethod.GET)
	public String welcome(Model model) {
		return "welcome";
	}

	/*
	 * @RequestMapping("/user/allUsers") public List<User> getAllUsers() { return
	 * userRepository.findByAuthority("user"); }
	 * 
	 * @RequestMapping("/admin") public List<User> getAllAdmins() { return
	 * userRepository.findByAuthority("admin"); }
	 */

	@RequestMapping(value = "/allUser", method = RequestMethod.GET)
	public ModelAndView getUser(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("user");
		System.out.println("in getuser");
		List<User> userList = userService.getAllUsers();
		modelAndView.addObject("userList", userList);
		return modelAndView;
	}

	@RequestMapping(value = "/allAdmin", method = RequestMethod.GET)
	public ModelAndView getAdmin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("admin");
		System.out.println("in getadmin");
		List<User> adminList = userService.getAllAdmins();
		modelAndView.addObject("adminList", adminList);
		return modelAndView;
	}
	
	@RequestMapping(value = "/messages/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("message")Message message) {
		messageService.saveMessage(message);
		System.out.println("in save message");
		return "success";
	}


}
