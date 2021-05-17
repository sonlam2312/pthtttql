package accounting.controller.banhang;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CongNoThuController {
	@GetMapping("/congnothu")
	public String CongNoThu() {
		return "banhang/congnothu";
	}
	@GetMapping("/form_congnothu")
	public String FormCongNoThu() {
		return "banhang/form_congnothu";
	}
}
