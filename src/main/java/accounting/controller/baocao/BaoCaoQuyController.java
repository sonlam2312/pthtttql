package accounting.controller.baocao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import accounting.model.PhieuChi;
import accounting.model.PhieuThu;
import accounting.repository.PhieuChiRepo;
import accounting.repository.PhieuThuRepo;

@Controller
public class BaoCaoQuyController {
	@Autowired
	private PhieuThuRepo phieuThuRepo;
	@Autowired
	private PhieuChiRepo phieuChiRepo;
	
	@GetMapping("/baocaoquy")
	public String FormQuy() {
		return "baocao/form_quy";
	}
	@GetMapping("/ketquabaocaoquy")
	public String BaoCaoQuy(@RequestParam String month, Model model) {
		accounting.model.BaoCaoQuy baocaoquy = new accounting.model.BaoCaoQuy();
		Date ngayBaoCao = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(ngayBaoCao);
		baocaoquy.setNgayBaoCao(strDate);
		baocaoquy.setMonth(month);
		List<PhieuThu> listPhieuThu = new ArrayList<PhieuThu>();
		List<PhieuChi> listPhieuChi = new ArrayList<PhieuChi>();
		List<PhieuThu> listPhieuThus = phieuThuRepo.findAll();
		double tongthu=0;
		double tongchi=0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for(PhieuThu p:listPhieuThus) {
			String thang_nam = sdf.format(p.getNgayHachToan()).substring(0,7);
			if(thang_nam.equals(month)) {
				tongthu += p.getSoTien();
				listPhieuThu.add(p);
			}
		}
		listPhieuThus.clear();
		List<PhieuChi> listPhieuChis = phieuChiRepo.findAll();
		for(PhieuChi p:listPhieuChis) {
			String thang_nam = sdf.format(p.getNgayHachToan()).substring(0,7);
			if(thang_nam.equals(month)) {
				tongchi += p.getSoTien();
				listPhieuChi.add(p);
			}
		}
		listPhieuChis.clear();
		baocaoquy.setTongthu(tongthu);
		baocaoquy.setTongchi(tongchi);
		baocaoquy.setTonquy(tongthu - tongchi);
		baocaoquy.setListPhieuChi(listPhieuChi);
		baocaoquy.setListPhieuThu(listPhieuThu);
		model.addAttribute("baocaoquy", baocaoquy);
		return "baocao/baocaoquy";
	}
}
