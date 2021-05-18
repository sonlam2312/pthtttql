package accounting.controller.baocao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaoCaoDSController {
	@GetMapping("/baocaodoanhso")
	public String FormDoanhSo() {
		return "baocao/form_doanhso";
	}
	@GetMapping("/ketquabaocaodoanhso")
	public String BaoCaoDoanhSo() {
		return "baocao/baocaodoanhso";
	}
}
