package fpt.swp.WorkSpace.service;

import com.amazonaws.services.kms.model.NotFoundException;
import fpt.swp.WorkSpace.models.Building;
import fpt.swp.WorkSpace.models.Room;
import fpt.swp.WorkSpace.models.RoomType;
import fpt.swp.WorkSpace.repository.BuildingRepository;
import fpt.swp.WorkSpace.repository.RoomRepository;
import fpt.swp.WorkSpace.repository.RoomTypeRepository;
import fpt.swp.WorkSpace.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.support.RouterFunctionMapping;

import java.util.ArrayList;
import java.util.List;
@Service
public class RoomService implements IRoomService{

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private AwsS3Service awsS3Service;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;


    @Override
    public Room addNewRoom(String buildingId, String romeTypeId, String roomName, String price, String[] staffID, String description, String status) {
//        String img = awsS3Service.saveImgToS3(file);
        Building findBuilding = buildingRepository.findById(buildingId).orElseThrow();
        RoomType roomType = roomTypeRepository.findById(romeTypeId).orElseThrow();
        if (findBuilding == null) {
            throw new NotFoundException("Khong tim thay co so");
        }
        if (roomType == null) {
            throw new NotFoundException("Loai phong khong hop le");
        }
        Room room = new Room();
        room.setRoomName(roomName);
        room.setPrice(Float.parseFloat(price));
//        room.setRoomImg(img);

        // set local day time
        String creationTime = Helper.convertLocalDateTime();
        room.setCreationTime(creationTime);

        // conver array to string
        String staffIDList = String.join(", ", staffID);
        room.setStaffAtRoom(staffIDList);

        room.setStatus(status);
        room.setBuilding(findBuilding);
        room.setRoomType(roomType);
        room.setDescription(description);
        roomType.setQuantity(roomType.getQuantity() + 1);    // update quantity in roomtype
        roomTypeRepository.save(roomType);
        return roomRepository.save(room);
    }

    @Override
    public List<Room> getAllRooms() {
        List<Room> roomList = roomRepository.findAll();
        if (roomList.isEmpty()) {
            throw new NotFoundException("Chua co phong nao!!!");
        }
        return roomList;
    }

    @Override
    public Room getRoomById(int id) {
        Room room = roomRepository.findById(id).orElseThrow();
        if (room == null) {
            throw new NotFoundException("Phong khong ton tai");
        }
        return room;
    }

    @Override
    public List<Room> getRoomsByBuildingId(String buildingId) {
        List<Room> roomList = roomRepository.getRoomByBuilding(buildingId);
        if (roomList.isEmpty()) {
            throw new NotFoundException("Co so nay chua co phong");
        }
        return roomList;
    }

    @Override
    public List<Room> getRoomsByRoomType(String roomTypeId) {
        List<Room> roomList = roomRepository.getRoomByRoomType(roomTypeId);
        if (roomList.isEmpty()) {
            throw new NotFoundException("Khong co phong hop le");
        }
        return roomList;
    }

    @Override
    public List<Room> getRoomsByBuildingAndRoomType(String buildingId, String roomTypeId) {
        List<Room> roomList = roomRepository.getRoomsByBuildingAndRoomType(buildingId, roomTypeId);
        if (roomList.isEmpty()) {
            throw new NotFoundException("Khong co phong hop le");
        }
        return roomList;
    }


    @Override
    public Room updateRoom(int roomId, String roomName, String price, String status) {
//        String imageUrl = null;
//        if (file != null && !file.isEmpty()) {
//            imageUrl = awsS3Service.saveImgToS3(file);
//        }
        Room room = roomRepository.findById(roomId).orElseThrow();
        if (roomName != null){
            room.setRoomName(roomName);
        }
        if (price != null) {
            room.setPrice(Float.parseFloat(price));
        }
//        if (imageUrl != null){
//            room.setRoomImg(imageUrl);
//        }
        if (status != null) {
            room.setStatus(status);
        }
        return roomRepository.save(room);
    }


    @Override
    public void deleteRoom(int id) {
        Room room = roomRepository.findById(id).orElseThrow();
        if (room == null) {
            throw new RuntimeException("Room not found");
        }
        roomRepository.delete(room);
    }

    public List<RoomType> getAllRoomType(){
        return roomTypeRepository.findAll();
    }
}
