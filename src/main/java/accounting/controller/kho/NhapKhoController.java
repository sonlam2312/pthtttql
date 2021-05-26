package accounting.controller.kho;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import accounting.model.ChiTietPhieuMua;
import accounting.model.ChungTuMua;
import accounting.model.PhieuNhapKho;
import accounting.repository.ChungTuMuaRepo;
import accounting.repository.PhieuNhapKhoRepo;

@Controller
public class NhapKhoController {
	@Autowired
	private PhieuNhapKhoRepo phieuNhapKhoRepo;
	@Autowired
	private ChungTuMuaRepo chungTuMuaRepo;
	@GetMapping("/nhapkho")
	public String nhapkho(Model model) {
		List<PhieuNhapKho> listPhieuNhapKho = phieuNhapKhoRepo.findAll();
		model.addAttribute("listPhieuNhapKho", listPhieuNhapKho);
		return "kho/nhapkho";
	}
	@GetMapping("/form_nhapkho")
	public String form_nhapkho(Model model) {
		PhieuNhapKho phieunhap = new PhieuNhapKho();
		List<ChiTietPhieuMua> listChiTietPhieuMua = new ArrayList<ChiTietPhieuMua>();
		ChiTietPhieuMua c = new ChiTietPhieuMua();
		ChiTietPhieuMua c1 = new ChiTietPhieuMua();
		listChiTietPhieuMua.add(c1);
		listChiTietPhieuMua.add(c);
		phieunhap.setChiTietPhieuMua(listChiTietPhieuMua);
		model.addAttribute("phieunhap", phieunhap);
		return "kho/form_nhapkho";
	}
	@GetMapping("/search_nhapkho")
	public String TimKiemPhieuNhap(Model model, @RequestParam String txt_nhapkho) {
		List<PhieuNhapKho> listPhieuNhapKho = phieuNhapKhoRepo.TimKiemPhieuNhap(txt_nhapkho);
		model.addAttribute("listPhieuNhapKho", listPhieuNhapKho);
		return "kho/nhapkho";
	}
	@GetMapping("/get_chungtumua")
	public String LayChungTuMua(Model model, @RequestParam String sochungtu) {
		List<ChungTuMua> listChungTuMua = chungTuMuaRepo.getChungTuMuaBySoChungTu(sochungtu);
		if(listChungTuMua.isEmpty()) {
			return "redirect:/form_nhapkho";
		}
		ChungTuMua chungtumua = listChungTuMua.get(0);
		PhieuNhapKho phieunhap = new PhieuNhapKho();
		String maDoiTuong = chungtumua.getMaDoiTuong();
		String tenDoiTuong = chungtumua.getTenDoiTuong();
		List<ChiTietPhieuMua> listChiTietPhieuMua = chungtumua.getChiTietPhieuMua();
		phieunhap.setMaDoiTuong(maDoiTuong);
		phieunhap.setTenDoiTuong(tenDoiTuong);
		phieunhap.setChiTietPhieuMua(listChiTietPhieuMua);
		phieunhap.setTongHang(chungtumua.getTongHang());
		phieunhap.setTongChietKhau(chungtumua.getTongChietKhau());
		phieunhap.setTongGTGT(chungtumua.getTongGTGT());
		phieunhap.setTongTien(chungtumua.getTongTien());
		model.addAttribute("phieunhap", phieunhap);
		return "kho/form_nhapkho";
	}
}
