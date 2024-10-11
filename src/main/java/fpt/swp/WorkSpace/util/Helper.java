package fpt.swp.WorkSpace.util;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import fpt.swp.WorkSpace.DTO.RoomDTO;
import fpt.swp.WorkSpace.models.Room;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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

    public static String generateRoomId(){
        String randomString =  UUID.randomUUID().toString().replace("-", "").substring(0, 3);
        return randomString ;

    }

    public static RoomDTO mapRoomToDTO(Room room){
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomId(room.getRoomId());
        roomDTO.setRoomName(room.getRoomName());
        roomDTO.setPrice(room.getPrice());
        roomDTO.setDescription(room.getDescription());
        roomDTO.setRoomImg(room.getRoomImg().split(","));
        roomDTO.setBuilding(room.getBuilding().getBuildingName());
        roomDTO.setRoomType(room.getRoomType().getRoomTypeName());
        return roomDTO;
    }



}
