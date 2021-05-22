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
public class PhieuXuatKho {
	@Id
	@GeneratedValue
	private int id;
	private Date ngayHachToan;
	private Date ngayChungTu;
	private String soPhieuXuat;
	private String maDoiTuong;
	private String tenDoiTuong;
	private String nguoiGiaoHang;
	private String dienGiai;
	private String nguoiPhuTrach;
	@OneToOne
	@JoinColumn(name = "id_chungtuban")
	private ChungTuBan chungTuBan;
	// này không lưu trong csdl cho đỡ nhiêu
	// lấy list chi tiết phiếu bán của chứng từ gán vào;
	@Transient
	private List<ChiTietPhieuBan> chiTietPhieuBans;
}