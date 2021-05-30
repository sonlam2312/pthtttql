package accounting.model;

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
public class ThangLuong {
	@Id
	@GeneratedValue
	private int id;
	private String month;
	@OneToMany(mappedBy = "thangLuong",cascade = CascadeType.ALL)
	private List<BangLuong> bangLuong;
}
