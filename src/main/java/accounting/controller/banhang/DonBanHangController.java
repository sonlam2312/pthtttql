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
import accounting.repository.DonBanHangRepo;
import accounting.repository.HangHoaRepo;
import accounting.repository.PhieuBanRepo;

@Controller
public class DonBanHangController {
	@Autowired
	private DonBanHangRepo donBanHangRepo;
	@Autowired
	private BaoGiaBanRepo baoGiaBanRepo;
	@Autowired
	private PhieuBanRepo phieuBanRepo;
	@Autowired
	private HangHoaRepo hangHoaRepo;
	@GetMapping("/donbanhang")
	public String DonBanHang(Model model) {
		List<accounting.model.DonBanHang> listDonBanHang = donBanHangRepo.findAll();
		model.addAttribute("listDonBanHang", listDonBanHang);
		return "banhang/donbanhang";
	}
	
	@GetMapping("/search_donbanhang")
	public String TimKiem(Model model, @RequestParam String txt_donbanhang) {
		List<accounting.model.DonBanHang> listDonBanHang = donBanHangRepo.findAllBySoDonHang(txt_donbanhang);
		model.addAttribute("listDonBanHang", listDonBanHang);
		return "banhang/donbanhang";
	}
	
	@GetMapping("/form_donban")
	public String formDonBan(Model model){
		accounting.model.DonBanHang donbanhang = new accounting.model.DonBanHang();
		List<ChiTietPhieuBan> listChiTietPhieuBan = new ArrayList<ChiTietPhieuBan>();
		listChiTietPhieuBan.add(new ChiTietPhieuBan());
		listChiTietPhieuBan.add(new ChiTietPhieuBan());
		donbanhang.setChiTietPhieuBan(listChiTietPhieuBan);
		model.addAttribute("donbanhang", donbanhang);
		return "banhang/form_donban";
	}
	@GetMapping("/getbaogiaban")
	public String LayBaoGiaBan(Model model, @RequestParam String sobaogia) {
		List<BaoGiaBan> listBaoGiaBan = baoGiaBanRepo.findBySoBaoGia(sobaogia);
		BaoGiaBan baogiaban = listBaoGiaBan.get(0);
		baogiaban.setTinhTrang("Hoàn Thành");
		String maDoiTuong = baogiaban.getMaDoiTuong();
		String tenDoiTuong = baogiaban.getTenDoiTuong();
		String diaChi = baogiaban.getDiaChi();
		String maSoThue =baogiaban.getMaSoThue();
		List<ChiTietPhieuBan> listChiTietPhieuBan = baogiaban.getChiTietPhieuBan();
		accounting.model.DonBanHang donbanhang = new accounting.model.DonBanHang();
		donbanhang.setMaDoiTuong(maDoiTuong);
		donbanhang.setTenDoiTuong(tenDoiTuong);
		donbanhang.setDiaChi(diaChi);
		donbanhang.setMaSoThue(maSoThue);
		donbanhang.setTongHang(baogiaban.getTongHang());
		donbanhang.setTongChietKhau(donbanhang.getTongChietKhau());
		donbanhang.setTongGTGT(baogiaban.getTongGTGT());
		donbanhang.setTongTien(baogiaban.getTongTien());
		donbanhang.setChiTietPhieuBan(listChiTietPhieuBan);
		model.addAttribute("donbanhang", donbanhang);
		return "banhang/form_donban";
	}
	@PostMapping("/donbanhang_add")
	public String ThemDonBanHang(@ModelAttribute("donbanhang") accounting.model.DonBanHang donbanhang) {
		donbanhang.setTinhTrang("Chưa xử lý");
		donBanHangRepo.save(donbanhang);
		List<ChiTietPhieuBan> listChiTietPhieuBan = donbanhang.getChiTietPhieuBan();
		for(ChiTietPhieuBan c:listChiTietPhieuBan) {
			int id_chitiet = c.getId();
			if(id_chitiet !=0 ) {
				Optional<ChiTietPhieuBan> chitiet = phieuBanRepo.findById(c.getId());
				ChiTietPhieuBan chitietphieuban = chitiet.get();
				chitietphieuban.setDonbanhang(donbanhang);
				BaoGiaBan baogiaban = chitietphieuban.getBaoGiaBan();
				baogiaban.setTinhTrang("Đã lập đơn");
				baoGiaBanRepo.save(baogiaban);
				phieuBanRepo.save(chitietphieuban);
			}else {
				int id_hang = Integer.parseInt(c.getMaHang().substring(2));
				Optional<HangHoa> h = hangHoaRepo.findById(id_hang);
				c.setHangHoa(h.get());
				c.setDonbanhang(donbanhang);
				phieuBanRepo.save(c);
			}
		}
		return "redirect:/donbanhang";
	}
	@GetMapping("/delete_donbanhang")
	public String XoaDonBanHang(@RequestParam String soDonHang) {
		List<accounting.model.DonBanHang> listdonbanhang = donBanHangRepo.findAllBySoDonHang(soDonHang);
		accounting.model.DonBanHang donbanhang = listdonbanhang.get(0);
		List<ChiTietPhieuBan> listChiTietPhieuBan = donbanhang.getChiTietPhieuBan();
		for(ChiTietPhieuBan c:listChiTietPhieuBan) {
			if(c.getBaoGiaBan() != null || c.getChungTuBan() != null || c.getPhieuXuatKho() != null) {
				c.setDonbanhang(null);
			}else {
				phieuBanRepo.delete(c);
			}
		}	
		donBanHangRepo.delete(donbanhang);
		return "redirect:/donbanhang";
	}
}
