package fpt.swp.WorkSpace.service;

import fpt.swp.WorkSpace.models.Room;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IRoomService {

    Room addNewRoom(String buildingId, String roomTypeId, String roomName, String price, String quantity,  MultipartFile file, String status);
    List<Room> getAllRooms();
    List<Room> getAllRoomsByBuildingId(String buildingId);
    Room getRoomById(int id);

    Room updateRoom(int roomId, String roomName, String price, String quantity,  MultipartFile file, String status);
    void deleteRoom(int id);



}
