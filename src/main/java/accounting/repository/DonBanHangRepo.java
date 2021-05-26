package accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import accounting.model.DonBanHang;

public interface DonBanHangRepo extends JpaRepository<DonBanHang, Integer>{;
	List<DonBanHang> findAllBySoDonHang(String sodonhang); 
}
