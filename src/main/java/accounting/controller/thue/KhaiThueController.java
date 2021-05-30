package accounting.controller.thue;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import accounting.model.ChungTuBan;
import accounting.model.ChungTuMua;
import accounting.model.ToKhaiThue;
import accounting.repository.ChungTuBanRepo;
import accounting.repository.ChungTuMuaRepo;
import accounting.repository.ToKhaiThueRepo;

@Controller
public class KhaiThueController {
	@Autowired
	private ChungTuBanRepo chungTuBanRepo;
	@Autowired
	private ChungTuMuaRepo chungTuMuaRepo;
	@Autowired
	private ToKhaiThueRepo khaiThueRepo;
	@GetMapping("/khaibaothue")
	public String KhaiBaoThue(Model model) {
		List<ToKhaiThue> listToKhai = khaiThueRepo.findAll();
		model.addAttribute("listKhaiThue", listToKhai);
		return "thue/khaibaothue";
	}
	@GetMapping("/search_thue")
	public String SearchThue(Model model, @RequestParam String txt_thue) {
		List<ToKhaiThue> listToKhai = khaiThueRepo.findAllByMauSo(txt_thue);
		model.addAttribute("listKhaiThue", listToKhai);
		return "redirect:/khaibaothue";
	}
	@GetMapping("/form_thue")
	public String formThue() {
		return "thue/form_thue";
	}
	@GetMapping("/tokhaithue")
	public String toKhaiThue(Model model, @RequestParam String ki) {
		ToKhaiThue tokhai = new ToKhaiThue();
		tokhai.setKi(ki);
		model.addAttribute("tokhaithue", tokhai);
		double tongGTGT =0;
		double tongGTGT10 =0;
		double tongGTGT5 =0;
		List<ChungTuBan> listChungTuBan = chungTuBanRepo.findAll();
		List<ChungTuBan> listChungTuBan1 = new ArrayList<ChungTuBan>();
		for(ChungTuBan c:listChungTuBan) {
			if(c.getNgayHachToan().toString().contains(ki)) {
				listChungTuBan1.add(c);
				tongGTGT += c.getTongGTGT();
			}
		}
		listChungTuBan.clear();
		model.addAttribute("listChungTuBan", listChungTuBan1);
		List<ChungTuMua> listChungTuMua = chungTuMuaRepo.findAll();
		List<ChungTuMua> listChungTuMua1 = new ArrayList<ChungTuMua>();
		for(ChungTuMua c:listChungTuMua) {
			if(c.getNgayHachToan().toString().contains(ki)) listChungTuMua1.add(c);
		}
		listChungTuMua.clear();
		model.addAttribute("listChungTuMua", listChungTuMua1);
		return "thue/tokhaithue";
	}
}
