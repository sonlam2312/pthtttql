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
public class ChiTietPhieuMua {
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
	@JoinColumn(name = "id_chungtumua",referencedColumnName = "id")
	private ChungTuMua chungTuMua;
	@ManyToOne
	@JoinColumn(name = "id_baogiamua", referencedColumnName = "id")
	private BaoGiaMua baoGiaMua;
	@ManyToOne
	@JoinColumn(name = "id_donmuahang")
	private DonMuaHang donMuaHang;
	@ManyToOne
	@JoinColumn(name = "id_hanghoamua", referencedColumnName = "id")
	private HangHoa hangHoa;
	@ManyToOne
	@JoinColumn(name = "id_phieunhapkho", referencedColumnName = "id")
	private PhieuNhapKho phieuNhapKho;
}
