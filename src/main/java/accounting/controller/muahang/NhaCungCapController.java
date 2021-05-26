package accounting.controller.muahang;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import accounting.model.NhaCungCap;
import accounting.repository.NhaCungCapRepo;

@Controller
public class NhaCungCapController {
	@Autowired
	private NhaCungCapRepo doiTuongRepo;
	@GetMapping("/nhacungcap")
	public String nhacungcap(Model model) {
		List<NhaCungCap> listNhaCungCap = doiTuongRepo.findAll();
		model.addAttribute("listNhaCungCap", listNhaCungCap);
		return "muahang/nhacungcap";
	}
	@GetMapping("/form_ncc")
	public String formNhaCungCap(Model model) {
		NhaCungCap nhacungcap = new NhaCungCap();
		model.addAttribute("nhacungcap", nhacungcap);
		return "muahang/form_ncc";
	}
	@GetMapping("/search_nhacungcap")
	public String TimKiemNhaCungCap(Model model, @RequestParam String txt_ncc) {
		List<NhaCungCap> listNhaCungCap = doiTuongRepo.findAllByMaDoiTuong(txt_ncc);
		model.addAttribute("listNhaCungCap", listNhaCungCap);
		return "muahang/nhacungcap";
	}
	@PostMapping("/nhacungcap_add")
	public String themNhaCungCap(@ModelAttribute("nhacungcap") NhaCungCap nhacungcap) {
		doiTuongRepo.save(nhacungcap);
		return "redirect:/nhacungcap";
	}
	@GetMapping("/delete_nhacungcap")
	public String XoaNhaCungCap(@RequestParam String maDoiTuong) {
		List<NhaCungCap> listDoiTuong = doiTuongRepo.findAllByMaDoiTuong(maDoiTuong);
		NhaCungCap m = listDoiTuong.get(0);
		doiTuongRepo.delete(m);
		return "redirect:/nhacungcap";
	}
	@GetMapping("/edit_nhacungcap")
	public String SuaNhaCungCap(@RequestParam String maDoiTuong, Model model) {
		List<NhaCungCap> listDoiTuong = doiTuongRepo.findAllByMaDoiTuong(maDoiTuong);
		NhaCungCap m = listDoiTuong.get(0);
		model.addAttribute("nhacungcap", m);
		return "muahang/form_suancc";
	}
	@PostMapping("/nhacungcap_update")
	public String EditNhaCungCap(@ModelAttribute("nhacungcap") NhaCungCap nhacungcap) {
		doiTuongRepo.save(nhacungcap);
		return "redirect:/nhacungcap";
	}
}
