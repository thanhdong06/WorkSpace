package fpt.swp.WorkSpace.controller;

import fpt.swp.WorkSpace.models.Room;
import fpt.swp.WorkSpace.models.RoomType;
import fpt.swp.WorkSpace.response.ResponseHandler;
import fpt.swp.WorkSpace.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasAuthority('MANAGER')")
public class RoomController {
    @Autowired
    private IRoomService roomService;

    @PostMapping("/manager/add-new-room")
    public ResponseEntity<Object> addNewRoom(@RequestParam(value = "buildingId", required = false) String buildingId,
                                             @RequestParam(value = "roomTypeId", required = false) String roomTypeId,
                                             @RequestParam(value = "roomName", required = false) String roomName,
                                             @RequestParam(value = "price", required = false) String price,
                                             @RequestParam(value = "image", required = false) MultipartFile image,
                                             @RequestParam(value = "status", required = false) String status){
        // Use parameter instead of body because image is a file
        // file should be multipart/form-data
        // JSON cann't handle this file

        try{
            Room newRoom = roomService.addNewRoom(buildingId, roomTypeId, roomName, price, image, status);
            return ResponseHandler.responseBuilder("Them phong thanh cong", HttpStatus.OK, newRoom);
        } catch (NullPointerException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/manager/get-all-room")
    public ResponseEntity<Object> getAllRoom(){
        List<Room> roomList = roomService.getAllRooms();
        if (roomList.isEmpty()){
            return ResponseHandler.responseBuilder("Khong co phong . Vui long them phong", HttpStatus.NOT_FOUND);
        }
        return ResponseHandler.responseBuilder("Success", HttpStatus.OK, roomList);
    }

    @GetMapping("manager/get-room-by-id/{roomId}")
    public ResponseEntity<Object> getRoomById(@PathVariable("roomId") int roomId){
        Room findRoom = roomService.getRoomById(roomId);
        try {
            return ResponseHandler.responseBuilder("Success", HttpStatus.OK, findRoom);
        }catch (Exception e){
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

        @GetMapping("manager/get-room-by-building/building")
    public ResponseEntity<Object> getRoomByIdBuilding(@RequestParam("buildingId") String buildingId){
        List<Room> roomList = roomService.getAllRoomsByBuildingId(buildingId);
        try {
            return ResponseHandler.responseBuilder("Success", HttpStatus.OK, roomList);
        } catch (Exception e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/manager/update-room/{roomId}")
    public ResponseEntity<Object> updateRoom(@PathVariable int roomId,
                                             @RequestParam(value = "roomName", required = false) String roomName,
                                             @RequestParam(value = "price", required = false) String price,
                                             @RequestParam(value = "quantity", required = false) String quantity,
                                             @RequestParam(value = "image", required = false) MultipartFile image,
                                             @RequestParam(value = "status", required = false) String status) {

        try {
            Room newRoom = roomService.updateRoom(roomId, roomName, price, image, status);
            return ResponseHandler.responseBuilder("Cap nhap thanh cong", HttpStatus.OK, newRoom);
        } catch (Exception e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/manager/delete-room/{roomId}")
    public ResponseEntity<Object> deleteRoom(@PathVariable int roomId){
             try {
                 roomService.deleteRoom(roomId);
                 return ResponseHandler.responseBuilder("Da xoa thanh cong", HttpStatus.OK);
             }catch (Exception e){
                 return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.BAD_REQUEST);
             }

    }

    @GetMapping("manager/get-all-room-type")
        public ResponseEntity<Object> getRoomType(){
            try {
                List<RoomType> roomTypeList = roomService.getAllRoomType();
                return ResponseHandler.responseBuilder("Success", HttpStatus.OK, roomTypeList);
            }catch (Exception e){
                return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.NOT_FOUND);
            }
        }




    }





