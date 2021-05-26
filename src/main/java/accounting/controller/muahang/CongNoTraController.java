package accounting.controller.muahang;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import accounting.model.ChungTuMua;
import accounting.model.PhieuChi;
import accounting.repository.ChungTuMuaRepo;
import accounting.repository.PhieuChiRepo;

@Controller
public class CongNoTraController {
	@Autowired
	private PhieuChiRepo phieuChiRepo;
	@Autowired
	private ChungTuMuaRepo chungTuMuaRepo;
	@GetMapping("/congnotra")
	public String CongNoTra(Model model) {
		List<accounting.model.CongNoTra> listCongNo = new ArrayList<>();
		List<ChungTuMua> listChungTuMua = chungTuMuaRepo.findAll();
		for(ChungTuMua c:listChungTuMua) {
			String soChungTu = c.getSoChungTu();
			List<PhieuChi> listPhieuChi = phieuChiRepo.getPhieuChiBySoChungTuMua(soChungTu);
			double sum =0;
			for(PhieuChi chi:listPhieuChi) {
				sum += chi.getSoTien();
			}
			double soconlai = c.getTongTien() - sum;
			String tinhTrang = soconlai >0 ? "Chưa trả hết":"Đã trả hết";
			accounting.model.CongNoTra congno = new accounting.model.CongNoTra();
			congno.setChungtumua(c);
			congno.setSoDaTra(sum);
			congno.setSoConLai(soconlai);
			congno.setTinhTrang(tinhTrang);
			listCongNo.add(congno);
		}
		model.addAttribute("listCongNo", listCongNo);
		return "muahang/congnotra";
	}
	@GetMapping("/form_congnotra")
	public String FormCongNoTra() {
		return "muahang/form_congnotra";
	}
	@GetMapping("/xemgiaodich")
	public String XemGiaoDichTra(@RequestParam("sochungtu") String soChungTu, Model model) {
		List<PhieuChi> listPhieuChi = phieuChiRepo.getPhieuChiBySoChungTuMua(soChungTu);
		model.addAttribute("listPhieuChi",listPhieuChi);
		return "muahang/lichsugiaodich";
	}
	@GetMapping("/tratiennhacungcap")
	public String TraTienNhaCungCap(@RequestParam("sochungtu") String soChungTu, Model model) {
		PhieuChi phieuchi = new PhieuChi();
		List<ChungTuMua> listChungTuMua = chungTuMuaRepo.getChungTuMuaBySoChungTu(soChungTu);
		ChungTuMua chungtumua = listChungTuMua.get(0);
		String maDoiTuong = chungtumua.getMaDoiTuong();
		String tenDoiTuong = chungtumua.getTenDoiTuong();
		String diaChi = chungtumua.getDiaChi();
		phieuchi.setMaDoiTuong(maDoiTuong);
		phieuchi.setTenDoiTuong(tenDoiTuong);
		phieuchi.setDiaChi(diaChi);
		phieuchi.setLoaiChi("tranhacungcap");
		phieuchi.setChungTuMua(chungtumua);
		model.addAttribute("phieuchi", phieuchi);
		return "quy/form_chitien";
	}
}
