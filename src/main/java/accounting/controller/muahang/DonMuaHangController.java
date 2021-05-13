package accounting.controller.muahang;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DonMuaHangController {
	@GetMapping("/donmuahang")
	public String donmuahang() {
		return "muahang/donmuahang";
	}
	@GetMapping("/form_donmua")
	public String formDonMua() {
		return "muahang/form_donmua";
	}
}
