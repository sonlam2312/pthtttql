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

import accounting.model.ChiTietPhieuMua;
import accounting.model.ChungTuMua;
import accounting.model.DonMuaHang;
import accounting.model.HoaDonMua;
import accounting.model.PhieuChi;
import accounting.model.PhieuNhapKho;
import accounting.repository.ChungTuMuaRepo;
import accounting.repository.DonMuaHangRepo;
import accounting.repository.HoaDonMuaRepo;
import accounting.repository.PhieuChiRepo;
import accounting.repository.PhieuMuaRepo;
import accounting.repository.PhieuNhapKhoRepo;

@Controller
public class MuaHangController {
	@Autowired
	private ChungTuMuaRepo chungTuMuaRepo;
	@Autowired
	private DonMuaHangRepo donMuaHangRepo;
	@Autowired
	private PhieuMuaRepo phieuMuaRepo;
	@Autowired
	private PhieuNhapKhoRepo phieuNhapRepo;
	@Autowired
	private PhieuChiRepo phieuChiRepo;
	@Autowired
	private HoaDonMuaRepo hoaDonMuaRepo;
	@GetMapping("/chungtumuahang")
	public String chungtumuahang(Model model) {
		List<ChungTuMua> listChungTuMua = chungTuMuaRepo.findAll();
		model.addAttribute("listChungTuMua", listChungTuMua);
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
		model.addAttribute("chungtumua", chungtumua);
		return "muahang/form_muahang";
	}
	@GetMapping("/getDonMuaHang")
	public String GetDonMuaHang(Model model, @RequestParam String sodonmua) {
		List<DonMuaHang> listDonMuaHangs = donMuaHangRepo.getDonMuaTheoSoDon(sodonmua);
		List<ChiTietPhieuMua> listChiTietPhieuMuas = phieuMuaRepo.listChiTietPhieuMuaByDonHang(sodonmua);
		if(listDonMuaHangs.isEmpty()) {
			return "muahang/form_muahang";
		}
		DonMuaHang donMuaHang = listDonMuaHangs.get(0);
		String maDoiTuong = donMuaHang.getMaDoiTuong();
		String tenDoiTuong = donMuaHang.getTenDoiTuong();
		String diaChi = donMuaHang.getDiaChi();
		String maSoThue = donMuaHang.getMaSoThue();
//		double tongHang = donMuaHang.getTongHang();
//		double tongGTGT = donMuaHang.getTongGTGT();
//		double tongChietKhau = donMuaHang.getTongChietKhau();
		ChungTuMua chungtumua = new ChungTuMua();
		chungtumua.setDonMuaHang(donMuaHang);
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
		model.addAttribute("chungtumua", chungtumua);
		model.addAttribute("donMuaHang", donMuaHang);
		return "muahang/form_muahang";
	}
	@PostMapping("/chungtumua_add")
	public String ThemChungTuMuaHang(@ModelAttribute("chungtumua") ChungTuMua chungtumua) {
		int id_donmuahang = chungtumua.getDonMuaHang().getId();
		String thanhToan = chungtumua.getThanhToan();
		String loaimua = chungtumua.getLoaiMuaHang();
		String nhankem = chungtumua.getNhanKemHoaDon();
		DonMuaHang donmuahang = donMuaHangRepo.getOne(id_donmuahang);
		List<ChiTietPhieuMua> chiTietPhieuMuas = chungtumua.getChiTietPhieuMua();
		if(donmuahang.getId() == 0) {
			chungtumua.setDonMuaHang(null);
		}else {
			donmuahang.setTinhTrang("Hoàn Thành");
			donMuaHangRepo.save(donmuahang);
		}
		if(thanhToan.equals("chuathanhtoan")) {
			chungtumua.setPhieuChi(null);
		}
		if(thanhToan.equals("thanhtoanngay")) {
			List<PhieuChi> phieuChis = chungtumua.getPhieuChi();
			PhieuChi phieuchi = phieuChis.get(0);
			phieuchi.setChungTuMua(chungtumua);
			phieuChiRepo.save(phieuchi);
		}
		if(nhankem.equals("nhankem")) {
			chungtumua.setHoaDonMua(null);
		}
		if(nhankem.equals("khongkem")) {
			HoaDonMua hoadonmua = chungtumua.getHoaDonMua();
			hoadonmua.setChiTietPhieuMuas(chiTietPhieuMuas);
			hoadonmua.setChungTuMua(chungtumua);
			hoaDonMuaRepo.save(hoadonmua);
		}
		if(loaimua.equals("muahangnhapkho")) {
			chungtumua.setPhieuNhapKho(null);
		}
		if(loaimua.equals("muahangkhongquakho")) {
			PhieuNhapKho phieunhap = chungtumua.getPhieuNhapKho();
			phieunhap.setChiTietPhieuMuas(chiTietPhieuMuas);
			phieunhap.setChungTuMua(chungtumua);
			phieuNhapRepo.save(phieunhap);
		}
		for(ChiTietPhieuMua c:chiTietPhieuMuas) {
			c.setChungTuMua(chungtumua);
			phieuMuaRepo.save(c);
		}
		chungtumua.setTinhTrang("Chưa xử lý");
		chungTuMuaRepo.save(chungtumua);
		return "redirect:/chungtumuahang";
	}
}
