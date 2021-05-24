package accounting.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class PhieuNhapKho {
	@Id
	@GeneratedValue
	private int id;
	private Date ngayHachToan;
	private Date ngayChungTu;
	private String soPhieuNhap;
	private String maDoiTuong;
	private String tenDoiTuong;
	private String nguoiGiaoHang;
	private String dienGiai;
	private String nguoiPhuTrach;
	@OneToOne
	@JoinColumn(name = "id_chungtumua",referencedColumnName = "id")
	private ChungTuMua chungTuMua;
	// này không lưu trong csdl cho đỡ nhiêu
	// lấy list chi tiết phiếu mua của chứng từ gán vào;
	@OneToMany(mappedBy = "phieuNhapKho", cascade = CascadeType.ALL)
	private List<ChiTietPhieuMua> chiTietPhieuMuas;
}
