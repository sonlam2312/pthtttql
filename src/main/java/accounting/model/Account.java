package accounting.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Table
@Entity
@Data
public class Account {
	@Id
	@GeneratedValue
	private int id;
	private String username;
	private String password;
	@OneToOne
	@JoinColumn(name="id_nhanvien")
	private NhanVien nhanVien;
}
