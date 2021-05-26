package accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import accounting.model.BaoGiaBan;

public interface BaoGiaBanRepo extends JpaRepository<BaoGiaBan, Integer>{
	List<BaoGiaBan> findBySoBaoGia(String soBaoGia);
}
