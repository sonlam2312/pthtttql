package accounting.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class DonBanHang {
	@Id
	@GeneratedValue
	private int id;
	private Date ngayDonHang;
	private Date ngayGiaoHang;
	private String soDonHang;
	private String maDoiTuong;
	private String tenDoiTuong;
	private String diaChi;
	private String maSoThue;
	private String nguoiPhuTrach;
	private double tongHang;
	private double tongChietKhau;
	private double tongGTGT;
	private double tongTien;
	private String tinhTrang;
	@OneToMany(mappedBy = "donbanhang")
	private List<ChiTietPhieuBan> chiTietPhieuBan;
}
