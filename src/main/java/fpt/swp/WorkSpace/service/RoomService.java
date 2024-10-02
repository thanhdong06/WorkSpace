package fpt.swp.WorkSpace.service;

import com.amazonaws.services.kms.model.NotFoundException;
import fpt.swp.WorkSpace.models.Building;
import fpt.swp.WorkSpace.models.Room;
import fpt.swp.WorkSpace.models.RoomType;
import fpt.swp.WorkSpace.repository.BuildingRepository;
import fpt.swp.WorkSpace.repository.RoomRepository;
import fpt.swp.WorkSpace.repository.RoomTypeRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
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
    public Room addNewRoom(String buildingId, String romeTypeId, String roomName, String price,   String status) {
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
        room.setCreationTime(LocalDateTime.now());
        room.setStatus(status);
        room.setBuilding(findBuilding);
        room.setRoomType(roomType);
        roomType.setQuantity(roomType.getQuantity() + 1);    // update quantity in roomtype
        roomTypeRepository.save(roomType);
        return roomRepository.save(room);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public List<Room> getAllRoomsByBuildingId(String buildingId) {
        List<Room> list = roomRepository.getRoomByBuilding(buildingId);
        if (list.isEmpty()) {
            throw new NotFoundException("Co so nay chua co phong");
        }
        return list;

    }

    @Override
    public Room getRoomById(int roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow();
            if (room == null) {
                throw new NullPointerException("Khong tim thay phong");
            }
        return room;
    }

    @Override
    public Room updateRoom(int roomId, String roomName, String price, MultipartFile file, String status) {
        String imageUrl = null;
        if (file != null && !file.isEmpty()) {
            imageUrl = awsS3Service.saveImgToS3(file);
        }
        Room room = roomRepository.findById(roomId).orElseThrow();
        if (roomName != null){
            room.setRoomName(roomName);
        }
        if (price != null) {
            room.setPrice(Float.parseFloat(price));
        }
        if (imageUrl != null){
            room.setRoomImg(imageUrl);
        }
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
