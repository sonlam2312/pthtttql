package accounting.controller.tienluong;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import accounting.repository.NhanVienRepo;

@Controller
public class NhanVienController {
	@Autowired
	private NhanVienRepo nhanVienRepo;
	@GetMapping("/nhanvien")
	public String NhanVien(Model model) {
		List<accounting.model.NhanVien> listNhanVien = nhanVienRepo.findAll();
		model.addAttribute("listNhanVien", listNhanVien);
		return "tienluong/nhanvien";
	}
	@GetMapping("/form_nhanvien")
	public String formNhanVien(Model model) {
		model.addAttribute("nhanvien", new accounting.model.NhanVien());
		return "tienluong/form_nhanvien";
	}
	@GetMapping("/search_nhanvien")
	public String TimKiem(@RequestParam String txt_nhanvien, Model model) {
		List<accounting.model.NhanVien> listNhanVien = nhanVienRepo.findAllByMaNhanVien(txt_nhanvien);
		model.addAttribute("listNhanVien", listNhanVien);
		return "tienluong/nhanvien";
	}
	@PostMapping("/add_nhanvien")
	public String ThemNhanVien(@ModelAttribute("nhanvien") accounting.model.NhanVien nhanvien) {
		nhanVienRepo.save(nhanvien);
		return "redirect:/nhanvien";
	}
	@GetMapping("/edit_nhanvien")
	public String FormSuaNhanVien(Model model, @RequestParam int id_nhanvien) {
		Optional<accounting.model.NhanVien> listOptional = nhanVienRepo.findById(id_nhanvien);
		model.addAttribute("nhanvien", listOptional.get());
		return "tienluong/form_suanhanvien";
	}
	@GetMapping("/update_nhanvien")
	public String SuaNhanVien(@ModelAttribute("nhanvien") accounting.model.NhanVien nhanvien) {
		nhanVienRepo.save(nhanvien);
		return "redirect:/nhanvien";
	}
	@GetMapping("/delete_nhanvien")
	public String XoaNhanVien(@RequestParam int id_nhanvien) {
		Optional<accounting.model.NhanVien> listOptional = nhanVienRepo.findById(id_nhanvien);
		nhanVienRepo.delete(listOptional.get());
		return "redirect:/nhanvien";
	}
}
