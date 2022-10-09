package com.app.web.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.web.entity.Role;
import com.app.web.entity.User;
import com.app.web.repository.RoleRepository;
import com.app.web.service.UserService;
import com.app.web.util.reports.UserExporterExcel;
import com.app.web.util.reports.UserExporterPDF;
import com.lowagie.text.DocumentException;

@Controller
public class UserController {
	@Autowired
	private UserService service;

	@Autowired
	private RoleRepository roleRepo;

	@GetMapping("/signup")
	public String registrationForm(Model model) {
		List<Role> roles = roleRepo.findAll();

		model.addAttribute("user", new User());
		model.addAttribute("roles", roles);

		return "user_registration";
	}

	@PostMapping("/signup")
	public String processRegistration(User user) {
		service.saveUserWithDefaultRole(user);
		return "redirect:/login?success";
	}

	@GetMapping("/users")
	public String viewUserList(Model model) {
		List<User> userList = service.listAll();
		model.addAttribute("users", userList);
		return "user_list";
	}

	@GetMapping("/users/edit/{id}")
	public String editUser(@PathVariable("id") Long id, Model model) {
		User user = service.getUserById(id);
		List<Role> roleList = service.getRoles();

		model.addAttribute("user", user);
		model.addAttribute("roles", roleList);

		return "user_update";
	}

	@PostMapping("/users/edit/save")
	public String saveUser(User user) {
		service.saveUser(user);
		return "redirect:/users";
	}

	@GetMapping("/login")
	public String showLoginPage() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "user_login";
		}

		return "redirect:/index";
	}

	@GetMapping("/users/exportpdf")
	public void exportUserListPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");

		DateFormat dateFormatter = new SimpleDateFormat("yyy-MM-dd_HH:mm:ss");
		String currentDate = dateFormatter.format(new Date());

		String header = "Content-Disposition";
		String value = "attachment; filename=Users_" + currentDate + ".pdf";

		response.setHeader(header, value);

		List<User> users = service.listAll();

		UserExporterPDF exporter = new UserExporterPDF(users);
		exporter.exportTable(response);
	}

	@GetMapping("/users/exportxlsx")
	public void exportUserListExcel(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/octect-stream");

		DateFormat dateFormatter = new SimpleDateFormat("yyy-MM-dd_HH:mm:ss");
		String currentDate = dateFormatter.format(new Date());

		String header = "Content-Disposition";
		String value = "attachment; filename=Users_" + currentDate + ".xlsx";

		response.setHeader(header, value);

		List<User> users = service.listAll();

		UserExporterExcel exporter = new UserExporterExcel(users);
		exporter.exportTable(response);
	}
}
