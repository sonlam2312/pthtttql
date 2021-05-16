package accounting.controller.kho;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KhoController {
	@GetMapping("/kho")
	public String kho() {
		return "kho/kho";
	}
	@GetMapping("/hanghoa")
	public String hanghoa() {
		return "kho/hanghoa";
	}
	@GetMapping("/form_hanghoa")
	public String formHangHoa() {
		return "kho/form_hanghoa";
	}
}
