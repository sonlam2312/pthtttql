package accounting.controller.baocao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import accounting.model.ChungTuBan;
import accounting.model.ChungTuMua;
import accounting.model.CongNoThu;
import accounting.model.PhieuChi;
import accounting.model.PhieuThu;
import accounting.repository.ChungTuBanRepo;
import accounting.repository.ChungTuMuaRepo;
import accounting.repository.PhieuChiRepo;
import accounting.repository.PhieuThuRepo;

@Controller
public class BaoCaoCongNoController {
	@Autowired
	private ChungTuBanRepo chungTuBanRepo;
	@Autowired
	private ChungTuMuaRepo chungTuMuaRepo;
	@Autowired
	private PhieuThuRepo phieuThuRepo;
	@Autowired
	private PhieuChiRepo phieuChiRepo;
	@GetMapping("/baocaocongno")
	public String FormCongNo() {
		return "baocao/form_congno";
	}
	@GetMapping("/ketquabaocaocongno")
	public String BaoCaoCongNo(Model model, @RequestParam String month) {
		List<accounting.model.CongNoThu> listCongNoThu = new ArrayList<accounting.model.CongNoThu>();
		List<ChungTuBan> listChungTuBan = chungTuBanRepo.findAll();
		double tongcongnothu =0;
		double tongcongnotra =0;
		for(ChungTuBan c:listChungTuBan) {
			if(c.getNgayHachToan().toString().contains(month)) {
				double tongtien = c.getTongTien();
				double sodathu =0;
				List<PhieuThu> listPhieuThu = phieuThuRepo.findAllByChungTuBan(c);
				for(PhieuThu p:listPhieuThu) {
					sodathu += p.getSoTien();
				}
				double soconlai = tongtien - sodathu;
				tongcongnothu += soconlai;
				String tinhtrang = null;
				CongNoThu congnothu = new CongNoThu();
				congnothu.setChungtuban(c);
				congnothu.setSoConLai(soconlai);
				congnothu.setTinhTrang(tinhtrang);
				listCongNoThu.add(congnothu);
			}
		}
		model.addAttribute("listCongNoThu", listCongNoThu);
		List<accounting.model.CongNoTra> listCongNo = new ArrayList<>();
		List<ChungTuMua> listChungTuMua = chungTuMuaRepo.findAll();
		for(ChungTuMua c:listChungTuMua) {
			if(c.getNgayHachToan().toString().contains(month)) {
				List<PhieuChi> listPhieuChi = phieuChiRepo.findAllByChungTuMua(c);
				double sum =0;
				for(PhieuChi chi:listPhieuChi) {
					sum += chi.getSoTien();
				}
				double soconlai = c.getTongTien() - sum;
				tongcongnotra += soconlai;
				accounting.model.CongNoTra congno = new accounting.model.CongNoTra();
				congno.setChungtumua(c);
				congno.setSoConLai(soconlai);
				listCongNo.add(congno);
			}
		}
		model.addAttribute("listCongNoTra", listCongNo);
		model.addAttribute("tongcongnotra", tongcongnotra);
		model.addAttribute("tongcongnothu", tongcongnothu);
		model.addAttribute("month", month);
		return "baocao/baocaocongno";
	}
}
