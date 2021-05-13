package accounting.controller.banhang;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChungTuBanController {
	@GetMapping("/chungtuban")
	public String chungTuBan() {
		return "banhang/chungtuban";
	}
	@GetMapping("/form_chungtuban")
	public String formChungTuBan() {
		return "banhang/form_banhang";
	}
}
