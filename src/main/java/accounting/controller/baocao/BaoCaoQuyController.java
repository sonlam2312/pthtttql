package accounting.controller.baocao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaoCaoQuyController {
	@GetMapping("/baocaoquy")
	public String FormQuy() {
		return "baocao/form_quy";
	}
	@GetMapping("/ketquabaocaoquy")
	public String BaoCaoQuy() {
		return "baocao/baocaoquy";
	}
}
