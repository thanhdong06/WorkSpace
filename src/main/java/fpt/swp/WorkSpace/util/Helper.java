package fpt.swp.WorkSpace.util;

import fpt.swp.WorkSpace.DTO.RoomDTO;
import fpt.swp.WorkSpace.models.Room;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;



public class Helper {

    public static String convertLocalDateTime(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String creationTime = now.format(formatter);
        return creationTime;
    }

    public static String generateRandomString(int begin, int end){
        String randomString =  UUID.randomUUID().toString().replace("-", "").substring(begin, end);
        return randomString ;
    }

    public static RoomDTO mapRoomToDTO(Room room){
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomId(room.getRoomId());
        roomDTO.setRoomName(room.getRoomName());
        roomDTO.setPrice(room.getPrice());
        roomDTO.setDescription(room.getDescription());
        roomDTO.setRoomImg(room.getRoomImg());
        roomDTO.setBuilding(room.getBuilding().getBuildingName());
        roomDTO.setRoomType(room.getRoomType().getRoomTypeName());
        return roomDTO;
    }

}
