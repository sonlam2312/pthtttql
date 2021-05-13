package accounting.controller.banhang;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DonBanHangController {
	@GetMapping("/donbanhang")
	public String baogiaban() {
		return "banhang/donbanhang";
	}
	
	@GetMapping("/form_donban")
	public String formDonBan(){
		return "banhang/form_donban";
	}
}
