package accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import accounting.model.PhieuNhapKho;

public interface PhieuNhapKhoRepo extends JpaRepository<PhieuNhapKho, Integer>{
	List<PhieuNhapKho> findAllBySoPhieuNhap(String soPhieuNhap);
}
