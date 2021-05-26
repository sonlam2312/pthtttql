package accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import accounting.model.ChungTuBan;

public interface ChungTuBanRepo extends JpaRepository<ChungTuBan, Integer>{
	List<ChungTuBan> findAllBySoChungTu(String soChungTu);
}
