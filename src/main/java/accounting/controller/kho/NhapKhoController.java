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

import accounting.model.ChiTietPhieuMua;
import accounting.model.ChungTuMua;
import accounting.model.HangHoa;
import accounting.model.PhieuNhapKho;
import accounting.repository.ChungTuMuaRepo;
import accounting.repository.HangHoaRepo;
import accounting.repository.PhieuMuaRepo;
import accounting.repository.PhieuNhapKhoRepo;

@Controller
public class NhapKhoController {
	@Autowired
	private PhieuNhapKhoRepo phieuNhapKhoRepo;
	@Autowired
	private ChungTuMuaRepo chungTuMuaRepo;
	@Autowired
	private HangHoaRepo hangHoaRepo;
	@Autowired
	private PhieuMuaRepo phieuMuaRepo;
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
		List<PhieuNhapKho> listPhieuNhapKho = phieuNhapKhoRepo.findAllBySoPhieuNhap(txt_nhapkho);
		model.addAttribute("listPhieuNhapKho", listPhieuNhapKho);
		return "kho/nhapkho";
	}
	@GetMapping("/get_chungtumua")
	public String LayChungTuMua(Model model, @RequestParam String sochungtu) {
		List<ChungTuMua> listChungTuMua = chungTuMuaRepo.findAllBySoChungTu(sochungtu);
		if(listChungTuMua.isEmpty()) {
			model.addAttribute("message", "Không tìm thấy chứng từ");
			return "kho/form_nhapkho";
		}
		ChungTuMua chungtumua = listChungTuMua.get(0);
		if(chungtumua.getPhieuNhapKho() != null) {
			model.addAttribute("message", "Chứng từ đã kèm phiếu nhập");
			return "kho/form_nhapkho";
		}
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
		phieunhap.setChungTuMua(chungtumua);
		model.addAttribute("phieunhap", phieunhap);
		return "kho/form_nhapkho";
	}
	@PostMapping("/phieunhap_add")
	public String ThemPhieuNhapKho(@ModelAttribute("phieunhap") PhieuNhapKho phieunhap) {
		int id_chungtumua = phieunhap.getChungTuMua().getId();
		if(id_chungtumua != 0) {
			Optional<ChungTuMua> listOptional = chungTuMuaRepo.findById(id_chungtumua);
			phieunhap.setChungTuMua(listOptional.get());
			List<ChiTietPhieuMua> listChiTietPhieuMua = phieunhap.getChiTietPhieuMua();
			for(ChiTietPhieuMua c:listChiTietPhieuMua) {
				c.setPhieuNhapKho(phieunhap);
				String mahang = c.getMaHang();
				List<HangHoa> listHangHoa = hangHoaRepo.findAllByMaHang(mahang);
				HangHoa h = listHangHoa.get(0);
				int soluong = h.getSoLuong() + c.getSoLuong();
				h.setSoLuong(soluong);
				hangHoaRepo.save(h);
			}
		}
		phieuNhapKhoRepo.save(phieunhap);
		return "redirect:/nhapkho";
	}
	@GetMapping("/edit_phieunhap")
	public String FormSuaPhieuNhap(Model model, @RequestParam int id_phieunhap) {
		Optional<PhieuNhapKho> listphieunhap = phieuNhapKhoRepo.findById(id_phieunhap);
		PhieuNhapKho phieunhap = listphieunhap.get();
		if(phieunhap.getChungTuMua() != null) {
			ChungTuMua chungtumua = phieunhap.getChungTuMua();
			List<ChiTietPhieuMua> listChiTietPhieuMua = chungtumua.getChiTietPhieuMua();
			phieunhap.setChiTietPhieuMua(listChiTietPhieuMua);
		}
		model.addAttribute("phieunhap", phieunhap);
		return "kho/form_suaphieunhap";
	}
	@GetMapping("/phieunhap_update")
	public String SuaPhieuNhapKho(@ModelAttribute("phieunhap") PhieuNhapKho phieunhap) {		
		phieuNhapKhoRepo.save(phieunhap);
		return "redirect:/nhapkho";
	}
	@GetMapping("/delete_phieunhap")
	public String XoaPhieuNhap(@RequestParam int id_phieunhap) {
		Optional<PhieuNhapKho> listOptional = phieuNhapKhoRepo.findById(id_phieunhap);
		PhieuNhapKho phieunhap = listOptional.get();
		if(phieunhap.getChungTuMua() == null) {
			phieuNhapKhoRepo.delete(phieunhap);
		}else {
			phieunhap.setChungTuMua(null);
			List<ChiTietPhieuMua> listChiTietPhieuBan = phieunhap.getChiTietPhieuMua();
			for(ChiTietPhieuMua c:listChiTietPhieuBan) {
				c.setPhieuNhapKho(null);
				phieuMuaRepo.save(c);
			}
			phieuNhapKhoRepo.delete(phieunhap);
		}
		return "redirect:/xuatkho";
	}
}
