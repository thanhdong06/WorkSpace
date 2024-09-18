package fpt.swp.WorkSpace.repository;

import fpt.swp.WorkSpace.models.AppUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {
     Optional<AppUser> findByuserName(String username);








}
