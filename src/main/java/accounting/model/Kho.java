package accounting.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class Kho {
	@Id
	@GeneratedValue
	private int id;
	private String maKho;
	private String tenKho;
	private String diaChi;
	private String SDT;
	private String thuKho;
	@OneToMany(mappedBy = "kho")
	private List<HangHoa> hangHoa;
}
