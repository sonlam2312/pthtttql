package accounting.controller.kho;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KiemKeController {
	@GetMapping("/kiemke")
	public String kiemke() {
		return "kho/kiemke";
	}
	@GetMapping("/form_ngaykiemke")
	public String NgayKiemKe() {
		return "kho/tao_kiemke";
	}
	@GetMapping("/form_kiemke")
	public String formKiemKe() {
		return "kho/form_kiemke";
	}
}
