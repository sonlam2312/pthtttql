package accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import accounting.model.ChungTuBan;
import accounting.model.PhieuThu;

public interface PhieuThuRepo extends JpaRepository<PhieuThu, Integer>{
	List<PhieuThu> findAllByChungTuBan(ChungTuBan chungtuban);
	List<PhieuThu> findAllBySoPhieuThu(String sophieuthu);
	List<PhieuThu> findAllByLoaiThu(String loaiThu);
	List<PhieuThu> findAllByMaDoiTuongAndLoaiThu(String madoituong,String loaithu);
}
