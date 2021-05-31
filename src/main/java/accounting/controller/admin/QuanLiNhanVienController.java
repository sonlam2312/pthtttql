package accounting.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import accounting.model.NhanVien;
import accounting.repository.NhanVienRepo;

@Controller
public class QuanLiNhanVienController {
	@Autowired
	private NhanVienRepo nhanVienRepo;
	@GetMapping("/admin_qlnv")
	public String QuanLiNhanVien(Model model) {
		List<NhanVien> listNhanVien = nhanVienRepo.findAll();
		model.addAttribute("listNhanVien", listNhanVien);
		return "admin/qlnhanvien";
	}
}
