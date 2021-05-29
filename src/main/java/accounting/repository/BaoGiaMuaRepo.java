package accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import accounting.model.BaoGiaMua;

public interface BaoGiaMuaRepo extends JpaRepository<BaoGiaMua, Integer>{
	List<BaoGiaMua> findAllBySoBaoGia(String sobaogia);
}
