package accounting.controller.kho;

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
import accounting.model.HangHoa;
import accounting.model.PhieuXuatKho;
import accounting.repository.ChungTuBanRepo;
import accounting.repository.HangHoaRepo;
import accounting.repository.PhieuBanRepo;
import accounting.repository.PhieuXuatKhoRepo;

@Controller
public class XuatKhoController {
	@Autowired
	private PhieuXuatKhoRepo phieuXuatRepo;
	@Autowired
	private ChungTuBanRepo chungTuBanRepo;
	@Autowired
	private PhieuBanRepo phieuBanRepo;
	@Autowired
	private HangHoaRepo hangHoaRepo;
	@GetMapping("/xuatkho")
	public String xuatkho(Model model) {
		List<PhieuXuatKho> listPhieuXuatKho = phieuXuatRepo.findAll();
		model.addAttribute("listPhieuXuat", listPhieuXuatKho);
		return "kho/xuatkho";
	}
	@GetMapping("/search_phieuxuatkho")
	public String TimKiem(@RequestParam String txt_xuatkho, Model model) {
		List<PhieuXuatKho> listPhieuXuatKho = phieuXuatRepo.findAllBySoPhieuXuat(txt_xuatkho);
		model.addAttribute("listPhieuXuat", listPhieuXuatKho);
		return "kho/xuatkho";
	}
	@GetMapping("/form_xuatkho")
	public String form_xuatkho(Model model) {
		PhieuXuatKho phieuxuat = new PhieuXuatKho();
		List<ChiTietPhieuBan> listChiTietPhieuBan = new ArrayList<ChiTietPhieuBan>();
		listChiTietPhieuBan.add(new ChiTietPhieuBan());
		listChiTietPhieuBan.add(new ChiTietPhieuBan());
		phieuxuat.setChiTietPhieuBan(listChiTietPhieuBan);
		model.addAttribute("phieuxuat", phieuxuat);
		return "kho/form_xuatkho";
	}
	@GetMapping("/getChungTuBan")
	public String DownloadChungTu(Model model, @RequestParam String sochungtu) {
		List<ChungTuBan> listChungTuBan = chungTuBanRepo.findAllBySoChungTu(sochungtu);
		if(listChungTuBan.isEmpty()) {
			model.addAttribute("message", "Không tìm thấy chứng từ");
			return "kho/form_xuatkho";
		}
		ChungTuBan chungtuban = listChungTuBan.get(0);
		if(chungtuban.getPhieuXuatKho() != null) {
			model.addAttribute("message", "Chứng từ đã kèm phiếu xuất");
			return "kho/form_xuatkho";
		}
		PhieuXuatKho phieuxuat = new PhieuXuatKho();
		String maDoiTuong = chungtuban.getMaDoiTuong();
		String tenDoiTuong = chungtuban.getTenDoiTuong();
		String diaChi = chungtuban.getDiaChi();
		double tongChietKhau = chungtuban.getTongChietKhau();
		double tongGTGT = chungtuban.getTongGTGT();
		double tongHang = chungtuban.getTongHang();
		double tienShip = chungtuban.getTienShip();
		double tongTien = chungtuban.getTongTien();
		List<ChiTietPhieuBan> liChiTietPhieuBan = chungtuban.getChiTietPhieuBan();
		phieuxuat.setMaDoiTuong(maDoiTuong);
		phieuxuat.setTenDoiTuong(tenDoiTuong);
		phieuxuat.setDiaChi(diaChi);
		phieuxuat.setChungTuBan(chungtuban);
		phieuxuat.setChiTietPhieuBan(liChiTietPhieuBan);
		phieuxuat.setTongChietKhau(tongChietKhau);
		phieuxuat.setTongHang(tongHang);
		phieuxuat.setTongGTGT(tongGTGT);
		phieuxuat.setTienShip(tienShip);
		phieuxuat.setTongTien(tongTien);
		model.addAttribute("phieuxuat", phieuxuat);
		return "kho/form_xuatkho";
	}
	@PostMapping("/phieuxuat_add")
	public String ThemPhieuXuat(@ModelAttribute("phieuxuat") PhieuXuatKho phieuxuat) {
		int id_chungtuban = phieuxuat.getChungTuBan().getId();
		if(id_chungtuban != 0) {
			Optional<ChungTuBan> chungtuban = chungTuBanRepo.findById(id_chungtuban);
			phieuxuat.setChungTuBan(chungtuban.get());
			List<ChiTietPhieuBan> listChiTietPhieuBan = phieuxuat.getChiTietPhieuBan();
			for(ChiTietPhieuBan c:listChiTietPhieuBan) {
				c.setPhieuXuatKho(phieuxuat);
				String mahang = c.getMaHang();
				List<HangHoa> listHangHoa = hangHoaRepo.findAllByMaHang(mahang);
				HangHoa h = listHangHoa.get(0);
				int soluong = h.getSoLuong() - c.getSoLuong();
				h.setSoLuong(soluong);
				hangHoaRepo.save(h);
			}
		}
		phieuXuatRepo.save(phieuxuat);
		return "redirect:/xuatkho";
	}
	@GetMapping("/delete_phieuxuat")
	public String XoaPhieuXuat(@RequestParam int idPhieuXuat) {
		Optional<PhieuXuatKho> listphieuxuat = phieuXuatRepo.findById(idPhieuXuat);
		PhieuXuatKho phieuxuat = listphieuxuat.get();
		if(phieuxuat.getChungTuBan() == null) {
			phieuXuatRepo.delete(phieuxuat);
		}else {
			phieuxuat.setChungTuBan(null);
			List<ChiTietPhieuBan> listChiTietPhieuBan = phieuxuat.getChiTietPhieuBan();
			for(ChiTietPhieuBan c:listChiTietPhieuBan) {
				c.setPhieuXuatKho(null);
				phieuBanRepo.save(c);
			}
			phieuXuatRepo.delete(phieuxuat);
		}
		return "redirect:/xuatkho";
	}
	@GetMapping("/edit_phieuxuat")
	public String formSuaPhieuXuat(Model model, @RequestParam int idPhieuXuat) {
		Optional<PhieuXuatKho> listphieuxuat = phieuXuatRepo.findById(idPhieuXuat);
		PhieuXuatKho phieuxuat = listphieuxuat.get();
		if(phieuxuat.getChungTuBan() != null) {
			ChungTuBan chungtuban = phieuxuat.getChungTuBan();
			List<ChiTietPhieuBan> listChiTietPhieuBan = chungtuban.getChiTietPhieuBan();
			phieuxuat.setChiTietPhieuBan(listChiTietPhieuBan);
		}
		model.addAttribute("phieuxuat", phieuxuat);
		return "kho/form_suaphieuxuat";
	}
	@GetMapping("/phieuxuat_update")
	public String SuaPhieuXuatKho(@ModelAttribute("phieuxuat") PhieuXuatKho phieuxuat) {		
		phieuXuatRepo.save(phieuxuat);
		return "redirect:/xuatkho";
	}
}
