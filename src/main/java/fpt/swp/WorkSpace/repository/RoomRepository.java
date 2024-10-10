package fpt.swp.WorkSpace.repository;

import fpt.swp.WorkSpace.models.Building;
import fpt.swp.WorkSpace.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoomRepository extends JpaRepository<Room, String> {

    @Query("SELECT r FROM Room r WHERE r.building.buildingId = ?1")
    List<Room> getRoomByBuilding( String buildingId);

    @Query("SELECT r FROM Room r WHERE r.building.buildingId = ?1 AND r.status = ?2")
    List<Room> getRoomByBuildingAndStatus( String buildingId, String status);


    @Query("SELECT r FROM Room r WHERE r.building.buildingId = ?1 AND r.roomType.id = ?2")
    List<Room> getRoomsByBuildingAndRoomType(String building, String roomType);

    @Query("SELECT r FROM Room r WHERE r.roomType.id= ?1")
    List<Room> getRoomByRoomType(String roomTypeId);

    @Query("SELECT r.roomId FROM Room r where r.roomId = ?1 ")
    boolean getRoomIdByRoomId(int roomId);
}
