package fpt.swp.WorkSpace.repository;

import fpt.swp.WorkSpace.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("SELECT c FROM Customer c WHERE c.user.userId = (SELECT u.userId FROM User u WHERE u.userName = ?1)")
    Customer findCustomerByUsername(@Param("username") String username);



}
