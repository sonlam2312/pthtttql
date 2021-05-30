package accounting.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Table
@Entity
@Data
public class BangLuong {
	@Id
	@GeneratedValue
	private int id;
	private String tenNhanVien;
	private String maNhanVien;
	private String phongBan;
	private double luong;
	private double thuong;
	private double phat;
	private double tamUng;
	private double hoanTamUng;
	private double soDaTra;
	private double soConPhaiTra;
	private String tinhTrang;
	private String taiKhoanNganHang;
	@ManyToOne
	@JoinColumn(name = "id_thangluong")
	private ThangLuong thangLuong;
}
