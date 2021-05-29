package accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import accounting.model.KiemKe;

public interface KiemKeRepo extends JpaRepository<KiemKe, Integer>{
	List<KiemKe> findAll();
	List<KiemKe> findAllBySoChungTu(String sochungtu);
}
