package accounting.controller.muahang;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaoGiaController {
	@GetMapping("/baogia")
	public String baogia() {
		return "muahang/baogia";
	}
	@GetMapping("/form_baogia")
	public String formBaoGia() {
		return "muahang/form_baogia";
	}
}
