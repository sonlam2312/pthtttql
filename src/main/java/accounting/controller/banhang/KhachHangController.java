package accounting.controller.banhang;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KhachHangController {
	@GetMapping("/khachhang")
	public String khachhang() {
		return "banhang/khachhang";
	}
	@GetMapping("/form_khachhang")
	public String formkhachhang() {
		return "banhang/form_khachhang";
	}
}
