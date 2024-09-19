package fpt.swp.WorkSpace.repository;

import fpt.swp.WorkSpace.models.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Customer, Integer> {
    Customer findByuserName(String username);








}
