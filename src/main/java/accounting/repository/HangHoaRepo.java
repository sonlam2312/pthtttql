package accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import accounting.model.HangHoa;

public interface HangHoaRepo extends JpaRepository<HangHoa, Integer>{
	@Query("SELECT h FROM HangHoa h JOIN h.kho k WHERE k.id = ?1 ")
	List<HangHoa> fillAllByIDKho(int id_kho);
}
