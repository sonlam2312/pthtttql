package accounting.controller.baocao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaoCaoCongNoController {
	@GetMapping("/baocaocongno")
	public String BaoCaoCongNo() {
		return "baocao/baocaocongno";
	}
}
