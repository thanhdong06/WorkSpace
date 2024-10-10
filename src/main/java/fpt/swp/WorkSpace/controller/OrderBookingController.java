package fpt.swp.WorkSpace.controller;

import fpt.swp.WorkSpace.DTO.OrderBookingDetailDTO;
import fpt.swp.WorkSpace.models.OrderBooking;
import fpt.swp.WorkSpace.response.OrderBookingResponse;
import fpt.swp.WorkSpace.response.ResponseHandler;
import fpt.swp.WorkSpace.service.IOrderBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class OrderBookingController {

    @Autowired
    private IOrderBookingService orderBookingService;



    @GetMapping("check-booked-slot")
    public ResponseEntity<Object> getBookedSlot(@RequestParam("roomId") String roomId,
                                                @RequestParam("checkin-date") String checkinDate) {
        try{
            List<OrderBookingResponse> bookedList = orderBookingService.getBookedSlotByRoomAndDate(checkinDate, roomId);
            return ResponseHandler.responseBuilder("ok", HttpStatus.OK, bookedList);
        } catch (RuntimeException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/customer/create-booking")
    public ResponseEntity<Object> createBooking(@RequestHeader("Authorization") String token,
                                                @RequestParam("roomId") String roomId,
                                                @RequestParam(value = "checkin-date", required = false) String checkInDay,
                                                @RequestParam("slots") List<Integer> slots,
                                                @RequestParam(value = "note", required = false) String note) {
        String jwtToken = token.substring(7);
        System.out.println(jwtToken);
        OrderBooking bookingResponse = orderBookingService.createOrderBooking(jwtToken, roomId, checkInDay, slots, note);
        return ResponseHandler.responseBuilder("ok", HttpStatus.CREATED, bookingResponse);
    }

    @GetMapping("/customer/history-booking")
    public ResponseEntity<Object> getCustomerHistoryBooking(@RequestHeader("Authorization") String token) {
        String jwtToken = token.substring(7);
        try{
            List<OrderBookingDetailDTO> bookedList = orderBookingService.getCustomerHistoryBooking(jwtToken);
            return ResponseHandler.responseBuilder("ok", HttpStatus.OK, bookedList);
        } catch (RuntimeException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/customer/create-booking-service")
    public ResponseEntity<Object> createBookingService(@RequestHeader("Authorization") String token,
                                                       @RequestParam("roomId") String roomId,
                                                       @RequestParam(value = "checkin-date", required = false) String checkInDay,
                                                       @RequestParam("slots") List<Integer> slots,
                                                       @RequestParam(required = false) MultiValueMap<String, String> items,
                                                       @RequestParam(value = "note", required = false) String note) {
        String jwtToken = token.substring(7);
        System.out.println(jwtToken);
        System.out.println(roomId);
        System.out.println(slots);
        System.out.println(checkInDay);
        System.out.println("itemms:"+items);
        // Tạo một MultiValueMap<Integer, Integer> để chứa dữ liệu đã chuyển đổi
        MultiValueMap<Integer, Integer> convertedItems = new LinkedMultiValueMap<>();

        // Chuyển đổi từ MultiValueMap<String, String> sang MultiValueMap<Integer, Integer>
        for (Map.Entry<String, List<String>> entry : items.entrySet()) {
            if (entry.getKey().startsWith("items[")) {
                // Tách lấy số từ khóa items[1], items[2], v.v.
                String keyString = entry.getKey().replace("items[", "").replace("]", "");
                Integer key = Integer.valueOf(keyString);
                // Chuyển đổi giá trị từ String sang Integer và thêm vào MultiValueMap
                for (String value : entry.getValue()) {
                    Integer quantity = Integer.valueOf(value);
                    convertedItems.add(key, quantity);  // Thêm vào MultiValueMap
                }
            }
        }

        // In kết quả để kiểm tra
        System.out.println("Converted Items: " + convertedItems);
        try{
            OrderBooking bookingResponse = orderBookingService.createOrderBookingService(jwtToken, roomId, checkInDay, slots, convertedItems, note);
            return ResponseHandler.responseBuilder("ok", HttpStatus.CREATED, bookingResponse );
        }catch (Exception e){
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }






}
