package accounting.controller.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import accounting.model.Account;
import accounting.repository.AccountRepo;

@Controller
public class HomeController {
	@Autowired
	private AccountRepo accountRepo;
	@GetMapping("/")
	public String login(Model model) {
		Account a = new Account();
		model.addAttribute("account", a);
		return "login";
	}
	@GetMapping("/login")
	public String checkLogin(@ModelAttribute("account") Account account, Model model) {
		String url = "";
		String username = account.getUsername();
		String password = account.getPassword();
		if(username.equals("admin") && password.equals("admin")){
			model.addAttribute("username", username);
			url = "admin/admin_index";
			return url;
		}
		List<Account> a = accountRepo.findByUsernameAndPassword(username, password);
		if(a.isEmpty()) {
			model.addAttribute("message", "Tài khoản hoặc mật khẩu không hợp lệ");
			url =  "login";
		}else {		
			url = "index";
		}
		model.addAttribute("username", username);
		return url;
	}
	@GetMapping("/home")
	public String home() {
		return "index";
	}
	@GetMapping("/admin_home")
	public String AdminHome() {
		return "admin/admin_index";
	}
	@GetMapping("/dangxuat")
	public String DangXuat() {
		return "redirect:/";
	}
}
