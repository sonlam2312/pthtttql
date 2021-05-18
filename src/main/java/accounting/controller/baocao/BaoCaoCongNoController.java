package accounting.controller.baocao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaoCaoCongNoController {
	@GetMapping("/baocaocongno")
	public String FormCongNo() {
		return "baocao/form_congno";
	}
	@GetMapping("/ketquabaocaocongno")
	public String BaoCaoCongNo() {
		return "baocao/baocaocongno";
	}
}
