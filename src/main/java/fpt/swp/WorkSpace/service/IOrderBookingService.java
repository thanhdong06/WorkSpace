package fpt.swp.WorkSpace.service;

import fpt.swp.WorkSpace.DTO.CustomerServiceDTO;
import fpt.swp.WorkSpace.DTO.OrderBookingDetailDTO;
import fpt.swp.WorkSpace.models.OrderBooking;
import fpt.swp.WorkSpace.response.OrderBookingResponse;
import org.springframework.util.MultiValueMap;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface IOrderBookingService {
    List<OrderBookingResponse> getBookedSlotByRoomAndDate(String date, String roomId);

    List<OrderBookingResponse> getBookedSlotByDate(String date);

    OrderBooking createOrderBooking(String jwttoken, String roomId, String date, List<Integer> slotBooking, String note);

    OrderBooking createMultiOrderBooking(String jwttoken, String roomId, String checkin, String checkout, int slot, String note);

    List<OrderBookingDetailDTO> getCustomerHistoryBooking(String jwttoken);

    OrderBooking createOrderBookingService(String jwttoken, String roomId, String date, List<Integer> slotBooking, MultiValueMap<Integer, Integer> items, String note);


    void updateServiceBooking(String orderBookingId, MultiValueMap<Integer, Integer> items);

    CustomerServiceDTO getCustomerService(String orderBookingId);


}
