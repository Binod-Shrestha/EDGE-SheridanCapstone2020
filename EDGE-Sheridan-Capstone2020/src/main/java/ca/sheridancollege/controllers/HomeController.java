package ca.sheridancollege.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.beans.User;
import ca.sheridancollege.repositories.RoleRepository;
import ca.sheridancollege.repositories.UserRepository;

@Controller
public class HomeController {
	@Autowired
	@Lazy
	public UserRepository userRepo;
	
	@Autowired
	public RoleRepository roleRepo;
	

	@GetMapping("/")
	public String goHome() {
		return "home.html";
	}

	@GetMapping("/user")
	public String goUserHome(Authentication auth) {
		
		String name = auth.getName();//get user name
		List<String> roles = new ArrayList<String>();
		for(GrantedAuthority ga : auth.getAuthorities()) {
			roles.add(ga.getAuthority());			
		}
		
		return "/user/home.html";
	}
	
	
	@GetMapping("/admin")
	public String goAdminHome(Authentication auth) {
		
		String name = auth.getName();//get user name
		List<String> roles = new ArrayList<String>();
		for(GrantedAuthority ga : auth.getAuthorities()) {
			roles.add(ga.getAuthority());			
		}
		
		return "/admin/admin.html";
	}
	@GetMapping("/basic")
	public String goBasicHome(Authentication auth) {
		
		String name = auth.getName();//get user name
		List<String> roles = new ArrayList<String>();
		for(GrantedAuthority ga : auth.getAuthorities()) {
			roles.add(ga.getAuthority());			
		}
		
		return "/basic/basic.html";
	}
	@GetMapping("/Soar")
	public String goSoarHome(Authentication auth) {
		
		String name = auth.getName();//get user name
		List<String> roles = new ArrayList<String>();
		for(GrantedAuthority ga : auth.getAuthorities()) {
			roles.add(ga.getAuthority());			
		}
		
		return "/soar/soar.html";
	}
	
	@GetMapping("/rise")
	public String goRiseHome(Authentication auth) {
		
		String name = auth.getName();//get user name
		List<String> roles = new ArrayList<String>();
		for(GrantedAuthority ga : auth.getAuthorities()) {
			roles.add(ga.getAuthority());			
		}
		
		return "/rise/rise.html";
	}
	
	
	
	
	
	

	// just loading the page
	@GetMapping("/login")
	public String goLogin() {
		return "login.html";
	}

	@GetMapping("/access-denied")
	public String goAccessDenied() {
		return "/error/access-denied.html";
	}

	@GetMapping("/register")
	public String goResitration() {
		return "/user/register.html";
	}
	private String encryptPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}

	@PostMapping("/register")
	public String goResitration(@RequestParam String username, @RequestParam String password) {
		User user = new User(username, encryptPassword(password));
		user.getRoles().add(roleRepo.findByRolename("ROLE_ADMIN"));
		user.getRoles().add(roleRepo.findByRolename("ROLE_USER"));
		userRepo.save(user);

		return "redirect:/";
	}

}
