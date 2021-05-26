package accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import accounting.model.ChungTuMua;

public interface ChungTuMuaRepo extends JpaRepository<ChungTuMua, Integer>{
	@Query("SELECT c FROM ChungTuMua c WHERE c.soChungTu LIKE %?1%")
	public List<ChungTuMua> Search_Chungtumua(String soChungtu);
	@Query("SELECT c FROM ChungTuMua c WHERE c.soChungTu = ?1")
	public List<ChungTuMua> getChungTuMuaBySoChungTu(String sochungtu);
}
