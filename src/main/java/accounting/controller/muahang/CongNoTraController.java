package accounting.controller.muahang;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CongNoTraController {
	@GetMapping("/congnotra")
	public String CongNoTra() {
		return "muahang/congnotra";
	}
	@GetMapping("/form_congnotra")
	public String FormCongNoTra() {
		return "muahang/form_congnotra";
	}
}
