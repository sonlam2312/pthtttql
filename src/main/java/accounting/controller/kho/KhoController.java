package accounting.controller.kho;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import accounting.model.HangHoa;
import accounting.model.Kho;
import accounting.repository.HangHoaRepo;
import accounting.repository.KhoRepo;

@Controller
public class KhoController {
	@Autowired
	private KhoRepo khoRepo;
	@Autowired
	private HangHoaRepo hangHoaRepo;
	@GetMapping("/kho")
	public String kho(Model model) {
		List<Kho> listKho = khoRepo.findAll();
		model.addAttribute("listKho", listKho);
		return "kho/kho";
	}
	@GetMapping("/hanghoa")
	public String hanghoa(@RequestParam("id_kho") int id_kho, Model model) {
		Optional<Kho> kho = khoRepo.findById(id_kho);
		List<HangHoa> listHangHoa = hangHoaRepo.findAllByKho(kho.get());
		model.addAttribute("listHangHoa", listHangHoa);
		return "kho/hanghoa";
	}
	@GetMapping("/form_hanghoa")
	public String formHangHoa(@RequestParam("id_kho") int id_kho, Model model) {
		HangHoa h = new HangHoa();
		Kho k = new Kho();
		h.setKho(k);
		model.addAttribute("hanghoa", h);
		return "kho/form_hanghoa";
	}
	@PostMapping("/hanghoa_save")
	public String ThemHangHoa(@ModelAttribute("hanghoa") HangHoa hanghoa) {
		String makho = hanghoa.getKho().getMaKho();
		List<Kho> kho = khoRepo.findAllByMaKho(makho);
		Kho k = kho.get(0);
		hanghoa.setKho(k);
		hangHoaRepo.save(hanghoa);
		return "redirect:/hanghoa?id_kho="+hanghoa.getKho().getId();
	}
	@GetMapping("/form_themkho")
	public String formThemKho(Model model) {
		Kho kho = new Kho();
		model.addAttribute("kho", kho);
		return "kho/form_themkho";
	}
	@PostMapping("/themkho")
	public String ThemKho(@ModelAttribute("kho") Kho kho) {
		khoRepo.save(kho);
		return "redirect:/kho";
	}
	@GetMapping("/delete_kho")
	public String XoaKho(@RequestParam int id_kho) {
		Optional<Kho> kho = khoRepo.findById(id_kho);
		Kho k = kho.get();
		List<HangHoa> listHangHoa = hangHoaRepo.findAllByKho(k);
		for(HangHoa h:listHangHoa) {
			hangHoaRepo.delete(h);
		}
		khoRepo.delete(k);
		return "redirect:/kho";
	}
	@GetMapping("/edit_kho")
	public String FormSuaKho(Model model, @RequestParam int id_kho) {
		Optional<Kho> k = khoRepo.findById(id_kho);
		model.addAttribute("kho", k.get());
		return "kho/form_suakho";
	}
	@PostMapping("/kho_update")
	public String SuaKho(@ModelAttribute("kho") Kho k) {
		khoRepo.save(k);
		return "redirect:/kho";
	}
	@GetMapping("/edit_hanghoa")
	public String FormSuaHangHoa(Model model, @RequestParam int id_hanghoa) {
		Optional<HangHoa> h = hangHoaRepo.findById(id_hanghoa);
		model.addAttribute("hanghoa", h.get());
		return "kho/form_suahang";
	}
	@PostMapping("/hanghoa_update")
	public String SuaHangHoa(@ModelAttribute("hanghoa") HangHoa hanghoa) {
		String maKho = hanghoa.getKho().getMaKho();
		List<Kho> kho = khoRepo.findAllByMaKho(maKho);
		Kho k = kho.get(0);
		hanghoa.setKho(k);
		hangHoaRepo.save(hanghoa);
		return "redirect:/hanghoa?id_kho="+hanghoa.getKho().getId();
	}
	@GetMapping("/delete_hanghoa")
	public String XoaHangHoa(@RequestParam int id_hanghoa) {
		Optional<HangHoa> hangHoa = hangHoaRepo.findById(id_hanghoa);
		HangHoa h = hangHoa.get();
		int id_kho = h.getKho().getId();
		hangHoaRepo.delete(h);
		return "redirect:/hanghoa?id_kho="+id_kho;
	}
	@GetMapping("/search_hanghoa")
	public String TimKiem(Model model, @RequestParam String txt_hanghoa) {
		List<HangHoa> listHangHoa = hangHoaRepo.findAllByMaHang(txt_hanghoa);
		model.addAttribute("listHangHoa", listHangHoa);
		return "kho/hanghoa";
	}
}
