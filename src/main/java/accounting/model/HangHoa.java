package accounting.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class HangHoa {
	@Id
	@GeneratedValue
	private int id;
	private String maHang;
	private String tenHang;
	private String moTa;
	private String donViTinh;
	private double giaNhap;
	private int soLuong;
	@ManyToOne
	@JoinColumn(name = "id_kho")
	private Kho kho;
	@OneToMany
	private List<ChiTietPhieuMua> chiTietPhieuMua;
	@OneToMany
	private List<ChiTietPhieuBan> chiTietPhieuBans;
}
