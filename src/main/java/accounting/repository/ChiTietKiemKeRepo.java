package accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import accounting.model.ChiTietPhieuKiemKe;

public interface ChiTietKiemKeRepo extends JpaRepository<ChiTietPhieuKiemKe, Integer>{

}
