package accounting.controller.thue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import accounting.model.ChiTietPhieuBan;
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
		double hangGTGT0=0;
		double hangGTGT5=0;
		double hangGTGT10=0;
		double tongGTGT0 =0;
		double tongGTGT10 =0;
		double tongGTGT5 =0;
		double tongGTGTban=0;
		List<ChungTuBan> listChungTuBan = chungTuBanRepo.findAll();
		List<ChungTuBan> listChungTuBan1 = new ArrayList<ChungTuBan>();
		for(ChungTuBan c:listChungTuBan) {
			if(c.getNgayHachToan().toString().contains(ki)) {
				listChungTuBan1.add(c);
				tongGTGTban += c.getTongGTGT();
				List<ChiTietPhieuBan> listChiTietPhieuBan = c.getChiTietPhieuBan();
				for(ChiTietPhieuBan chitiet:listChiTietPhieuBan) {
					int soluong = chitiet.getSoLuong();
					double gia = chitiet.getGia();
					double GTGT = chitiet.getVAT();
					double thanhtien = chitiet.getThanhTien();
					if(GTGT == 0) {
						hangGTGT0 += thanhtien;
						tongGTGT0 = 0;
					}
					if(GTGT ==5 ) {
						hangGTGT5 += gia*soluong;
						tongGTGT5 += hangGTGT5 * 0.05;
					}
					if(GTGT == 10) {
						hangGTGT10 += gia*soluong;
						tongGTGT10 += hangGTGT10 *0.1;
					}
				}
			}
		}
		listChungTuBan.clear();
		model.addAttribute("listChungTuBan", listChungTuBan1);
		List<ChungTuMua> listChungTuMua = chungTuMuaRepo.findAll();
		List<ChungTuMua> listChungTuMua1 = new ArrayList<ChungTuMua>();
		double tongHangMua=0;
		double tongGTGTmua =0;
		for(ChungTuMua c:listChungTuMua) {
			if(c.getNgayHachToan().toString().contains(ki)) {
				listChungTuMua1.add(c);
				tongGTGTmua += c.getTongGTGT();
				tongHangMua += c.getTongTien() - c.getTongGTGT() - c.getTienShip();
			}
		}
		listChungTuMua.clear();
		model.addAttribute("listChungTuMua", listChungTuMua1);
		model.addAttribute("hangGTGT0", hangGTGT0);
		model.addAttribute("hangGTGT", hangGTGT5+hangGTGT10);
		model.addAttribute("tongGTGTban", tongGTGTban);
		model.addAttribute("hangGTGT5", hangGTGT5);
		model.addAttribute("hangGTGT10", hangGTGT10);
		model.addAttribute("tongGTGT", tongGTGT5+tongGTGT10);
		model.addAttribute("tongGTGT0", tongGTGT0);
		model.addAttribute("tongGTGT5", tongGTGT5);
		model.addAttribute("tongGTGT10", tongGTGT10);
		model.addAttribute("tongHangMua", tongHangMua);
		model.addAttribute("tongGTGTmua", tongGTGTmua);
		return "thue/tokhaithue";
	}
	@PostMapping("/tokhaithue_add")
	public String ThemToKhaiThue(@ModelAttribute("tokhaithue") ToKhaiThue tokhaithue) {
		tokhaithue.setTenToKhai("Thuế giá trị gia tăng kì"+tokhaithue.getKi());
		khaiThueRepo.save(tokhaithue);
		return "redirect:/khaibaothue";
	}
	@GetMapping("/xem_tokhai")
	public String Xem(@RequestParam int id_tokhai, Model model) {
		Optional<ToKhaiThue> listOptional = khaiThueRepo.findById(id_tokhai);
		model.addAttribute("tokhaithue", listOptional.get());
		return "redirect:/tokhaithue?ki="+listOptional.get().getKi();
	}
	@GetMapping("/delete_tokhai")
	public String Xoa(@RequestParam int id_tokhai) {
		Optional<ToKhaiThue> listOptional = khaiThueRepo.findById(id_tokhai);
		khaiThueRepo.delete(listOptional.get());
		return "redirect:/khaibaothue";
	}
}
