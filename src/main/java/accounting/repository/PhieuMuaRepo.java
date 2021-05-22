package accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import accounting.model.ChiTietPhieuMua;

public interface PhieuMuaRepo extends JpaRepository<ChiTietPhieuMua, Integer>{
	@Query("SELECT c FROM BaoGiaMua m INNER JOIN m.chiTietPhieuMua c WHERE m.soBaoGia = ?1")
	List<ChiTietPhieuMua> listChiTietPhieuMua(String sobaogia);
	@Query("SELECT c FROM DonMuaHang h INNER JOIN h.chiTietPhieuMua c WHERE h.soDonHang = ?1 AND c.donMuaHang = h.id")
	List<ChiTietPhieuMua> listChiTietPhieuMuaByDonHang(String sodonhang);
	@Query("UPDATE ChiTietPhieuMua c SET c.donMuaHang = ?1 WHERE c.baoGiaMua = ?2")
	void UpdateChiTietPhieu(int id_donmuahang, int id_baogiamua);
}
