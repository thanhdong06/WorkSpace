package fpt.swp.WorkSpace.service;

import fpt.swp.WorkSpace.models.OrderBooking;
import fpt.swp.WorkSpace.response.OrderBookingResponse;

import java.sql.Date;
import java.util.List;

public interface IOrderBookingService {
    List<OrderBookingResponse> getBookedSlotByRoomAndDate(Date date, int roomId);

    OrderBooking createOrderBooking(String jwttoken, int roomId, String date, List<Integer> slotBooking, String note);

    List<OrderBooking> getCustomerHistoryBooking(String jwttoken);
}
