package accounting.controller.tienluong;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import accounting.model.NhanVien;
import accounting.model.PhieuChi;
import accounting.model.PhieuThu;
import accounting.model.ThangLuong;
import accounting.repository.BangLuongRepo;
import accounting.repository.NhanVienRepo;
import accounting.repository.PhieuChiRepo;
import accounting.repository.PhieuThuRepo;
import accounting.repository.ThangLuongRepo;

@Controller
public class TraLuongController {
	@Autowired
	private PhieuChiRepo phieuChiRepo;
	@Autowired
	private PhieuThuRepo phieuThuRepo;
	@Autowired
	private ThangLuongRepo thangLuongRepo;
	@Autowired
	private NhanVienRepo nhanVienRepo;
	@Autowired
	private BangLuongRepo bangLuongRepo;
	@GetMapping("/chitamung")
	public String ChiTamUng(Model model) {
		List<PhieuChi> listPhieuChi = phieuChiRepo.findAllByLoaiChi("tamung");
		model.addAttribute("listPhieuChi", listPhieuChi);
		return "tienluong/chitien";
	}
	@GetMapping("/thutamung")
	public String ThuTamUng(Model model) {
		List<PhieuThu> listPhieuThu = phieuThuRepo.findAllByLoaiThu("tamung");
		model.addAttribute("listPhieuThu", listPhieuThu);
		return "tienluong/thutien";
	}
	@GetMapping("/hachtoan")
	public String HachToan() {
		return "tienluong/form_thangluong";
	}
	@GetMapping("/search_bangluong")
	public String TimKiem(Model model, @RequestParam String txt_bangluong) {
		List<accounting.model.BangLuong> listBangLuong = bangLuongRepo.findAllByMaNhanVien(txt_bangluong);
		model.addAttribute("listBangLuong", listBangLuong);
		return "tienluong/hachtoanluong";
	}
	@GetMapping("/thongtintraluong")
	public String BangLuong(Model model, @RequestParam String month) {
		model.addAttribute("thang", month);
		List<ThangLuong> listThangLuong = thangLuongRepo.findAllByMonth(month);
		if(listThangLuong.isEmpty()) {
			ThangLuong thangluong = new ThangLuong();
			thangluong.setMonth(month);
			List<accounting.model.BangLuong> listBangLuong = new ArrayList<accounting.model.BangLuong>();
			List<NhanVien> listNhanVien = nhanVienRepo.findAll();
			for(NhanVien nhanvien:listNhanVien) {
				accounting.model.BangLuong bangluong = new accounting.model.BangLuong();
				bangluong.setMaNhanVien(nhanvien.getMaNhanVien());
				bangluong.setTenNhanVien(nhanvien.getTenNhanVien());
				bangluong.setTaiKhoanNganHang(nhanvien.getTaiKhoanNganHang());
				double thuong =0;
				double phat =0;
				double luong = nhanvien.getLuong();
				bangluong.setLuong(luong);
				bangluong.setThuong(thuong);
				bangluong.setPhat(phat);
				double tamung =0;
				double hoantamung =0;
				List<PhieuThu> listPhieuThu = phieuThuRepo.findAllByMaDoiTuongAndLoaiThu(nhanvien.getMaNhanVien(), "tamung");
				if(listPhieuThu.isEmpty()) hoantamung =0;
				for(PhieuThu p:listPhieuThu) {
					if(p.getNgayHachToan().toString().contains(month))
						hoantamung += p.getSoTien();
				}
				List<PhieuChi> listPhieuChi = phieuChiRepo.findAllByMaDoiTuongAndLoaiChi(nhanvien.getMaNhanVien(), "tamung");
				if(listPhieuChi.isEmpty()) tamung =0;
				for(PhieuChi p:listPhieuChi) {
					if(p.getNgayHachToan().toString().contains(month))
						tamung += p.getSoTien();
				}
				bangluong.setHoanTamUng(hoantamung);
				bangluong.setTamUng(tamung);
				double sodatra =0;
				bangluong.setSoDaTra(sodatra);
				double soconphaitra = luong + thuong - phat + hoantamung - tamung - sodatra;
				bangluong.setSoConPhaiTra(soconphaitra);
				String tinhTrang = soconphaitra >0?"chưa trả hết":"đã trả";
				bangluong.setTinhTrang(tinhTrang);
				bangluong.setThangLuong(thangluong);
				listBangLuong.add(bangluong);
			}
			thangluong.setBangLuong(listBangLuong);
			thangLuongRepo.save(thangluong);
		}
		List<ThangLuong> listThangLuong1 = thangLuongRepo.findAllByMonth(month);
		List<accounting.model.BangLuong> listBangLuong = listThangLuong1.get(0).getBangLuong();
		model.addAttribute("listBangLuong", listBangLuong);
		return "tienluong/hachtoanluong";
	}
	@GetMapping("/traluong")
	public String TraLuong(Model model, @RequestParam int id_bangluong) {
		accounting.model.BangLuong bangluong = bangLuongRepo.findById(id_bangluong).get();
		PhieuChi phieuchi = new PhieuChi();
		phieuchi.setMaDoiTuong(bangluong.getMaNhanVien());
		phieuchi.setTenDoiTuong(bangluong.getTenNhanVien());
		phieuchi.setTaiKhoanNhan(bangluong.getTaiKhoanNganHang());
		phieuchi.setLoaiChi("traluong");
		model.addAttribute("phieuchi", phieuchi);
		return "quy/form_chitien";
	}
	@GetMapping("/xemlichsutra")
	public String Xemlichsugiaodich(Model model, @RequestParam int id_bangluong) {
		accounting.model.BangLuong bangluong = bangLuongRepo.findById(id_bangluong).get();
		List<PhieuThu> listPhieuThus = phieuThuRepo.findAllByMaDoiTuongAndLoaiThu(bangluong.getMaNhanVien(), "tamung");
		List<PhieuThu> listPhieuThu = new ArrayList<PhieuThu>();
		for(PhieuThu p:listPhieuThus) {
			if(p.getNgayHachToan().toString().contains(bangluong.getThangLuong().getMonth())) {
				listPhieuThu.add(p);
			}
		}
		listPhieuThus.clear();
		model.addAttribute("listPhieuThu", listPhieuThu);
		List<PhieuChi> listPhieuChi1 = phieuChiRepo.findAllByMaDoiTuongAndLoaiChi(bangluong.getMaNhanVien(), "tamung");
		List<PhieuChi> listPhieuChi2 = phieuChiRepo.findAllByMaDoiTuongAndLoaiChi(bangluong.getMaNhanVien(), "traluong");
		listPhieuChi1.addAll(listPhieuChi2);
		List<PhieuChi> listPhieuChi = new ArrayList<PhieuChi>();
		for(PhieuChi p:listPhieuChi1) {
			if(p.getNgayHachToan().toString().contains(bangluong.getThangLuong().getMonth())) {
				listPhieuChi.add(p);
			}
		}
		listPhieuChi1.clear();
		listPhieuChi2.clear();
		model.addAttribute("listPhieuChi", listPhieuChi);
		return "tienluong/lichsugiaodich";
	}
	@GetMapping("/edit_bangluong")
	public String FormSua(Model model, @RequestParam int id_bangluong) {
		accounting.model.BangLuong bangluong = bangLuongRepo.findById(id_bangluong).get();
		model.addAttribute("bangluong", bangluong);
		return "tienluong/suabangluong";
	}
	@GetMapping("/update_bangluong")
	public String DieuChinhThuongPhat(@ModelAttribute("bangluong") accounting.model.BangLuong bangluong) {
		accounting.model.BangLuong bangluong1 = bangLuongRepo.getOne(bangluong.getId());
		bangluong1.setThuong(bangluong.getThuong());
		bangluong1.setPhat(bangluong.getPhat());
		bangLuongRepo.save(bangluong1);
		return "redirect:/thongtintraluong?month="+bangluong1.getThangLuong().getMonth();
	}
}
