package fpt.swp.WorkSpace.repository;

import fpt.swp.WorkSpace.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query("SELECT r FROM Room r WHERE r.building.buildingId = ?1")
    List<Room> getRoomByBuilding(@Param("buildingId") String buildingId);
}
