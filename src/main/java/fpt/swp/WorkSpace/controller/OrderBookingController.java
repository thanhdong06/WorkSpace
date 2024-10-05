package fpt.swp.WorkSpace.controller;

import fpt.swp.WorkSpace.models.OrderBooking;
import fpt.swp.WorkSpace.response.OrderBookingResponse;
import fpt.swp.WorkSpace.response.ResponseHandler;
import fpt.swp.WorkSpace.service.IOrderBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderBookingController {

    @Autowired
    private IOrderBookingService orderBookingService;

    @GetMapping("/hello/or")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("customer/booked-slot")
    public ResponseEntity<Object> getBookedSlot(@RequestParam("roomId") int roomId,
                                                @RequestParam("checkin-date") Date checkinDate) {
        try{
            List<OrderBookingResponse> bookedList = orderBookingService.getBookedSlotByRoomAndDate(checkinDate, roomId);
            return ResponseHandler.responseBuilder("ok", HttpStatus.OK, bookedList);
        } catch (RuntimeException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/customer/create-booking")
    public ResponseEntity<Object> createBooking(@RequestParam("token") String token,
                                                @RequestParam("roomId") int roomId,
                                                @RequestParam("checkin-date") Date checkInDay,
                                                @RequestParam("slots") List<Integer> slots,
                                                @RequestParam(value = "note", required = false) String note) {
        OrderBookingResponse bookingResponse = orderBookingService.createOrderBooking(token, roomId, checkInDay, slots, note);
        return ResponseHandler.responseBuilder("ok", HttpStatus.CREATED, bookingResponse);
    }
}
