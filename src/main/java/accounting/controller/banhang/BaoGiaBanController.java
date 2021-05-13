package accounting.controller.banhang;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaoGiaBanController {
	@GetMapping("/baogiaban")
	public String baogiaban() {
		return "banhang/baogiaban";
	}
	
	@GetMapping("/form_baogiaban")
	public String formBaoGiaBan(){
		return "banhang/form_baogiaban";
	}
}
