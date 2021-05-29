package accounting.controller.muahang;

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

import accounting.model.BaoGiaMua;
import accounting.model.ChiTietPhieuMua;
import accounting.model.HangHoa;
import accounting.repository.BaoGiaMuaRepo;
import accounting.repository.HangHoaRepo;
import accounting.repository.PhieuMuaRepo;

@Controller
public class BaoGiaController {
	@Autowired
	private BaoGiaMuaRepo baoGiaMuaRepo;
	@Autowired
	private PhieuMuaRepo phieuMuaRepo;
	@Autowired
	private HangHoaRepo hangHoaRepo;
	@GetMapping("/baogia")
	public String baogia(Model model) {
		List<BaoGiaMua> listBaoGiaMua = baoGiaMuaRepo.findAll();
		model.addAttribute("listBaoGiaMua", listBaoGiaMua);
		return "muahang/baogia";
	}
	@GetMapping("/form_baogia")
	public String formBaoGia(Model model) {
		BaoGiaMua baogiamua = new BaoGiaMua();
		List<ChiTietPhieuMua> listPhieuMua = new ArrayList<ChiTietPhieuMua>();
		for(int i=0; i<2; i++) {
			listPhieuMua.add(new ChiTietPhieuMua());
		}
		baogiamua.setChiTietPhieuMua(listPhieuMua);
		model.addAttribute("baogiamua", baogiamua);
		return "muahang/form_baogia";
	}
	@GetMapping("/search_baogiamua")
	public String TimKiemBaoGia(Model model, @RequestParam String txt_baogia) {
		List<BaoGiaMua> listBaoGiaMua = baoGiaMuaRepo.findAllBySoBaoGia(txt_baogia);
		model.addAttribute("listBaoGiaMua", listBaoGiaMua);
		return "muahang/baogia";
	}
	@PostMapping("/baogiamua_save")
	public String ThemBaoGiaMua(@ModelAttribute("baogiamua") BaoGiaMua baogiamua) {
		baogiamua.setTinhTrang("Chưa xử lý");
		List<ChiTietPhieuMua> listChiTietPhieuMua = baogiamua.getChiTietPhieuMua();
		baoGiaMuaRepo.save(baogiamua);
		for(ChiTietPhieuMua c:listChiTietPhieuMua) {
			c.setBaoGiaMua(baogiamua);
			String maHang = c.getMaHang();
			List<HangHoa> listHangHoa = hangHoaRepo.findAllByMaHang(maHang);
			if(listHangHoa.isEmpty()) {
				return "redirect:/form_baogia";
			}else {
				HangHoa h = listHangHoa.get(0);
				c.setHangHoa(h);
			}
			phieuMuaRepo.save(c);
		}
		return "redirect:/baogia";
	}
	@GetMapping("/delete_baogiamua")
	public String XoaBaoGiaMua(@RequestParam int id_baogia) {
		Optional<BaoGiaMua> listOptional = baoGiaMuaRepo.findById(id_baogia);
		baoGiaMuaRepo.delete(listOptional.get());
		return "redirect:/baogia";
	}
	@GetMapping("/edit_baogiamua")
	public String FormSuaBaoGia(Model model, @RequestParam int id_baogia) {
		Optional<BaoGiaMua> listOptional = baoGiaMuaRepo.findById(id_baogia);
		BaoGiaMua baogiamua = listOptional.get();
		model.addAttribute("baogiamua", baogiamua);
		return "muahang/form_suabaogia";
	}
	@GetMapping("/baogiamua_update")
	public String SuaBaoGiaMua(@ModelAttribute("baogiamua") BaoGiaMua baogiamua) {
		List<ChiTietPhieuMua> listChiTietPhieuMua = baogiamua.getChiTietPhieuMua();
		baoGiaMuaRepo.save(baogiamua);
		for(ChiTietPhieuMua c:listChiTietPhieuMua) {
			c.setBaoGiaMua(baogiamua);
			String maHang = c.getMaHang();
			List<HangHoa> listHangHoa = hangHoaRepo.findAllByMaHang(maHang);
			if(listHangHoa.isEmpty()) {
				return "redirect:/form_baogia";
			}else {
				HangHoa h = listHangHoa.get(0);
				c.setHangHoa(h);
			}
			phieuMuaRepo.save(c);
		}
		return "redirect:/baogia";
	}
}
