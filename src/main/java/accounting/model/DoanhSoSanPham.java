package accounting.model;

import lombok.Data;

@Data
public class DoanhSoSanPham {
	private int id;
	private String maDoiTuong;
	private String tenDoiTuong;
	private String diaChi;
	private double soTien;
}
