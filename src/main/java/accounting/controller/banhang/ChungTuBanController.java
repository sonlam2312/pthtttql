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
import accounting.repository.HoaDonBanRepo;
import accounting.repository.PhieuBanRepo;
import accounting.repository.PhieuThuRepo;
import accounting.repository.PhieuXuatKhoRepo;

@Controller
public class ChungTuBanController {
	@Autowired
	private ChungTuBanRepo chungTuBanRepo;
	@Autowired
	private DonBanHangRepo donBanHangRepo;
	@Autowired
	private PhieuThuRepo phieuThuRepo;
	@Autowired
	private PhieuBanRepo phieuBanRepo;
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
		DonBanHang donbanhang = listDonBanHang.get(0);
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
			int id_hangHoa = Integer.parseInt(c.getMaHang().substring(2));
			Optional<HangHoa> hangHoa = hangHoaRepo.findById(id_hangHoa);
			int soLuongCon = hangHoa.get().getSoLuong();
			if(soluong>soLuongCon) {
				model.addAttribute("chungtuban", chungtuban);
				model.addAttribute("message", "Mã hàng "+ hangHoa.get().getMaHang()+ " trong kho không còn đủ");
				return "banhang/form_banhang";
			}
		}
		PhieuXuatKho phieuxuat = chungtuban.getPhieuXuatKho();
		if(kemphieuxuat) {
			phieuxuat.setChungTuBan(chungtuban);
//			phieuXuatRepo.save(phieuxuat);
		}else {
			chungtuban.setPhieuXuatKho(null);
		}
		if(kemhoadon.equals("nhankem")) {
			HoaDonBan hoadonban = chungtuban.getHoaDonBan();
			hoadonban.setTongTien(chungtuban.getTongTien());
			hoadonban.setChungTuBan(chungtuban);
//			hoaDonBanRepo.save(hoadonban);
		}
		if(kemhoadon.equals("khongkem")) {
			chungtuban.setHoaDonBan(null);
		}
		chungTuBanRepo.save(chungtuban);
		for(ChiTietPhieuBan c:listChiTietPhieuBan) {
			int id = c.getId();
			if(kemphieuxuat) {
				c.setPhieuXuatKho(phieuxuat);
				if(id!=0) {
					Optional<ChiTietPhieuBan> list = phieuBanRepo.findById(id);
					ChiTietPhieuBan phieuban = list.get();
					phieuban.setChungTuBan(chungtuban);
					phieuban.setPhieuXuatKho(phieuxuat);
//					phieuBanRepo.save(phieuban);
				}else {
					c.setChungTuBan(chungtuban);
					c.setPhieuXuatKho(phieuxuat);
//					phieuBanRepo.save(c);
				}
			}else {
				c.setPhieuXuatKho(null);
				if(id!=0) {
					Optional<ChiTietPhieuBan> list = phieuBanRepo.findById(id);
					ChiTietPhieuBan phieuban = list.get();
					phieuban.setChungTuBan(chungtuban);
//					phieuBanRepo.save(phieuban);
				}else {
					c.setChungTuBan(chungtuban);
//					phieuBanRepo.save(c);
				}
			}
		}
		if(thanhToan.equals("thanhtoanngay")) {
			List<PhieuThu> listPhieuThu = chungtuban.getPhieuThu();
			PhieuThu phieuthu = listPhieuThu.get(0);
			phieuthu.setChungTuBan(chungtuban);
			phieuThuRepo.save(phieuthu);
		}
		if(thanhToan.equals("chuathanhtoan")) {
			chungtuban.setPhieuThu(null);
		}
		return "redirect:/chungtuban";
	}
}
