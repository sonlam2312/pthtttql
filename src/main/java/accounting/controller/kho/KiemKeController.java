package accounting.controller.kho;

import java.sql.Date;
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

import accounting.model.ChiTietPhieuKiemKe;
import accounting.model.HangHoa;
import accounting.model.Kho;
import accounting.model.KiemKe;
import accounting.repository.ChiTietKiemKeRepo;
import accounting.repository.HangHoaRepo;
import accounting.repository.KhoRepo;
import accounting.repository.KiemKeRepo;

@Controller
public class KiemKeController {
	@Autowired
	private ChiTietKiemKeRepo chiTietRepo;
	@Autowired
	private KiemKeRepo kiemKeRepo;
	@Autowired
	private KhoRepo khoRepo;
	@Autowired
	private HangHoaRepo hangHoaRepo;
	@GetMapping("/kiemke")
	public String kiemke(Model model) {
		List<KiemKe> listKiemKe = kiemKeRepo.findAll();
		model.addAttribute("listKiemKe", listKiemKe);
		return "kho/kiemke";
	}
	@GetMapping("/search_kiemke")
	public String TimKiem(@RequestParam String txt_kiemke, Model model) {
		List<KiemKe> listKiemKe = kiemKeRepo.findAllBySoChungTu(txt_kiemke);
		model.addAttribute("listKiemKe", listKiemKe);
		return "kho/kiemke";
	}
	@GetMapping("/form_ngaykiemke")
	public String NgayKiemKe(Model model) {
		return "kho/tao_kiemke";
	}
	@GetMapping("/form_kiemke")
	public String formKiemKe(Model model, @RequestParam String makho, @RequestParam Date ngay) {
		List<Kho> listkho = khoRepo.findAllByMaKho(makho);
		if(listkho.isEmpty()) {
			return "redirect:/form_ngaykiemke";
		}
		Kho kho = listkho.get(0);
		List<ChiTietPhieuKiemKe> listChiTietPhieuKiemKe = new ArrayList<ChiTietPhieuKiemKe>();
		List<HangHoa> listHangHoa = hangHoaRepo.findAllByKho(kho);
		int n =listHangHoa.size();
		for(int i=0 ; i<n ; i++) {
			HangHoa h = listHangHoa.get(i);
			ChiTietPhieuKiemKe c = new ChiTietPhieuKiemKe();
			c.setMaHang(h.getMaHang());
			c.setTenHang(h.getTenHang());
			c.setTenKho(kho.getTenKho());
			c.setDonViTinh(h.getDonViTinh());
			c.setSoLuongTheoSo(h.getSoLuong());
			listChiTietPhieuKiemKe.add(c);
		}
		KiemKe kiemke = new KiemKe();
		kiemke.setKho(kho.getTenKho());
		kiemke.setChiTietPhieuKiemKe(listChiTietPhieuKiemKe);
		model.addAttribute("kiemke", kiemke);
		return "kho/form_kiemke";
	}
	@PostMapping("/donkiemke_add")
	public String ThemKiemKe(@ModelAttribute("kiemke") KiemKe kiemke) {
		List<ChiTietPhieuKiemKe> listChiTietPhieuKiemKe = kiemke.getChiTietPhieuKiemKe();
		for(ChiTietPhieuKiemKe c:listChiTietPhieuKiemKe) {
			c.setPhieuKiemKe(kiemke);
		}
		kiemKeRepo.save(kiemke);
		return "redirect:/kiemke";
	}
	@GetMapping("/edit_kiemke")
	public String FormSuaKiemKe(@RequestParam int id_kiemke, Model model) {
		Optional<KiemKe> listKiemKe = kiemKeRepo.findById(id_kiemke);
		KiemKe k = listKiemKe.get();
		List<ChiTietPhieuKiemKe> listChiTietPhieuKiemKe = k.getChiTietPhieuKiemKe();
		k.setChiTietPhieuKiemKe(listChiTietPhieuKiemKe);
		model.addAttribute("kiemke", k);
		return "kho/sua_kiemke";
	}
	@GetMapping("/donkiemke_update")
	public String SuaKiemKe(@ModelAttribute("kiemke") KiemKe kiemke) {
		List<ChiTietPhieuKiemKe> lChiTietKiemKe = kiemke.getChiTietPhieuKiemKe();
		for(ChiTietPhieuKiemKe c:lChiTietKiemKe) {
			c.setPhieuKiemKe(kiemke);
		}
		kiemKeRepo.save(kiemke);
		return "redirect:/kiemke";
	}
	@GetMapping("/delete_kiemke")
	public String XoaKiemKe(@RequestParam int id_kiemke) {
		Optional<KiemKe> listKiemKe = kiemKeRepo.findById(id_kiemke);
		List<ChiTietPhieuKiemKe> listChiTietPhieuKiemKe = listKiemKe.get().getChiTietPhieuKiemKe();
		for(ChiTietPhieuKiemKe c:listChiTietPhieuKiemKe) {
			chiTietRepo.delete(c);
		}
		kiemKeRepo.delete(listKiemKe.get());
		return "redirect:/kiemke";
	}
}
