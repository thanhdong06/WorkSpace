package fpt.swp.WorkSpace.repository;

import fpt.swp.WorkSpace.models.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsRepository extends JpaRepository<Items, Integer> {
}
