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
import accounting.model.DonMuaHang;
import accounting.model.HangHoa;
import accounting.repository.BaoGiaMuaRepo;
import accounting.repository.DonMuaHangRepo;
import accounting.repository.HangHoaRepo;
import accounting.repository.PhieuMuaRepo;

@Controller
public class DonMuaHangController {
	@Autowired
	private BaoGiaMuaRepo baoGiaMuaRepo;
	@Autowired
	private PhieuMuaRepo phieuMuaRepo;
	@Autowired
	private DonMuaHangRepo donMuaHangRepo;
	@Autowired
	private HangHoaRepo hangHoaRepo;
	@GetMapping("/donmuahang")
	public String donmuahang(Model model) {
		List<DonMuaHang> listDonMuaHang = donMuaHangRepo.findAll();
		model.addAttribute("listdonmuahang", listDonMuaHang);
		return "muahang/donmuahang";
	}
	@GetMapping("/search_donmuahang")
	public String TimKiemBaoGia(Model model, @RequestParam String txt_donmua) {
		List<DonMuaHang> listDonMua = donMuaHangRepo.findAllBySoDonHang(txt_donmua);
		model.addAttribute("listdonmuahang", listDonMua);
		return "muahang/donmuahang";
	}
	@GetMapping("/form_donmua")
	public String formDonMua(Model model) {
		DonMuaHang donmua = new DonMuaHang();
		List<ChiTietPhieuMua> list = new ArrayList<ChiTietPhieuMua>();
		list.add(new ChiTietPhieuMua());
		list.add(new ChiTietPhieuMua());
		donmua.setChiTietPhieuMua(list);
		model.addAttribute("donmua", donmua);
		return "muahang/form_donmua";
	}
	@PostMapping("/donMuaHang_add")
	public String ThemDonMuaHang(@ModelAttribute("donmua") DonMuaHang donmuahang) {
		donmuahang.setTinhTrang("Chưa xử lý");
		donMuaHangRepo.save(donmuahang);
		List<ChiTietPhieuMua> listChiTietPhieuMua = donmuahang.getChiTietPhieuMua();
		for(ChiTietPhieuMua c:listChiTietPhieuMua) {
			String mahang = c.getMaHang();
			List<HangHoa> listHangHoa = hangHoaRepo.findAllByMaHang(mahang);
			HangHoa h = listHangHoa.get(0);
			c.setHangHoa(h);
			c.setDonMuaHang(donmuahang);
			phieuMuaRepo.save(c);
		}
		return "redirect:/donmuahang";
	}
	@GetMapping("/getBaoGiaMua")
	public String LayBaoGiaMua(Model model, @RequestParam String sobaogia) {
		List<BaoGiaMua> listbaogiamua = baoGiaMuaRepo.findAllBySoBaoGia(sobaogia);
		if(listbaogiamua.isEmpty()) {
			model.addAttribute("message", "Không tìm thấy báo giá");
			return "redirect:/form_donmua";
		}
		BaoGiaMua baogiamua = listbaogiamua.get(0);
		baogiamua.setTinhTrang("Đã lập đơn");
		baoGiaMuaRepo.save(baogiamua);
		List<ChiTietPhieuMua> listChiTietPhieuMua = baogiamua.getChiTietPhieuMua();
		DonMuaHang donmua = new DonMuaHang();
		donmua.setChiTietPhieuMua(listChiTietPhieuMua);
		donmua.setMaDoiTuong(baogiamua.getMaDoiTuong());
		donmua.setTenDoiTuong(baogiamua.getTenDoiTuong());
		donmua.setDiaChi(baogiamua.getDiaChi());
		donmua.setMaSoThue(baogiamua.getMaSoThue());
		donmua.setNguoiPhuTrach(baogiamua.getNguoiPhuTrach());
		donmua.setTongChietKhau(baogiamua.getTongChietKhau());
		donmua.setTongHang(baogiamua.getTongHang());
		donmua.setTongGTGT(baogiamua.getTongGTGT());
		donmua.setTongTien(baogiamua.getTongTien());
		model.addAttribute("donmua", donmua);
		return "muahang/form_donmua";
	}
	@GetMapping("/edit_donmuahang")
	public String FormSuaDonMuaHang(Model model, @RequestParam int id_donmuahang) {
		Optional<DonMuaHang> listOptional = donMuaHangRepo.findById(id_donmuahang);
		DonMuaHang donmua = listOptional.get();
		model.addAttribute("donmua", donmua);
		return "muahang/form_suadonmuahang";
	}
	@GetMapping("/donMuaHang_update")
	public String SuaDonMuaHang(@ModelAttribute("donmua") DonMuaHang donmuahang) {
		donMuaHangRepo.save(donmuahang);
		List<ChiTietPhieuMua> listChiTietPhieuMua = donmuahang.getChiTietPhieuMua();
		for(ChiTietPhieuMua c:listChiTietPhieuMua) {
			String mahang = c.getMaHang();
			List<HangHoa> listHangHoa = hangHoaRepo.findAllByMaHang(mahang);
			HangHoa h = listHangHoa.get(0);
			c.setHangHoa(h);
			c.setDonMuaHang(donmuahang);
			phieuMuaRepo.save(c);
		}
		return "redirect:/donmuahang";
	}
	@GetMapping("/delete_donmuahang")
	public String XoaDonMuaHang(@RequestParam int id_donmuahang) {
		Optional<DonMuaHang> listOptional = donMuaHangRepo.findById(id_donmuahang);
		DonMuaHang donmua = listOptional.get();
		donMuaHangRepo.delete(donmua);
		return "redirect:/donmuahang";
	}
}
