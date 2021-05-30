package accounting.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class NhanVien {
	@Id
	@GeneratedValue
	private int id;
	private String maNhanVien;
	private String tenNhanVien;
	private String diaChi;
	private String SDT;
	private String chucVu;
	private String phongBan;
	private double luong;
	private String taiKhoanNganHang;
	@OneToOne
	private Account account;
}
