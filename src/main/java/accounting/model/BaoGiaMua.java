package accounting.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class BaoGiaMua {
	@Id
	@GeneratedValue
	private int id;
	private Date ngayBaoGia;
	private Date ngayHieuLuc;
	private String soBaoGia;
	private String maDoiTuong;
	private String tenDoiTuong;
	private String maSoThue;
	private String diaChi;
	private String nguoiPhuTrach;
	private double tongHang;
	private double tongChietKhau;
	private double tongGTGT;
	private double tongTien;
	private String tinhTrang;
	@OneToMany(mappedBy = "baoGiaMua",fetch = FetchType.EAGER)
	private List<ChiTietPhieuMua> chiTietPhieuMua;
}
