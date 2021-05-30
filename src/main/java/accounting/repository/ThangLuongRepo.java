package accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import accounting.model.ThangLuong;

public interface ThangLuongRepo extends JpaRepository<ThangLuong, Integer>{
	List<ThangLuong> findAllByMonth(String month);
}
