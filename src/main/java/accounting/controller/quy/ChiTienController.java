package accounting.controller.quy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChiTienController {
	@GetMapping("/chitien")
	public String chitien() {
		return "quy/chitien";
	}
	@GetMapping("/form_phieuchi")
	public String formChiTien() {
		return "quy/form_chitien";
	}
}
