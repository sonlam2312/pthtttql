package accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import accounting.model.BangLuong;

public interface BangLuongRepo extends JpaRepository<BangLuong, Integer>{
	List<BangLuong> findAllByMaNhanVien(String manhanvien);
}
