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
import accounting.model.DoanhSoSanPham;
import accounting.repository.ChungTuBanRepo;
import accounting.repository.ChungTuMuaRepo;

@Controller
public class BaoCaoDSController {
	@Autowired
	private ChungTuBanRepo chungTuBanRepo;
	@Autowired
	private ChungTuMuaRepo chungTuMuaRepo;
	@GetMapping("/baocaodoanhso")
	public String FormDoanhSo() {
		return "baocao/form_doanhso";
	}
	@GetMapping("/ketquabaocaodoanhso")
	public String BaoCaoDoanhSo(@RequestParam String month, Model model) {
		List<ChungTuMua> listChungTuMua = chungTuMuaRepo.findAll();
		List<DoanhSoSanPham> doanhsosanpham = new ArrayList<DoanhSoSanPham>();
		boolean flag1 = false;
		for(ChungTuMua c:listChungTuMua) {
			if(c.getNgayHachToan().toString().contains(month)) {
				if(!doanhsosanpham.isEmpty()) {
					int n = doanhsosanpham.size();
					for(int i=0; i<n; i++) {
						DoanhSoSanPham ds = doanhsosanpham.get(i);
						if(ds.getMaDoiTuong().equals(c.getMaDoiTuong())) {
							double sotien = ds.getSoTien();
							double sotienmois = sotien += c.getTongTien();
							ds.setSoTien(sotienmois);
							flag1 = true;
							doanhsosanpham.set(i, ds);
						}else {
							flag1= false;
						}
					}
				}
				if(!flag1) {
					DoanhSoSanPham ds = new DoanhSoSanPham();
					ds.setMaDoiTuong(c.getMaDoiTuong());
					ds.setTenDoiTuong(c.getTenDoiTuong());
					ds.setDiaChi(c.getDiaChi());
					ds.setSoTien(c.getTongTien());
					doanhsosanpham.add(ds);
				}
			}
		}
		model.addAttribute("doanhsomua", doanhsosanpham);
		List<ChungTuBan> listChungTuBan = chungTuBanRepo.findAll();
		List<DoanhSoSanPham> doanhsosanpham1 = new ArrayList<DoanhSoSanPham>();
		boolean flag2 = false;
		for(ChungTuBan c:listChungTuBan) {
			if(c.getNgayHachToan().toString().contains(month)) {
				if(!doanhsosanpham1.isEmpty()) {
					int n =doanhsosanpham1.size();
					for(int i=0 ; i<n; i++) {
						DoanhSoSanPham ds = doanhsosanpham1.get(i);
						if(ds.getMaDoiTuong().equals(c.getMaDoiTuong())) {
							double sotien = ds.getSoTien();
							double sotienmois = sotien += c.getTongTien();
							ds.setSoTien(sotienmois);
							flag2 = true;
							doanhsosanpham1.set(i, ds);
						}else {
							flag2 = false;
						}
					}
				}
				if(!flag2) {
					DoanhSoSanPham ds = new DoanhSoSanPham();
					ds.setMaDoiTuong(c.getMaDoiTuong());
					ds.setTenDoiTuong(c.getTenDoiTuong());
					ds.setDiaChi(c.getDiaChi());
					ds.setSoTien(c.getTongTien());
					doanhsosanpham1.add(ds);
				}
			}
		}
		model.addAttribute("doanhsoban", doanhsosanpham1);
		model.addAttribute("month", month);
		double tongmua=0, tongban=0;
		for(DoanhSoSanPham ds:doanhsosanpham) {
			tongmua += ds.getSoTien();
		}
		for(DoanhSoSanPham ds:doanhsosanpham1) {
			tongban += ds.getSoTien();
		}
		model.addAttribute("tongmua", tongmua);
		model.addAttribute("tongban", tongban);
		return "baocao/baocaodoanhso";
	}
}
