package accounting.controller.kho;

import java.util.List;

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
		List<HangHoa> listHangHoa = hangHoaRepo.fillAllByIDKho(id_kho);
		model.addAttribute("listHangHoa", listHangHoa);
		return "kho/hanghoa";
	}
	@GetMapping("/form_hanghoa")
	public String formHangHoa(@RequestParam("id_kho") int id_kho, Model model) {
		HangHoa h = new HangHoa();
		model.addAttribute("hanghoa", h);
		return "kho/form_hanghoa";
	}
	@PostMapping("/hanghoa_save")
	public String ThemHangHoa(@ModelAttribute("hanghoa") HangHoa hanghoa) {
		hangHoaRepo.save(hanghoa);
		return "redirect:/hanghoa?id_kho="+hanghoa.getKho().getId();
	}
}
