package accounting.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class NhaCungCap {
	@Id
	@GeneratedValue
	private int id; 
	private String maDoiTuong;
	private String tenDoiTuong;
	private String diaChi;
	private String SDT;
	private String maSoThue;
	private String soTaiKhoan;
}
