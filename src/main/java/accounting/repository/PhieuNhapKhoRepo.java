package accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import accounting.model.PhieuNhapKho;

public interface PhieuNhapKhoRepo extends JpaRepository<PhieuNhapKho, Integer>{
	@Query("SELECT p FROM PhieuNhapKho p WHERE p.soPhieuNhap LIKE %?1%")
	public List<PhieuNhapKho> TimKiemPhieuNhap(String soPhieuNhap);
}
