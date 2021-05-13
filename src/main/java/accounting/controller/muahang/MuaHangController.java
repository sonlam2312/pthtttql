package accounting.controller.muahang;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MuaHangController {
	@GetMapping("/chungtumuahang")
	public String chungtumuahang() {
		return "muahang/chungtumua";
	}
	@GetMapping("/form_chungtumua")
	public String form_chungtumua() {
		return "muahang/form_muahang";
	}
}
