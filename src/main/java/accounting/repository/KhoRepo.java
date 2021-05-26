package accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import accounting.model.Kho;

public interface KhoRepo extends JpaRepository<Kho, Integer>{
	List<Kho> findAll();
	List<Kho> findAllByMaKho(String makho);
}
