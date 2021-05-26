package accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import accounting.model.KhachHang;

public interface KhachHangRepo extends JpaRepository<KhachHang, Integer>{
	List<KhachHang> findAll();
	List<KhachHang> findAllByMaDoiTuong(String madoituong);
}
