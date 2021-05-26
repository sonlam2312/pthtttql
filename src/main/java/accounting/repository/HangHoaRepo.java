package accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import accounting.model.HangHoa;
import accounting.model.Kho;

public interface HangHoaRepo extends JpaRepository<HangHoa, Integer>{
	List<HangHoa> findAllByKho(Kho kho);
	List<HangHoa> findAllByMaHang(String mahanghoa);
}
