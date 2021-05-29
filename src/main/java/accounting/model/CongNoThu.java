package accounting.model;

import lombok.Data;

@Data
public class CongNoThu {
	private ChungTuBan chungtuban;
	private double soDaTra;
	private double soConLai;
	private String tinhTrang;
}
