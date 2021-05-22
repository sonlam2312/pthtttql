package accounting.controller.muahang;

import java.util.ArrayList;
import java.util.List;

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
import accounting.repository.BaoGiaMuaRepo;
import accounting.repository.DonMuaHangRepo;
import accounting.repository.PhieuMuaRepo;

@Controller
public class DonMuaHangController {
	@Autowired
	private BaoGiaMuaRepo baoGiaMuaRepo;
	@Autowired
	private PhieuMuaRepo phieuMuaRepo;
	@Autowired
	private DonMuaHangRepo donMuaHangRepo;
	@GetMapping("/donmuahang")
	public String donmuahang(Model model) {
		List<DonMuaHang> listDonMuaHang = donMuaHangRepo.findAll();
		model.addAttribute("listdonmuahang", listDonMuaHang);
		return "muahang/donmuahang";
	}
	@GetMapping("/search_donmuahang")
	public String TimKiemBaoGia(Model model, @RequestParam String txt_donmua) {
		List<DonMuaHang> listDonMua = donMuaHangRepo.search_donmuahang(txt_donmua);
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
		int id_baogia = donmuahang.getBaoGiaMua().getId();
		BaoGiaMua baogiamua = baoGiaMuaRepo.getOne(id_baogia);
		List<ChiTietPhieuMua> listPhieuMua = new ArrayList<ChiTietPhieuMua>();
		if(id_baogia==0) {
			donmuahang.setBaoGiaMua(null);
			listPhieuMua = donmuahang.getChiTietPhieuMua();
		}else {
			baogiamua.setTinhTrang("Hoàn Thành");
			listPhieuMua = baogiamua.getChiTietPhieuMua();
			donmuahang.setBaoGiaMua(baogiamua);
		}
		for(ChiTietPhieuMua c:listPhieuMua) {
			c.setDonMuaHang(donmuahang);
			phieuMuaRepo.save(c);
		}
		donMuaHangRepo.save(donmuahang);
		return "redirect:/donmuahang";
	}
	@GetMapping("/getBaoGiaMua")
	public String LayBaoGiaMua(Model model, @RequestParam String sobaogia) {
		List<BaoGiaMua> listbaogiamua = baoGiaMuaRepo.getBaoGiaBySoBaoGia(sobaogia);
		List<ChiTietPhieuMua> listChiTietPhieuMuas = phieuMuaRepo.listChiTietPhieuMua(sobaogia);
		if(listbaogiamua.isEmpty()) {
			return "redirect:/form_donmua";
		}
		BaoGiaMua baogiamua = listbaogiamua.get(0);
		DonMuaHang donmua = new DonMuaHang();
		donmua.setChiTietPhieuMua(listChiTietPhieuMuas);
		donmua.setMaDoiTuong(baogiamua.getMaDoiTuong());
		donmua.setTenDoiTuong(baogiamua.getTenDoiTuong());
		donmua.setDiaChi(baogiamua.getDiaChi());
		donmua.setMaSoThue(baogiamua.getMaSoThue());
		donmua.setNguoiPhuTrach(baogiamua.getNguoiPhuTrach());
		donmua.setTongChietKhau(baogiamua.getTongChietKhau());
		donmua.setTongHang(baogiamua.getTongHang());
		donmua.setTongGTGT(baogiamua.getTongGTGT());
		donmua.setTongTien(baogiamua.getTongTien());
		donmua.setBaoGiaMua(baogiamua);
		model.addAttribute("donmua", donmua);
		model.addAttribute("sobaogia", baogiamua.getSoBaoGia());
		return "muahang/form_donmua";
	}
}
