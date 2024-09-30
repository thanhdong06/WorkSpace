package fpt.swp.WorkSpace.repository;

import fpt.swp.WorkSpace.models.CustomerWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerWalletRepository extends JpaRepository<CustomerWallet, String> {
}
