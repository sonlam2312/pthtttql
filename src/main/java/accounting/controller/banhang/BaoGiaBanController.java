package accounting.controller.banhang;

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

import accounting.model.BaoGiaBan;
import accounting.model.ChiTietPhieuBan;
import accounting.model.HangHoa;
import accounting.repository.BaoGiaBanRepo;
import accounting.repository.HangHoaRepo;
import accounting.repository.PhieuBanRepo;

@Controller
public class BaoGiaBanController {
	@Autowired
	private BaoGiaBanRepo baoGiaBanRepo;
	@Autowired
	private HangHoaRepo hangHoaRepo;
	@Autowired
	private PhieuBanRepo phieuBanRepo;
	@GetMapping("/baogiaban")
	public String baogiaban(Model model) {
		List<BaoGiaBan> listBaoGiaBan = baoGiaBanRepo.findAll();
		model.addAttribute("listBaoGiaBan", listBaoGiaBan);
		return "banhang/baogiaban";
	}
	@GetMapping("/search_baogiaban")
	public String timKiemBaoGia(Model model, @RequestParam String txt_baogia) {
		List<BaoGiaBan> listBaoGiaBan = baoGiaBanRepo.findBySoBaoGia(txt_baogia);
		model.addAttribute("listBaoGiaBan", listBaoGiaBan);
		return "banhang/baogiaban";
	}
	@GetMapping("/form_baogiaban")
	public String formBaoGiaBan(Model model){
		BaoGiaBan baogiaban = new BaoGiaBan();
		List<ChiTietPhieuBan> listChiTietPhieuBan = new ArrayList<ChiTietPhieuBan>();
		listChiTietPhieuBan.add(new ChiTietPhieuBan());
		listChiTietPhieuBan.add(new ChiTietPhieuBan());
		baogiaban.setChiTietPhieuBan(listChiTietPhieuBan);
		model.addAttribute("baogiaban", baogiaban);
		return "banhang/form_baogiaban";
	}
	@PostMapping("/baogiaban_add")
	public String ThemBaoGia(
			@ModelAttribute("baogiaban") BaoGiaBan baogiaban) {
		baogiaban.setTinhTrang("Chưa xử lý");
		List<ChiTietPhieuBan> listChiTietPhieuBan = baogiaban.getChiTietPhieuBan();
		baoGiaBanRepo.save(baogiaban);
		for(ChiTietPhieuBan c:listChiTietPhieuBan) {
			c.setBaoGiaBan(baogiaban);
			String maHang = c.getMaHang();
			List<HangHoa> listHangHoa = hangHoaRepo.findAllByMaHang(maHang);
			if(listHangHoa.isEmpty()) {
				return "redirect:/form_baogiaban";
			}else {
				HangHoa h = listHangHoa.get(0);
				c.setHangHoa(h);
			}
			phieuBanRepo.save(c);
		}
		return "redirect:/baogiaban";
	}
	@GetMapping("/delete_baogiaban")
	public String XoaBaoGia(@RequestParam int id_baogia) {
		Optional<BaoGiaBan> listBaoGiaBan = baoGiaBanRepo.findById(id_baogia);
		BaoGiaBan baogiaban = listBaoGiaBan.get();
		List<ChiTietPhieuBan> listChiTietPhieuBan = baogiaban.getChiTietPhieuBan();
		for(ChiTietPhieuBan c:listChiTietPhieuBan) {
			phieuBanRepo.delete(c);
		}	
		baoGiaBanRepo.delete(baogiaban);
		return "redirect:/baogiaban";
	}
	@GetMapping("/edit_baogiaban")
	public String FormSuaBaoGia(@RequestParam int id_baogia, Model model) {
		Optional<BaoGiaBan> listOptional = baoGiaBanRepo.findById(id_baogia);
		BaoGiaBan baogiaban = listOptional.get();
		model.addAttribute("baogiaban", baogiaban);
		return "banhang/form_suabaogiaban";
	}
	@GetMapping("/baogiaban_update")
	public String SuaBaoGia(
			@ModelAttribute("baogiaban") BaoGiaBan baogiaban) {
		List<ChiTietPhieuBan> listChiTietPhieuBan = baogiaban.getChiTietPhieuBan();
		baoGiaBanRepo.save(baogiaban);
		for(ChiTietPhieuBan c:listChiTietPhieuBan) {
			c.setBaoGiaBan(baogiaban);
			String maHang = c.getMaHang();
			List<HangHoa> listHangHoa = hangHoaRepo.findAllByMaHang(maHang);
			if(listHangHoa.isEmpty()) {
				return "redirect:/form_baogiaban";
			}else {
				HangHoa h = listHangHoa.get(0);
				c.setHangHoa(h);
			}
			phieuBanRepo.save(c);
		}
		return "redirect:/baogiaban";
	}
}
