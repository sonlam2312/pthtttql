package accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import accounting.model.NhanVien;

public interface NhanVienRepo extends JpaRepository<NhanVien, Integer>{
	List<NhanVien> findAllByMaNhanVien(String manhanvien);
}
