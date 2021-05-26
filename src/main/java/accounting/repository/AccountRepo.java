package accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import accounting.model.Account;

public interface AccountRepo extends JpaRepository<Account, Integer>{
	List<Account> findByUsernameAndPassword(String username, String password);
}
