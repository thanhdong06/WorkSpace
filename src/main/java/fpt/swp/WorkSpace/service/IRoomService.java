package fpt.swp.WorkSpace.service;

import fpt.swp.WorkSpace.DTO.RoomDTO;
import fpt.swp.WorkSpace.models.Room;
import fpt.swp.WorkSpace.models.RoomType;

import java.util.List;

public interface IRoomService {

    Room addNewRoom(String buildingId, String roomTypeId, String roomName, String price, String[] staffId , String description, String status);

    List<Room> getAllRooms();

    Room getRoomById(String id);

    RoomDTO viewRoomById(String id);

    List<Room> getRoomsByBuildingId(String buildingId);

    List<RoomDTO> viewRoomsByBuildingId(String buildingId);

    List<RoomDTO> getRoomsByBuildingAndStatus(String buildingId, String status);

    List<Room> getRoomsByRoomType(String roomTypeId);

    List<Room> getRoomsByBuildingAndRoomType(String buildingId, String roomTypeId);

    List<RoomType> getAllRoomType();

    Room updateRoom(String roomId, String roomName, String price, String status, String[] staffId, String description);

    void deleteRoom(String id);

}
