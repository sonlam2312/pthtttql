package accounting.controller.tienluong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NhanVienController {
	@GetMapping("/nhanvien")
	public String NhanVien() {
		return "tienluong/nhanvien";
	}
	@GetMapping("/form_nhanvien")
	public String formNhanVien() {
		return "tienluong/form_nhanvien";
	}
}
