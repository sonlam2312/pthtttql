package accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import accounting.model.HoaDonBan;

public interface HoaDonBanRepo extends JpaRepository<HoaDonBan, Integer>{

}
