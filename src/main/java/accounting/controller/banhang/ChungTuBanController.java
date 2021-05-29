package accounting.controller.banhang;

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

import accounting.model.ChiTietPhieuBan;
import accounting.model.ChungTuBan;
import accounting.model.DonBanHang;
import accounting.model.HangHoa;
import accounting.model.HoaDonBan;
import accounting.model.PhieuThu;
import accounting.model.PhieuXuatKho;
import accounting.repository.ChungTuBanRepo;
import accounting.repository.DonBanHangRepo;
import accounting.repository.HangHoaRepo;

@Controller
public class ChungTuBanController {
	@Autowired
	private ChungTuBanRepo chungTuBanRepo;
	@Autowired
	private DonBanHangRepo donBanHangRepo;
	@Autowired
	private HangHoaRepo hangHoaRepo;
	
	@GetMapping("/chungtuban")
	public String chungTuBan(Model model) {
		List<ChungTuBan> listChungTuBan = chungTuBanRepo.findAll();
		model.addAttribute("listChungTuBan", listChungTuBan);
		return "banhang/chungtuban";
	}
	@GetMapping("/search_chungtuban")
	public String TimKiemChungTuBan(Model model, @RequestParam String txt_chungtuban) {
		List<ChungTuBan> listChungTuBan = chungTuBanRepo.findAllBySoChungTu(txt_chungtuban);
		model.addAttribute("listChungTuBan", listChungTuBan);
		return "banhang/chungtuban";
	}
	@GetMapping("/form_chungtuban")
	public String formChungTuBan(Model model) {
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		PhieuXuatKho phieuxuat = new PhieuXuatKho();
		phieuxuat.setNgayChungTu(date);
		phieuxuat.setNgayHachToan(date);
		List<PhieuThu> listPhieuThu = new ArrayList<PhieuThu>();
		PhieuThu phieuthu = new PhieuThu();
		phieuthu.setNgayChungTu(date);
		phieuthu.setNgayHachToan(date);
		listPhieuThu.add(phieuthu);
		HoaDonBan hoadonban = new HoaDonBan();
		hoadonban.setNgayHoaDon(date);
		List<ChiTietPhieuBan> listChiTietPhieuBan = new ArrayList<ChiTietPhieuBan>();
		listChiTietPhieuBan.add(new ChiTietPhieuBan());
		listChiTietPhieuBan.add(new ChiTietPhieuBan());
		ChungTuBan chungTuBan = new ChungTuBan();
		chungTuBan.setNgayChungTu(date);
		chungTuBan.setNgayHachToan(date);
		chungTuBan.setChiTietPhieuBan(listChiTietPhieuBan);
		chungTuBan.setPhieuThu(listPhieuThu);
		chungTuBan.setPhieuXuatKho(phieuxuat);
		chungTuBan.setHoaDonBan(hoadonban);
		model.addAttribute("chungtuban", chungTuBan);
		return "banhang/form_banhang";
	}
	@GetMapping("/getDonBanHang")
	public String LayDonBanHang(Model model, @RequestParam String sodonban) {
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		List<DonBanHang> listDonBanHang = donBanHangRepo.findAllBySoDonHang(sodonban);
		if(listDonBanHang.isEmpty()) {
			model.addAttribute("message1", "Không tìm thấy đơn bán hàng");
			return "banhang/form_banhang";
		}
		DonBanHang donbanhang = listDonBanHang.get(0);
//		if(donbanhang.getTinhTrang().equals("Đã lập chứng từ")) {
//			model.addAttribute("message", "Không tìm thấy đơn bán hàng");
//			return "banhang/form_banhang";
//		}
		String maDoiTuong = donbanhang.getMaDoiTuong();
		String tenDoiTuong = donbanhang.getTenDoiTuong();
		String diaChi = donbanhang.getDiaChi();
		String maSoThue = donbanhang.getMaSoThue();
		double tongHang = donbanhang.getTongHang();
		double tongGTGT = donbanhang.getTongGTGT();
		double tongChietKhau = donbanhang.getTongChietKhau();
		List<ChiTietPhieuBan> listChiTietPhieuBan = donbanhang.getChiTietPhieuBan();
		PhieuXuatKho phieuxuat = new PhieuXuatKho();
		phieuxuat.setMaDoiTuong(maDoiTuong);
		phieuxuat.setTenDoiTuong(tenDoiTuong);
		phieuxuat.setNgayChungTu(date);
		phieuxuat.setNgayHachToan(date);
		phieuxuat.setDiaChi(diaChi);
		phieuxuat.setTongHang(tongHang);
		phieuxuat.setTongChietKhau(tongChietKhau);
		phieuxuat.setTongGTGT(tongGTGT);
		List<PhieuThu> listPhieuThu = new ArrayList<PhieuThu>();
		PhieuThu phieuthu = new PhieuThu();
		phieuthu.setMaDoiTuong(maDoiTuong);
		phieuthu.setTenDoiTuong(tenDoiTuong);
		phieuthu.setDiaChi(diaChi);
		phieuthu.setNgayChungTu(date);
		phieuthu.setNgayHachToan(date);
		listPhieuThu.add(phieuthu);
		HoaDonBan hoadonban = new HoaDonBan();
		hoadonban.setMaDoiTuong(maDoiTuong);
		hoadonban.setTenDoiTuong(tenDoiTuong);
		hoadonban.setMaSoThue(maSoThue);
		hoadonban.setDiaChi(diaChi);
		hoadonban.setNgayHoaDon(date);
		ChungTuBan chungtuban = new ChungTuBan();
		chungtuban.setMaDoiTuong(maDoiTuong);
		chungtuban.setTenDoiTuong(tenDoiTuong);
		chungtuban.setDiaChi(diaChi);
		chungtuban.setNgayChungTu(date);
		chungtuban.setNgayHachToan(date);
		chungtuban.setPhieuThu(listPhieuThu);
		chungtuban.setHoaDonBan(hoadonban);
		chungtuban.setPhieuXuatKho(phieuxuat);
		chungtuban.setChiTietPhieuBan(listChiTietPhieuBan);
		chungtuban.setTongHang(tongHang);
		chungtuban.setTongGTGT(tongGTGT);
		chungtuban.setTongChietKhau(tongChietKhau);
		donbanhang.setTinhTrang("Đã lập chứng từ");
		donBanHangRepo.save(donbanhang);
		model.addAttribute("chungtuban", chungtuban);
		return "banhang/form_banhang";
	}
	@PostMapping("/chungtuban_add")
	public String ThemChungTuBan(@ModelAttribute("chungtuban") ChungTuBan chungtuban, Model model) {
		String thanhToan = chungtuban.getThanhToan();
		boolean kemphieuxuat = chungtuban.isKemphieuxuat();
		String kemhoadon = chungtuban.getNhanKemHoaDon();
		List<ChiTietPhieuBan> listChiTietPhieuBan = chungtuban.getChiTietPhieuBan();
		for(ChiTietPhieuBan c:listChiTietPhieuBan) {
			if(c.getMaHang() == null) break;
			int soluong = c.getSoLuong();
			List<HangHoa> listHangHoa = hangHoaRepo.findAllByMaHang(c.getMaHang());
			HangHoa h = listHangHoa.get(0);
			int soLuongCon = h.getSoLuong();
			if(soluong>soLuongCon) {
				model.addAttribute("chungtuban", chungtuban);
				model.addAttribute("message", "Mã hàng "+ h.getMaHang()+ " trong kho không còn đủ");
				return "banhang/form_banhang";
			}
		}
		
		if(thanhToan.equals("thanhtoanngay")) {
			List<PhieuThu> listPhieuThu = chungtuban.getPhieuThu();
			PhieuThu phieuthu = listPhieuThu.get(0);
			phieuthu.setChungTuBan(chungtuban);
		}else {
			chungtuban.setPhieuThu(null);
		}
		PhieuXuatKho phieuxuat = chungtuban.getPhieuXuatKho();
		if(kemphieuxuat) {
			phieuxuat.setChungTuBan(chungtuban);
			phieuxuat.setTongHang(chungtuban.getTongHang());
			phieuxuat.setTienShip(chungtuban.getTienShip());
			phieuxuat.setTongChietKhau(chungtuban.getTongChietKhau());
			phieuxuat.setTongGTGT(chungtuban.getTongGTGT());
			phieuxuat.setTongTien(chungtuban.getTongTien());
		}else {
			chungtuban.setPhieuXuatKho(null);
		}
		HoaDonBan hoadonban = chungtuban.getHoaDonBan();
		if(kemhoadon.equals("nhankem")) {
			hoadonban.setTongTien(chungtuban.getTongTien());
			hoadonban.setChungTuBan(chungtuban);
		}else {
			chungtuban.setHoaDonBan(null);
		}
		for(ChiTietPhieuBan c : listChiTietPhieuBan) {
			String mahang = c.getMaHang();
			List<HangHoa> listHangHoa = hangHoaRepo.findAllByMaHang(mahang);
			HangHoa h = listHangHoa.get(0);
			c.setHangHoa(h);
			c.setChungTuBan(chungtuban);
			if(kemphieuxuat) {
				c.setPhieuXuatKho(phieuxuat);
				int soluong = h.getSoLuong() - c.getSoLuong();
				h.setSoLuong(soluong);
				hangHoaRepo.save(h);
			}
		}
		chungTuBanRepo.save(chungtuban);
		return "redirect:/chungtuban";
	}
	@GetMapping("/delete_chungtuban")
	public String XoaChungTuBan(@RequestParam int id_chungtuban) {
		Optional<ChungTuBan> listChungTuBan = chungTuBanRepo.findById(id_chungtuban);
		chungTuBanRepo.delete(listChungTuBan.get());
		return "redirect:/chungtuban";
	}
}
