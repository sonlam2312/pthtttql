package accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import accounting.model.BaoGiaMua;

public interface BaoGiaMuaRepo extends JpaRepository<BaoGiaMua, Integer>{
	@Query("SELECT b FROM BaoGiaMua b WHERE b.soBaoGia like %?1% ")
	List<BaoGiaMua> search_baogiamua(String id);
	@Query("SELECT b FROM BaoGiaMua b WHERE b.soBaoGia = ?1")
	List<BaoGiaMua> getBaoGiaBySoBaoGia(String sobaogia);
}
