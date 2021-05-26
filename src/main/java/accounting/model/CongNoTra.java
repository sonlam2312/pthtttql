package accounting.model;

import lombok.Data;

@Data
public class CongNoTra {
	private ChungTuMua chungtumua;
	private double soDaTra;
	private double soConLai;
	private String tinhTrang;
}
