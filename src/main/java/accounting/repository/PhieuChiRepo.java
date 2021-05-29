package accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import accounting.model.ChungTuMua;
import accounting.model.PhieuChi;

public interface PhieuChiRepo extends JpaRepository<PhieuChi, Integer>{
	List<PhieuChi> findAllBySoPhieuChi(String sophieuchi);
	List<PhieuChi> findAllByChungTuMua(ChungTuMua chungtumua);
}
