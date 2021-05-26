package accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import accounting.model.PhieuChi;

public interface PhieuChiRepo extends JpaRepository<PhieuChi, Integer>{
	@Query("SELECT sum(soTien) FROM PhieuChi c WHERE c.chungTuMua.soChungTu = ?1")
	public double getTongTienDaTra(String soChungTu);
	@Query("SELECT c FROM PhieuChi c WHERE c.chungTuMua.soChungTu = ?1")
	public List<PhieuChi> getPhieuChiBySoChungTuMua(String soChungTu);
	@Query("SELECT c FROM PhieuChi c WHERE c.soPhieuChi LIKE %?1%")
	public List<PhieuChi> TimKiemPhieuChi(String sophieuchi);
}
