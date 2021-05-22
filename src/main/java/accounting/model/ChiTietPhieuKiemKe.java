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
public class ChiTietPhieuKiemKe {
	@Id
	@GeneratedValue
	private int id;
	private String maHang;
	private String tenHang;
	private String tenKho;
	private String donViTinh;
	private int soLuongTheoSo;
	private int soLuongKiemDinh;
	private int chenhLech;
	private String xuLy;
	@ManyToOne
	@JoinColumn(name = "id_phieukiemke", referencedColumnName = "id")
	private KiemKe phieuKiemKe;
}
