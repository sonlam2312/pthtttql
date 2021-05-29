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
public class HoaDonMua {
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
	@JoinColumn(name = "id_chungtumua",referencedColumnName = "id")
	private ChungTuMua chungTuMua;
	@Transient
	private List<ChiTietPhieuMua> chiTietPhieuMuas;
}
