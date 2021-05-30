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

import accounting.model.BangLuong;
import accounting.model.ChungTuMua;
import accounting.model.PhieuChi;
import accounting.repository.BangLuongRepo;
import accounting.repository.ChungTuMuaRepo;
import accounting.repository.PhieuChiRepo;

@Controller
public class ChiTienController {
	@Autowired
	private PhieuChiRepo phieuChiRepo;
	@Autowired
	private ChungTuMuaRepo chungTuMuaRepo;
	@Autowired
	private BangLuongRepo bangLuongRepo;
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
		if(phieuchi.getLoaiChi().equals("traluong")) {
			String manhanvien = phieuchi.getMaDoiTuong();
			List<BangLuong> listbangluong = bangLuongRepo.findAllByMaNhanVien(manhanvien);
			BangLuong bangluong = listbangluong.get(0);
			double sodatra = bangluong.getSoDaTra() + phieuchi.getSoTien();
			double soconlai = bangluong.getSoConPhaiTra() - phieuchi.getSoTien();
			String tinhTrang = soconlai>0?"chưa trả hết":"đã trả";
			bangluong.setSoDaTra(sodatra);
			bangluong.setSoConPhaiTra(soconlai);
			bangluong.setTinhTrang(tinhTrang);
			bangLuongRepo.save(bangluong);
			phieuChiRepo.save(phieuchi);
			return "redirect:/thongtintraluong?month="+bangluong.getThangLuong().getMonth();
		}
		return "redirect:/chitien";
	}
	@GetMapping("/edit_phieuchi")
	public String FormSuaPhieuChi(Model model, @RequestParam int id_phieuchi) {
		Optional<PhieuChi> lisOptional = phieuChiRepo.findById(id_phieuchi);
		PhieuChi phieuchi = lisOptional.get();
		model.addAttribute("phieuchi", phieuchi);
		return "quy/form_suachitien";
	}
	@GetMapping("/phieuchi_update")
	public String SuaPhieuChi(@ModelAttribute("phieuchi") PhieuChi phieuchi) {
		int id_chungtumua= phieuchi.getChungTuMua().getId();
		if(id_chungtumua == 0) {
			phieuchi.setChungTuMua(null);
			phieuChiRepo.save(phieuchi);
		}else {
			phieuChiRepo.save(phieuchi);
		}
		return "redirect:/chitien";
	}
	@GetMapping("/delete_phieuchi")
	public String XoaPhieuChi(@RequestParam int id_phieuchi) {
		Optional<PhieuChi> lisOptional = phieuChiRepo.findById(id_phieuchi);
		PhieuChi phieuchi = lisOptional.get();
		phieuchi.setChungTuMua(null);
		phieuChiRepo.delete(phieuchi);
		return "redirect:/chitien";
	}
}
