package accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import accounting.model.ToKhaiThue;

public interface ToKhaiThueRepo extends JpaRepository<ToKhaiThue, Integer>{
	List<ToKhaiThue> findAllByMauSo(String mauso);
}
