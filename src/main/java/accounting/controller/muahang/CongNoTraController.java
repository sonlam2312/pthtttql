package accounting.controller.muahang;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CongNoTraController {
	@GetMapping("/congnotra")
	public String CongNoTra() {
		return "muahang/congnotra";
	}
	@GetMapping("/form_congnotra")
	public String FormCongNoTra() {
		return "muahang/form_congnotra";
	}
	@GetMapping(value = "/lichsugiaodichtra")
	public String LichSuGiaoDich(@RequestParam(name="action",required = false) String action) {
		String url = null;
		if(action.equals("Xem")) {
			url = "muahang/lichsugiaodich";
		}
		if(action.equals("Trả")) {
			url = "redirect:/form_phieuchi";
		}
		return url;
	}
}
