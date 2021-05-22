package accounting.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class BaoGiaBan {
	@Id
	@GeneratedValue
	private int id;
	private Date ngayBaoGia;
	private Date ngayHieuLuc;
	private String soBaoGia;
	private String maDoiTuong;
	private String tenDoiTuong;
	private String nguoiPhuTrach;
	private double tongHang;
	private double tongChietKhau;
	private double tongGTGT;
	private double tongTien;
	private String tinhTrang;
	@OneToOne
	private DonBanHang donBanHang;
	@OneToMany(mappedBy = "baoGiaBan")
	private List<ChiTietPhieuBan> chiTietPhieuBan;
}
