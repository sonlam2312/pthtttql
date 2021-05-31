package accounting.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table
@Entity
@Data
public class ToKhaiThue {
	@Id
	@GeneratedValue
	private int id;
	private String mauSo;
	private String tenToKhai;
	private String loaiThue = "GTGT";
	private String ki;
	private String nguoiLapDon;
	private Date ngayKi;
}
