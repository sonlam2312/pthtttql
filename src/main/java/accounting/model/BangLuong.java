package accounting.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
	@OneToOne
	@JoinColumn(name = "id_nhanvien")
	private NhanVien nhanVien;
}
