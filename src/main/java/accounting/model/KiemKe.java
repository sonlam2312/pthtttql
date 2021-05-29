package accounting.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Table
@Entity
@Data
public class KiemKe {
	@Id
	@GeneratedValue
	private int id;
	private String soChungTu;
	private Date ngayKiemKe;
	private String mucDich;
	private String kho;
	private String nguoiPhuTrach;
	@OneToMany(mappedBy = "phieuKiemKe",cascade = CascadeType.ALL)
	private List<ChiTietPhieuKiemKe> chiTietPhieuKiemKe;
	
}
