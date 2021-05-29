package accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import accounting.model.DonMuaHang;

public interface DonMuaHangRepo extends JpaRepository<DonMuaHang, Integer>{
	List<DonMuaHang> findAllBySoDonHang(String soDonHang);
}
