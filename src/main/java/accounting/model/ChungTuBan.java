package accounting.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	private String nguoiPhuTrach;
	// @Transient là các cờ để xem có lập hóa đơn, phiếu thu, phiếu xuất hay không, không lưu trong csdl
	@Transient
	private String thanhToan;
	private String phuongThucThanhToan = "Tiền mặt";
	@Transient
	private String nhanKemHoaDon;
	@Transient
	private String loaiMuaHang; // nhận hoặc không nhận hóa đơn
	private double tongHang;
	private double tongChietKhau;
	private double tongGTGT;
	private double tongTien;
	@OneToOne(mappedBy = "chungTuBan")
	private PhieuXuatKho phieuXuatKho;
	@OneToOne(mappedBy = "chungTuBan")
	private HoaDonBan hoaDonBan;
	@OneToMany(mappedBy = "chungTuBan")
	private List<PhieuThu> phieuThu;
	@OneToMany(mappedBy = "chungTuBan")
	private List<ChiTietPhieuBan> chiTietPhieuBan;
	@OneToOne
	@JoinColumn(name = "id_donbanhang")
	private DonMuaHang donBanHang;
	@ManyToOne
	@JoinColumn(name = "id_hanghoaban", referencedColumnName = "id")
	private HangHoa hangHoa;
}
