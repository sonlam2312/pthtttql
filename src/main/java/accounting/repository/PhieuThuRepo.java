package accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import accounting.model.PhieuThu;

public interface PhieuThuRepo extends JpaRepository<PhieuThu, Integer>{
	
}
