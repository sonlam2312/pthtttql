package accounting.controller.kho;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class XuatKhoController {
	@GetMapping("/xuatkho")
	public String xuatkho() {
		return "kho/xuatkho";
	}
	@GetMapping("/form_xuatkho")
	public String form_xuatkho() {
		return "kho/form_xuatkho";
	}
}
