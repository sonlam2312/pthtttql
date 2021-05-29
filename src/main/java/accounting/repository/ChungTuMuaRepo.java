package accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import accounting.model.ChungTuMua;

public interface ChungTuMuaRepo extends JpaRepository<ChungTuMua, Integer>{
	List<ChungTuMua> findAllBySoChungTu(String sochungtu);
}
