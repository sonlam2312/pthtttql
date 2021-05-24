package accounting.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuanLiTaiKhoanController {
	@GetMapping("admin_qltk")
	public String QuanLiTaiKhoan() {
		return "admin/qltaikhoan";
	}

	@GetMapping("/form_taotaikhoan")
	public String FormTaoTaiKhoan() {
		return "admin/form_taotaikhoan";
	}
}
