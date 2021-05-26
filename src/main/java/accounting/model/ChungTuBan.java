package accounting.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Table
@Data
public class ChungTuBan {
	@Id
	@GeneratedValue
	private int id;
	private Date ngayHachToan;
	private Date ngayChungTu;
	private String soChungTu;
	private String maDoiTuong;
	private String tenDoiTuong;
	private String dienGiai;
	private String diaChi;
	private String nguoiPhuTrach;
	private String tinhTrang;
	// @Transient là các cờ để xem có lập hóa đơn, phiếu thu, phiếu xuất hay không, không lưu trong csdl
	@Transient
	private String thanhToan;
	private String phuongThucThanhToan = "Tiền mặt";
	@Transient
	private String nhanKemHoaDon;
	@Transient
	private boolean kemphieuxuat; // nhận hoặc không nhận hóa đơn
	private double tongHang;
	private double tongChietKhau;
	private double tongGTGT;
	private double tongTien;
	private double tienShip;
	@OneToOne(mappedBy = "chungTuBan",cascade = CascadeType.ALL)
	private PhieuXuatKho phieuXuatKho;
	@OneToOne(mappedBy = "chungTuBan",cascade = CascadeType.ALL)
	private HoaDonBan hoaDonBan;
	@OneToMany(mappedBy = "chungTuBan")
	private List<PhieuThu> phieuThu;
	@OneToMany(mappedBy = "chungTuBan")
	private List<ChiTietPhieuBan> chiTietPhieuBan;
}
