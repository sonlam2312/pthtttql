package accounting.controller.muahang;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NhaCungCapController {
	@GetMapping("/nhacungcap")
	public String nhacungcap() {
		return "muahang/nhacungcap";
	}
	@GetMapping("/form_ncc")
	public String formNhaCungCap() {
		return "muahang/form_ncc";
	}
}
