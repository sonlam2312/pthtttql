package accounting.controller.muahang;

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

import accounting.model.ChiTietPhieuMua;
import accounting.model.ChungTuMua;
import accounting.model.DonMuaHang;
import accounting.model.HangHoa;
import accounting.model.HoaDonMua;
import accounting.model.PhieuChi;
import accounting.model.PhieuNhapKho;
import accounting.repository.ChungTuMuaRepo;
import accounting.repository.DonMuaHangRepo;
import accounting.repository.HangHoaRepo;

@Controller
public class MuaHangController {
	@Autowired
	private ChungTuMuaRepo chungTuMuaRepo;
	@Autowired
	private DonMuaHangRepo donMuaHangRepo;
	@Autowired
	private HangHoaRepo hangHoaRepo;
	@GetMapping("/chungtumuahang")
	public String chungtumuahang(Model model) {
		List<ChungTuMua> listChungTuMua = chungTuMuaRepo.findAll();
		model.addAttribute("listChungTuMua", listChungTuMua);
		return "muahang/chungtumua";
	}
	@GetMapping("search_chungtumua")
	public String TimChungTuMua(Model model, @RequestParam String txt_chungtumua) {
		List<ChungTuMua> list = chungTuMuaRepo.findAllBySoChungTu(txt_chungtumua);
		model.addAttribute("listChungTuMua", list);
		return "muahang/chungtumua";
	}
	@GetMapping("/form_chungtumua")
	public String form_chungtumua(Model model) {
		ChungTuMua chungtumua = new ChungTuMua();
		List<ChiTietPhieuMua> list = new ArrayList<ChiTietPhieuMua>();
		list.add(new ChiTietPhieuMua());
		list.add(new ChiTietPhieuMua());
		chungtumua.setChiTietPhieuMua(list);
		PhieuNhapKho phieunhap = new PhieuNhapKho();
		PhieuChi phieuchi = new PhieuChi();
		HoaDonMua hoadonmua = new HoaDonMua();
		chungtumua.setPhieuNhapKho(phieunhap);
		List<PhieuChi> listPhieuChi = new ArrayList<PhieuChi>();
		listPhieuChi.add(phieuchi);
		chungtumua.setPhieuChi(listPhieuChi);
		chungtumua.setHoaDonMua(hoadonmua);
		//
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		phieuchi.setNgayChungTu(date);
		phieuchi.setNgayHachToan(date);
		phieunhap.setNgayHachToan(date);
		chungtumua.setNgayChungTu(date);
		chungtumua.setNgayHachToan(date);
		phieunhap.setNgayChungTu(date);
		hoadonmua.setNgayHoaDon(date);
		//
		model.addAttribute("chungtumua", chungtumua);
		return "muahang/form_muahang";
	}
	@GetMapping("/getDonMuaHang")
	public String GetDonMuaHang(Model model, @RequestParam String sodonmua) {
		List<DonMuaHang> listDonMuaHangs = donMuaHangRepo.findAllBySoDonHang(sodonmua);
		if(listDonMuaHangs.isEmpty()) {
			model.addAttribute("message", "Không tìm thấy đơn mua hàng");
			return "muahang/form_muahang";
		}
		DonMuaHang donMuaHang = listDonMuaHangs.get(0);
		List<ChiTietPhieuMua> listChiTietPhieuMuas = donMuaHang.getChiTietPhieuMua();
		String maDoiTuong = donMuaHang.getMaDoiTuong();
		String tenDoiTuong = donMuaHang.getTenDoiTuong();
		String diaChi = donMuaHang.getDiaChi();
		String maSoThue = donMuaHang.getMaSoThue();
		donMuaHang.setTinhTrang("Đã lập chứng từ");
		donMuaHangRepo.save(donMuaHang);
		ChungTuMua chungtumua = new ChungTuMua();
		chungtumua.setChiTietPhieuMua(listChiTietPhieuMuas);
		chungtumua.setMaDoiTuong(maDoiTuong);
		chungtumua.setTenDoiTuong(tenDoiTuong);
		chungtumua.setDiaChi(diaChi);
		// phiếu nhập kho
		PhieuNhapKho phieunhap = new PhieuNhapKho();
		phieunhap.setMaDoiTuong(maDoiTuong);
		phieunhap.setTenDoiTuong(tenDoiTuong);
		chungtumua.setPhieuNhapKho(phieunhap);
		// phiếu chi
		PhieuChi phieuchi = new PhieuChi();
		phieuchi.setMaDoiTuong(maDoiTuong);
		phieuchi.setTenDoiTuong(tenDoiTuong);
		phieuchi.setDiaChi(diaChi);
		List<PhieuChi> listPhieuChi = new ArrayList<PhieuChi>();
		listPhieuChi.add(phieuchi);
		chungtumua.setPhieuChi(listPhieuChi);
		// hóa đơn mua hàng
		HoaDonMua hoadonmua = new HoaDonMua();
		hoadonmua.setMaDoiTuong(maDoiTuong);
		hoadonmua.setTenDoiTuong(tenDoiTuong);
		hoadonmua.setMaSoThue(maSoThue);
		hoadonmua.setDiaChi(diaChi);
		chungtumua.setHoaDonMua(hoadonmua);
		// date
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		phieuchi.setNgayChungTu(date);
		phieuchi.setNgayHachToan(date);
		phieunhap.setNgayHachToan(date);
		chungtumua.setNgayChungTu(date);
		chungtumua.setNgayHachToan(date);
		phieunhap.setNgayChungTu(date);
		hoadonmua.setNgayHoaDon(date);
		//
		model.addAttribute("chungtumua", chungtumua);
		model.addAttribute("donMuaHang", donMuaHang);
		return "muahang/form_muahang";
	}
	@PostMapping("/chungtumua_add")
	public String ThemChungTuMuaHang(@ModelAttribute("chungtumua") ChungTuMua chungtumua) {
		String thanhToan = chungtumua.getThanhToan();
		String loaimua = chungtumua.getLoaiMuaHang();
		String nhankem = chungtumua.getNhanKemHoaDon();
		chungtumua.setTinhTrang("Chưa xử lý");
		if(thanhToan.equals("chuathanhtoan")) {
			chungtumua.setPhieuChi(null);
		}
		if(thanhToan.equals("thanhtoanngay")) {
			List<PhieuChi> phieuChis = chungtumua.getPhieuChi();
			PhieuChi phieuchi = phieuChis.get(0);
			phieuchi.setChungTuMua(chungtumua);
		}
		if(nhankem.equals("khongkem")) {
			chungtumua.setHoaDonMua(null);
		}
		if(nhankem.equals("nhankem")) {
			HoaDonMua hoadonmua = chungtumua.getHoaDonMua();
//			hoadonmua.setChiTietPhieuMuas(chiTietPhieuMuas);
			hoadonmua.setTongTien(chungtumua.getTongTien());
			hoadonmua.setChungTuMua(chungtumua);
		}
		if(loaimua.equals("muahangkhongquakho")) {
			chungtumua.setPhieuNhapKho(null);
		}
		if(loaimua.equals("muahangnhapkho")) {
			PhieuNhapKho phieunhap = chungtumua.getPhieuNhapKho();
//			phieunhap.setChiTietPhieuMuas(chiTietPhieuMuas);
			phieunhap.setChungTuMua(chungtumua);
		}
		List<ChiTietPhieuMua> listChiTietPhieuMua = chungtumua.getChiTietPhieuMua();
		for(ChiTietPhieuMua c : listChiTietPhieuMua) {
			String mahang = c.getMaHang();
			List<HangHoa> listHangHoa = hangHoaRepo.findAllByMaHang(mahang);
			HangHoa h = listHangHoa.get(0);
			c.setHangHoa(h);
			c.setChungTuMua(chungtumua);
			if(loaimua.equals("muahangnhapkho")) {
				int soluong = h.getSoLuong() + c.getSoLuong();
				h.setSoLuong(soluong);
				hangHoaRepo.save(h);
			}
		}
		chungTuMuaRepo.save(chungtumua);
		return "redirect:/chungtumuahang";
	}
	@GetMapping("/delete_chungtumua")
	public String XoaChungTu(@RequestParam int id_chungtumua) {
		Optional<ChungTuMua> listOptional = chungTuMuaRepo.findById(id_chungtumua);
		ChungTuMua chungtumua = listOptional.get();
		chungTuMuaRepo.delete(chungtumua);
		return "redirect:/chungtumuahang";
	}
}
