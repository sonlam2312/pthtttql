package accounting.controller.banhang;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CongNoThuController {
	@GetMapping("/congnothu")
	public String CongNoThu() {
		return "banhang/congnothu";
	}
	@GetMapping("/form_congnothu")
	public String FormCongNoThu() {
		return "banhang/form_congnothu";
	}
	@GetMapping(value = "/lichsugiaodichthu")
	public String LichSuGiaoDich(@RequestParam(name="action",required = false) String action) {
		String url = null;
		if(action.equals("Xem")) {
			url = "banhang/lichsugiaodich";
		}
		if(action.equals("Thu")) {
			url = "redirect:/form_phieuthu";
		}
		return url;
	}
}
