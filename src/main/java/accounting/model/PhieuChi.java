package accounting.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Table
@Data
public class PhieuChi {
	@Id
	@GeneratedValue
	private int id;
	private Date ngayHachToan;
	private Date ngayChungTu;
	private String soPhieuChi;
	private String maDoiTuong;
	private String tenDoiTuong;
	private String taiKhoanNhan;
	private String diaChi;
	private String dienGiai;
	private double soTien;
	@Transient
	private String phuongThucThanhToan;
	@Transient
	private String loaiChi;
	@ManyToOne
	@JoinColumn(name = "id_chungtumua", referencedColumnName = "id")
	private ChungTuMua chungTuMua;
}
