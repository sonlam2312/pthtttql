package accounting.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
	@GetMapping("/")
	public String login() {
		return "login";
	}
	@PostMapping("/login")
	public String checkLogin() {
		return "redirect:/admin_home";
	}
	@GetMapping("/home")
	public String home() {
		return "index";
	}
	@GetMapping("/admin_home")
	public String AdminHome() {
		return "admin/admin_index";
	}
}
