package accounting.controller.tienluong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TraLuongController {
	@GetMapping("/chitamung")
	public String ChiTamUng() {
		return "tienluong/chitien";
	}
	@GetMapping("/thutamung")
	public String ThuTamUng() {
		return "tienluong/thutien";
	}
	@GetMapping("/hachtoan")
	public String HachToan() {
		return "tienluong/hachtoanluong";
	}
}
