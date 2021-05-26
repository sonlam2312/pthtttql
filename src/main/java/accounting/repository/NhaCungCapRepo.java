package accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import accounting.model.NhaCungCap;

public interface NhaCungCapRepo extends JpaRepository<NhaCungCap, Integer>{
	List<NhaCungCap> findAll();
	List<NhaCungCap> findAllByMaDoiTuong(String maDoiTuong);
}
