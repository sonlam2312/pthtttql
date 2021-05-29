package accounting.controller.banhang;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import accounting.model.ChungTuBan;
import accounting.model.CongNoThu;
import accounting.model.PhieuThu;
import accounting.repository.ChungTuBanRepo;
import accounting.repository.PhieuThuRepo;

@Controller
public class CongNoThuController {
	@Autowired
	private ChungTuBanRepo chungTuBanRepo;
	@Autowired
	private PhieuThuRepo phieuThuRepo;
	@GetMapping("/congnothu")
	public String XemCongNoThu(Model model) {
		List<accounting.model.CongNoThu> listCongNoThu = new ArrayList<accounting.model.CongNoThu>();
		List<ChungTuBan> listChungTuBan = chungTuBanRepo.findAll();
		for(ChungTuBan c:listChungTuBan) {
			double tongtien = c.getTongTien();
			double sodathu =0;
			List<PhieuThu> listPhieuThu = phieuThuRepo.findAllByChungTuBan(c);
			for(PhieuThu p:listPhieuThu) {
				sodathu += p.getSoTien();
			}
			double soconlai = tongtien - sodathu;
			String tinhtrang = null;
			if(soconlai == 0) tinhtrang = "Đã trả hết";
			else if(soconlai >0) tinhtrang = "Chưa thu hết";
			else tinhtrang = "Thu thừa";
			CongNoThu congnothu = new CongNoThu();
			congnothu.setChungtuban(c);
			congnothu.setSoDaTra(sodathu);
			congnothu.setSoConLai(soconlai);
			congnothu.setTinhTrang(tinhtrang);
			listCongNoThu.add(congnothu);
		}
		model.addAttribute("listCongNoThu", listCongNoThu);
		return "banhang/congnothu";
	}
	@GetMapping("/form_congnothu")
	public String FormCongNoThu() {
		return "banhang/form_congnothu";
	}
	@GetMapping("/search_congnothu")
	public String TimKiem(Model model, @RequestParam String txt_congnothu) {
		List<accounting.model.CongNoThu> listCongNoThu = new ArrayList<accounting.model.CongNoThu>();
		List<ChungTuBan> listChungTuBan = chungTuBanRepo.findAllBySoChungTu(txt_congnothu);
		if(listChungTuBan.isEmpty()) {
			model.addAttribute("listCongNoThu", null);
			return "banhang/congnothu";
		}
		ChungTuBan c = listChungTuBan.get(0);
			double tongtien = c.getTongTien();
			double sodathu =0;
			List<PhieuThu> listPhieuThu = phieuThuRepo.findAllByChungTuBan(c);
			for(PhieuThu p:listPhieuThu) {
				sodathu += p.getSoTien();
			}
			double soconlai = tongtien - sodathu;
			String tinhtrang = soconlai>0?"Chưa thu hết":"Đã thu hết";
			CongNoThu congnothu = new CongNoThu();
			congnothu.setChungtuban(c);
			congnothu.setSoDaTra(sodathu);
			congnothu.setSoConLai(soconlai);
			congnothu.setTinhTrang(tinhtrang);
			listCongNoThu.add(congnothu);
		model.addAttribute("listCongNoThu", listCongNoThu);
		return "banhang/congnothu";
	}
	@GetMapping("/xemgiaodichthu")
	public String XemLichSuGiaoDich(Model model, @RequestParam String sochungtu) {
		List<ChungTuBan> listChungTuBan = chungTuBanRepo.findAllBySoChungTu(sochungtu);
		if(listChungTuBan.isEmpty()) {
			return "redirect:/congnothu";
		}
		List<PhieuThu> listPhieuThu = phieuThuRepo.findAllByChungTuBan(listChungTuBan.get(0));
		model.addAttribute("listPhieuThu", listPhieuThu);
		return "banhang/lichsugiaodich";
	}
	@GetMapping("/thutienkhachhang")
	public String ThuTienKhachHang(Model model, @RequestParam String sochungtu) {
		List<ChungTuBan> listChungTuBan = chungTuBanRepo.findAllBySoChungTu(sochungtu);
		if(listChungTuBan.isEmpty()) {
			return "redirect:/congnothu";
		}
		ChungTuBan chungtuban = listChungTuBan.get(0);
		PhieuThu phieuthu = new PhieuThu();
		phieuthu.setMaDoiTuong(chungtuban.getMaDoiTuong());
		phieuthu.setTenDoiTuong(chungtuban.getTenDoiTuong());
		phieuthu.setDiaChi(chungtuban.getDiaChi());
		phieuthu.setLoaiThu("tienhang");
		phieuthu.setChungTuBan(chungtuban);
		model.addAttribute("phieuthu", phieuthu);
		return "quy/form_thutien";
	}
}
