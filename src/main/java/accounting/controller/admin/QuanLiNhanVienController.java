package accounting.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuanLiNhanVienController {
	@GetMapping("/admin_qlnv")
	public String QuanLiNhanVien() {
		return "admin/qlnhanvien";
	}
}
