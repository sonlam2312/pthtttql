package accounting.controller.banhang;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import accounting.model.KhachHang;
import accounting.repository.KhachHangRepo;

@Controller
public class KhachHangController {
	@Autowired
	private KhachHangRepo khachHangRepo;
	@GetMapping("/khachhang")
	public String khachhang(Model model) {
		List<KhachHang> listKhachHang = khachHangRepo.findAll();
		model.addAttribute("listKhachHang", listKhachHang);
		return "banhang/khachhang";
	}
	@GetMapping("/form_khachhang")
	public String formkhachhang(Model model) {
		KhachHang kh = new KhachHang();
		model.addAttribute("khachhang", kh);
		return "banhang/form_khachhang";
	}
	@PostMapping("/khachhang_add")
	public String ThemKhachHang(@ModelAttribute("khachhang") KhachHang khachHang) {
		khachHangRepo.save(khachHang);
		return "redirect:/khachhang";
	}
	@GetMapping("/search_khachhang")
	public String TimKiem(@RequestParam String txt_khachhang, Model model) {
		List<KhachHang> listKhachHang = khachHangRepo.findAllByMaDoiTuong(txt_khachhang);
		model.addAttribute("listKhachHang", listKhachHang);
		return "banhang/khachhang";
	}
	@GetMapping("/delete_khachhang")
	public String XoaKhachHang(@RequestParam String maDoiTuong) {
		List<KhachHang> listKhachHang = khachHangRepo.findAllByMaDoiTuong(maDoiTuong);
		KhachHang kh = listKhachHang.get(0);
		khachHangRepo.delete(kh);
		return "redirect:/khachhang";
	}
	@GetMapping("/edit_khachhang")
	public String FormSuaKhachHang(Model model, @RequestParam String maDoiTuong) {
		List<KhachHang> listKhachHang = khachHangRepo.findAllByMaDoiTuong(maDoiTuong);
		KhachHang khachhang = listKhachHang.get(0);
		model.addAttribute("khachhang", khachhang);
		return "banhang/form_suakhachhang";
	}
	@PostMapping("/khachhang_update")
	public String SuaKhachHang(@ModelAttribute("khachhang") KhachHang khachhang) {
		khachHangRepo.save(khachhang);
		return "redirect:/khachhang";
	}
}
