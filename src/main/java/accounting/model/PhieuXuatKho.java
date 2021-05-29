package accounting.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class PhieuXuatKho {
	@Id
	@GeneratedValue
	private int id;
	private Date ngayHachToan;
	private Date ngayChungTu;
	private String soPhieuXuat;
	private String maDoiTuong;
	private String tenDoiTuong;
	private String nguoiGiaoHang;
	private String diaChi;
	private String dienGiai;
	private String nguoiPhuTrach;
	private double tongHang;
	private double tongChietKhau;
	private double tongGTGT;
	private double tongTien;
	private double tienShip;
	@OneToOne
	@JoinColumn(name = "id_chungtuban")
	private ChungTuBan chungTuBan;
	@OneToMany(mappedBy = "phieuXuatKho")
	private List<ChiTietPhieuBan> chiTietPhieuBan;
}