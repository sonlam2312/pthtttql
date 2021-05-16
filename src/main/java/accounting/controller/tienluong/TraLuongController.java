package accounting.controller.tienluong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TraLuongController {
	@GetMapping("/hachtoan")
	public String HachToan() {
		return "tienluong/hachtoanluong";
	}
}
