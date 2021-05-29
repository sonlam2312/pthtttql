package accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import accounting.model.ChungTuBan;
import accounting.model.PhieuXuatKho;

public interface PhieuXuatKhoRepo extends JpaRepository<PhieuXuatKho, Integer>{
	List<PhieuXuatKho> findAllByChungTuBan(ChungTuBan chungtuban);
	List<PhieuXuatKho> findAllBySoPhieuXuat(String sophieuxuat);
}
