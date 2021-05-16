package accounting.controller.kho;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NhapKhoController {
	@GetMapping("/nhapkho")
	public String nhapkho() {
		return "kho/nhapkho";
	}
	@GetMapping("/form_nhapkho")
	public String form_nhapkho() {
		return "kho/form_nhapkho";
	}
}
