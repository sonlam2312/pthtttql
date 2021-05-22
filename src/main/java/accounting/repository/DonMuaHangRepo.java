package accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import accounting.model.DonMuaHang;

public interface DonMuaHangRepo extends JpaRepository<DonMuaHang, Integer>{
	@Query("SELECT d FROM DonMuaHang d WHERE d.soDonHang like %?1% ")
	List<DonMuaHang> search_donmuahang(String id);
	@Query("SELECT d FROM DonMuaHang d WHERE d.soDonHang = ?1 ")
	List<DonMuaHang> getDonMuaTheoSoDon(String soDonHang);
}
