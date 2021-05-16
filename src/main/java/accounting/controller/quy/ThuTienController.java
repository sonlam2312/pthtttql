package accounting.controller.quy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThuTienController {
	@GetMapping("/thutien")
	public String thutien() {
		return "quy/thutien";
	}
	@GetMapping("/form_phieuthu")
	public String formThuTien() {
		return "quy/form_thutien";
	}
}
