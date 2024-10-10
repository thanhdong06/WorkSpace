package fpt.swp.WorkSpace.service;

import fpt.swp.WorkSpace.models.OrderBooking;
import fpt.swp.WorkSpace.response.OrderBookingResponse;
import org.springframework.util.MultiValueMap;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface IOrderBookingService {
    List<OrderBookingResponse> getBookedSlotByRoomAndDate(String date, String roomId);

    OrderBooking createOrderBooking(String jwttoken, String roomId, String date, List<Integer> slotBooking, String note);

    List<OrderBooking> getCustomerHistoryBooking(String jwttoken);

    OrderBooking createOrderBookingService(String jwttoken, String roomId, String date, List<Integer> slotBooking, MultiValueMap<Integer, Integer> items, String note);
}
