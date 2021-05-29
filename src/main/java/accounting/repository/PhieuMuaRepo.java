package accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import accounting.model.ChiTietPhieuMua;

public interface PhieuMuaRepo extends JpaRepository<ChiTietPhieuMua, Integer>{
	
}
