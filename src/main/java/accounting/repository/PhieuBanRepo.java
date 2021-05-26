package accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import accounting.model.ChiTietPhieuBan;

public interface PhieuBanRepo extends JpaRepository<ChiTietPhieuBan, Integer>{

}
