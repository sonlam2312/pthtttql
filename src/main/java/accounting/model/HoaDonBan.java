package accounting.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Table
@Data
public class HoaDonBan {
	@Id
	@GeneratedValue
	private int id;
	private String mauSoHoaDon;
	private String kiHieuHoaDon;
	private String soHoaDon;
	private Date ngayHoaDon;
	private String maDoiTuong;
	private String tenDoiTuong;
	private String diaChi;
	private String maSoThue;
	private double tongTien;
	@OneToOne
	@JoinColumn(name = "id_chungtuban")
	private ChungTuBan chungTuBan;
	// này không lưu trong csdl cho đỡ nhiêu
	// lấy list chi tiết phiếu ban của chứng từ gán vào;
	@Transient
	private List<ChiTietPhieuBan> chiTietPhieuBans;
}
