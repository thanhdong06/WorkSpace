package fpt.swp.WorkSpace.service;

import fpt.swp.WorkSpace.models.Customer;
import fpt.swp.WorkSpace.models.OrderBooking;
import fpt.swp.WorkSpace.models.Room;
import fpt.swp.WorkSpace.models.TimeSlot;
import fpt.swp.WorkSpace.repository.*;
import fpt.swp.WorkSpace.response.OrderBookingResponse;
import fpt.swp.WorkSpace.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderBookingService  implements IOrderBookingService {
    @Autowired
    private OrderBookingRepository orderBookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private TimeSlotRepository timeSlotRepository;




    @Override
    public List<OrderBookingResponse> getBookedSlotByRoomAndDate(Date date, int roomId) {
        // get booking list checkin day and room avaiable
        List<OrderBooking> bookings = orderBookingRepository.getTimeSlotBookedByRoomAndDate( date, roomId);


        List<OrderBookingResponse> bookingResponsesList = new ArrayList<>();

        if ( bookings.isEmpty()){
            throw new RuntimeException("Ngay " + date + " phong " + roomId + " chua co booking nao.");
        }
        for (OrderBooking orderBooking : bookings){
            OrderBookingResponse orderBookingResponse = new OrderBookingResponse();
            orderBookingResponse.setBookingId(orderBooking.getBookingId());
            orderBookingResponse.setRoomId(orderBooking.getRoom().getRoomId());
            orderBookingResponse.setCheckinDate(orderBooking.getCheckinDate());
            orderBookingResponse.setTotalPrice(orderBooking.getTotalPrice());

            // Get all timeslot in Booking
            List<Integer> timeSlotIdBooked = new ArrayList<>();
            int countSlot = orderBooking.getSlot().size();
            for (int i = 0; i < countSlot; i++){
                timeSlotIdBooked.add(orderBooking.getSlot().get(i).getTimeSlotId());
            }
            orderBookingResponse.setSlotId(timeSlotIdBooked);
            bookingResponsesList.add(orderBookingResponse);
        }
        return bookingResponsesList;
    }

    @Override
    public OrderBooking createOrderBooking(String jwttoken, int roomId, String checkinDate, List<Integer> slotBooking, String note) {

        String username = jwtService.extractUsername(jwttoken);
        Customer customer =  customerRepository.findCustomerByUsername(username);

        Room room = roomRepository.findById(roomId).get();
        int countSlot = slotBooking.size();
        float totalPrice = room.getPrice() * countSlot;

        // get time slot customer booked
        List<TimeSlot> timeSlots = new ArrayList<>();
        for (int i = 0; i < countSlot; i++){
            TimeSlot timeSlot = timeSlotRepository.findById(slotBooking.get(i)).get();
            timeSlots.add(timeSlot);
        }

        OrderBooking orderBooking = new OrderBooking();
        orderBooking.setBookingId(Helper.generateRandomString(0,5));
        orderBooking.setCustomer(customer);
        orderBooking.setRoom(room);
        orderBooking.setCheckinDate(checkinDate);
        orderBooking.setTotalPrice(totalPrice);
        orderBooking.setSlot(timeSlots);
        orderBooking.setCreateAt(Helper.convertLocalDateTime());
        orderBooking.setNote(note);
        OrderBooking result = orderBookingRepository.save(orderBooking);      // saved

//        OrderBookingResponse orderBookingResponse = new OrderBookingResponse();
//        orderBookingResponse.setBookingId(orderBooking.getBookingId());
//        orderBookingResponse.setCustomerId(customer.getUserId());
//        orderBookingResponse.setRoomId(room.getRoomId());
//        orderBookingResponse.setSlotId(slotBooking);
//        orderBookingResponse.setCheckinDate(orderBooking.getCheckinDate());
//        orderBookingResponse.setTotalPrice(orderBooking.getTotalPrice());
//        orderBookingResponse.setNote(orderBooking.getNote());

        return result;
    }

    @Override
    public List<OrderBooking> getCustomerHistoryBooking(String jwttoken) {
        String userName = jwtService.extractUsername(jwttoken);
        List<OrderBooking> historyBookingList = orderBookingRepository.getCustomerHistoryBooking(userName);
//        List<OrderBookingResponse> bookingResponsesList = new ArrayList<>();
//        for (OrderBooking orderBooking : historyBookingList){
//            OrderBookingResponse orderBookingResponse = new OrderBookingResponse();
//            orderBookingResponse.setBookingId(orderBooking.getBookingId());
//            orderBookingResponse.setRoomId(orderBooking.getRoom().getRoomId());
//            orderBookingResponse.setCheckinDate(orderBooking.getCheckinDate());
//            orderBookingResponse.setTotalPrice(orderBooking.getTotalPrice());
//            orderBookingResponse.setStatus("FINISHED");
//
//            // Get all timeslot in Booking
//            List<Integer> timeSlotIdBooked = new ArrayList<>();
//            int countSlot = orderBooking.getSlot().size();
//            for (int i = 0; i < countSlot; i++){
//                timeSlotIdBooked.add(orderBooking.getSlot().get(i).getTimeSlotId());
//            }
//            orderBookingResponse.setSlotId(timeSlotIdBooked);
//            bookingResponsesList.add(orderBookingResponse);

        return historyBookingList;
    }


}
