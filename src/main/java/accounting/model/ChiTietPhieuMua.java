package accounting.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	private ChungTuMua chungTuMua;
	@ManyToOne
	private BaoGiaMua baoGiaMua;
	@ManyToOne
	private DonMuaHang donMuaHang;
	@ManyToOne
	private HangHoa hangHoa;
	@ManyToOne
	private PhieuNhapKho phieuNhapKho;
}
