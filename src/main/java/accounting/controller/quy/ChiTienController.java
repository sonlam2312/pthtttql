package accounting.controller.quy;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import accounting.model.ChungTuMua;
import accounting.model.PhieuChi;
import accounting.repository.ChungTuMuaRepo;
import accounting.repository.PhieuChiRepo;

@Controller
public class ChiTienController {
	@Autowired
	private PhieuChiRepo phieuChiRepo;
	@Autowired
	private ChungTuMuaRepo chungTuMuaRepo;
	@GetMapping("/chitien")
	public String chitien(Model model) {
		List<PhieuChi> listPhieuChi = phieuChiRepo.findAll();
		model.addAttribute("listPhieuChi", listPhieuChi);
		return "quy/chitien";
	}
	@GetMapping("/form_phieuchi")
	public String formChiTien(Model model) {
		PhieuChi phieuchi = new PhieuChi();
		phieuchi.setChungTuMua(null);
		model.addAttribute("phieuchi", phieuchi);
		return "quy/form_chitien";
	}
	@GetMapping("search_phieuchi")
	public String TimKiemPhieuChi(Model model, @RequestParam String txt_phieuchi) {
		List<PhieuChi> listPhieuChi = phieuChiRepo.findAllBySoPhieuChi(txt_phieuchi);
		model.addAttribute("listPhieuChi", listPhieuChi);
		return "quy/chitien";
	}
	@PostMapping("/phieuchi_add")
	public String ThemPhieuChi(@ModelAttribute("phieuchi") PhieuChi phieuchi) {
		int id_chungtumua = phieuchi.getChungTuMua().getId();
		if(id_chungtumua == 0) {
			phieuchi.setChungTuMua(null);
			phieuChiRepo.save(phieuchi);
		}else {
			Optional<ChungTuMua> listChungTuMua = chungTuMuaRepo.findById(id_chungtumua);
			ChungTuMua chungtumua = listChungTuMua.get();
			phieuchi.setChungTuMua(chungtumua);
			phieuChiRepo.save(phieuchi);
		}
		return "redirect:/chitien";
	}
}
