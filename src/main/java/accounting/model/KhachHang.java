package accounting.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class KhachHang {
	@Id
	@GeneratedValue
	private int id; // id KHxxx là khách hàng, NCCxx là nhà cung cấp
	private String maDoiTuong;
	private String tenDoiTuong;
	private String diaChi;
	private String SDT;
	private String maSoThue;
	private String soTaiKhoan;
}
