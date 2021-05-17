package accounting.controller.thue;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KhaiThueController {
	@GetMapping("/khaibaothue")
	public String KhaiBaoThue() {
		return "thue/khaibaothue";
	}
	@GetMapping("/form_thue")
	public String formThue() {
		return "thue/form_thue";
	}
	@GetMapping("/tokhaithue")
	public String toKhaiThue() {
		return "thue/tokhaithue";
	}
}
