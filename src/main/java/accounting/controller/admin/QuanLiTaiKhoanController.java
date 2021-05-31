package accounting.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import accounting.model.Account;
import accounting.model.NhanVien;
import accounting.repository.AccountRepo;
import accounting.repository.NhanVienRepo;

@Controller
public class QuanLiTaiKhoanController {
	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private NhanVienRepo nhanVienRepo;
	@GetMapping("/admin_qltk")
	public String QuanLiTaiKhoan(Model model) {
		List<Account> listTaiKhoan = accountRepo.findAll();
		model.addAttribute("listTaiKhoan", listTaiKhoan);
		return "admin/qltaikhoan";
	}

	@GetMapping("/form_taotaikhoan")
	public String FormTaoTaiKhoan(Model model) {
		model.addAttribute("taikhoan", new Account());
		return "admin/form_taotaikhoan";
	}
	@PostMapping("/add_taikhoan")
	public String TaoTaiKhoan(@ModelAttribute("taikhoan") Account taikhoan) {
		String manhanvien = taikhoan.getNhanVien().getMaNhanVien();
		List<NhanVien> listOptional = nhanVienRepo.findAllByMaNhanVien(manhanvien);
		if(listOptional.isEmpty()) {
			return "form_taotaikhoan";
		}else {
			NhanVien nv = listOptional.get(0);
			taikhoan.setNhanVien(nv);
			accountRepo.save(taikhoan);
		}
		return "redirect:/admin_qltk";
	}
	@GetMapping("/edit_taikhoan")
	public String FormSuaTaiKhoang(@RequestParam int id_taikhoan, Model model) {
		Optional<Account> listOptional = accountRepo.findById(id_taikhoan);
		model.addAttribute("taikhoan", listOptional.get());
		return "admin/form_suataikhoan";
	}
	@GetMapping("/update_taikhoan")
	public String SuaTaiKhoan(@ModelAttribute("taikhoan") Account taikhoan) {
		String manhanvien = taikhoan.getNhanVien().getMaNhanVien();
		List<NhanVien> listOptional = nhanVienRepo.findAllByMaNhanVien(manhanvien);
		if(listOptional.isEmpty()) {
			return "form_taotaikhoan";
		}else {
			NhanVien nv = listOptional.get(0);
			taikhoan.setNhanVien(nv);
			accountRepo.save(taikhoan);
		}
		return "redirect:/admin_qltk";
	}
	@GetMapping("/delete_taikhoan")
	public String XoaTaiKhoan(@RequestParam int id_taikhoan, Model model) {
		Optional<Account> listOptional = accountRepo.findById(id_taikhoan);
		Account a = listOptional.get();
		a.setNhanVien(null);
		accountRepo.delete(a);
		return "redirect:/admin_qltk";
	}
}
