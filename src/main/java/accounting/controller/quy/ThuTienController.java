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

import accounting.model.ChungTuBan;
import accounting.model.PhieuThu;
import accounting.repository.ChungTuBanRepo;
import accounting.repository.PhieuThuRepo;

@Controller
public class ThuTienController {
	@Autowired
	private PhieuThuRepo phieuThuRepo;
	@Autowired
	private ChungTuBanRepo chungTuBanRepo;
	@GetMapping("/thutien")
	public String thutien(Model model) {
		List<PhieuThu> listPhieuThu = phieuThuRepo.findAll();
		model.addAttribute("listPhieuThu", listPhieuThu);
		return "quy/thutien";
	}
	@GetMapping("/search_phieuthu")
	public String TimKiemPhieuThu(Model model, @RequestParam String txt_phieuthu) {
		List<PhieuThu> listPhieuThu = phieuThuRepo.findAllBySoPhieuThu(txt_phieuthu);
		model.addAttribute("listPhieuThu", listPhieuThu);
		return "quy/thutien";
	}
	@GetMapping("/form_phieuthu")
	public String formThuTien(Model model) {
		PhieuThu phieuthu = new PhieuThu();
		phieuthu.setChungTuBan(null);
		model.addAttribute("phieuthu", phieuthu);
		return "quy/form_thutien";
	}
	@PostMapping("/phieuthu_add")
	public String ThemPhieuThu(@ModelAttribute("phieuthu") PhieuThu phieuthu) {
		int id_chungtuban = phieuthu.getChungTuBan().getId();
		if(id_chungtuban == 0) {
			phieuthu.setChungTuBan(null);
			phieuThuRepo.save(phieuthu);
		}else {
			Optional<ChungTuBan> listChungTuBan = chungTuBanRepo.findById(id_chungtuban);
			ChungTuBan chungtuban = listChungTuBan.get();
			phieuthu.setChungTuBan(chungtuban);
			phieuThuRepo.save(phieuthu);
		}
		return "redirect:/thutien";
	}
	@GetMapping("/edit_phieuthu")
	public String FormSuaPhieuThu(Model model, @RequestParam int id_phieuthu) {
		Optional<PhieuThu> lisOptional = phieuThuRepo.findById(id_phieuthu);
		PhieuThu phieuthu = lisOptional.get();
		model.addAttribute("phieuthu", phieuthu);
		return "quy/form_suathutien";
	}
	@GetMapping("/phieuthu_update")
	public String SuaPhieuThu(@ModelAttribute("phieuthu") PhieuThu phieuthu) {
		int id_chungtuban = phieuthu.getChungTuBan().getId();
		if(id_chungtuban == 0) {
			phieuthu.setChungTuBan(null);
			phieuThuRepo.save(phieuthu);
		}else {
			phieuThuRepo.save(phieuthu);
		}
		return "redirect:/thutien";
	}
	@GetMapping("/delete_phieuthu")
	public String XoaPhieuThu(@RequestParam int id_phieuthu) {
		Optional<PhieuThu> lisOptional = phieuThuRepo.findById(id_phieuthu);
		PhieuThu phieuthu = lisOptional.get();
		phieuthu.setChungTuBan(null);
		phieuThuRepo.delete(phieuthu);
		return "redirect:/thutien";
	}
}
