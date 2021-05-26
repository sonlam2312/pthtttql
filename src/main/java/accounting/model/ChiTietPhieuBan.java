package accounting.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class ChiTietPhieuBan {
	@Id
	@GeneratedValue
	private int id;
	private String maHang;
	private String tenHang;
	private int soLuong;
	private String donViTinh;
	private double gia;
	private double chietkhau;
	private double VAT;
	private double thanhTien;
	@ManyToOne
	@JoinColumn(name = "id_baogiaban")
	private BaoGiaBan baoGiaBan;
	@ManyToOne
	@JoinColumn(name = "id_donbanhang")
	private DonBanHang donbanhang;
	@ManyToOne
	@JoinColumn(name = "id_hanghoa")
	private HangHoa hangHoa;
	@ManyToOne
	@JoinColumn(name = "id_chungtuban")
	private ChungTuBan chungTuBan;
	@ManyToOne
	@JoinColumn(name = "id_phieuxuat")
	private PhieuXuatKho phieuXuatKho;
}
