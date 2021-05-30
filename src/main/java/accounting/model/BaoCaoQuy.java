package accounting.model;

import java.util.List;

import lombok.Data;

@Data
public class BaoCaoQuy {
	private List<PhieuChi> listPhieuChi;
	private List<PhieuThu> listPhieuThu;
	private double tongthu;
	private double tongchi;
	private double tonquy;
	private String month;
	private String ngayBaoCao;
}
