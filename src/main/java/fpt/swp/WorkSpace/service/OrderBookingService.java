package fpt.swp.WorkSpace.service;

import fpt.swp.WorkSpace.DTO.CustomerServiceDTO;
import fpt.swp.WorkSpace.DTO.OrderBookingDetailDTO;
import fpt.swp.WorkSpace.models.*;
import fpt.swp.WorkSpace.repository.*;
import fpt.swp.WorkSpace.response.OrderBookingResponse;
import fpt.swp.WorkSpace.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Service
public class OrderBookingService  implements IOrderBookingService {
    @Autowired
    private OrderBookingRepository orderBookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ServiceItemsRepository itemsRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Autowired
    private OrderBookingDetailRepository orderBookingDetailRepository;

    @Autowired
    private ServiceItemsRepository serviceItemsRepository;


    @Override
    public List<OrderBookingDetailDTO> getBookedSlotByRoomAndDate(String date, String roomId) {
        // get booking list checkin day and room avaiable
        List<OrderBooking> bookings = orderBookingRepository.findTimeSlotBookedByRoomAndDate( date, roomId);


        List<OrderBookingDetailDTO> bookingList = new ArrayList<>();

        if ( bookings.isEmpty()){
            throw new RuntimeException("Ngay " + date + " chua co booking nao.");
        }
        for (OrderBooking orderBooking : bookings){
            OrderBookingDetailDTO dto = new OrderBookingDetailDTO();
            dto.setBookingId(orderBooking.getBookingId());
            dto.setRoomId(orderBooking.getRoom().getRoomId());
            dto.setCheckinDate(orderBooking.getCheckinDate());
            dto.setCheckoutDate(orderBooking.getCheckoutDate());


            // Get all timeslot in Booking
            List<TimeSlot> timeSlotIdBooked = new ArrayList<>();
            int countSlot = orderBooking.getSlot().size();
            for (int i = 0; i < countSlot; i++){
                timeSlotIdBooked.add(orderBooking.getSlot().get(i));
            }
            dto.setSlots(timeSlotIdBooked);
            bookingList.add(dto);
        }
        return bookingList ;
    }

    @Override
    public List<OrderBookingDetailDTO> getBookedSlotByDate(String date) {
        // get booking list checkin day and room avaiable
        List<OrderBooking> bookings = orderBookingRepository.findBookingsByDate(date);


        List<OrderBookingDetailDTO> bookingList = new ArrayList<>();

        if ( bookings.isEmpty()){
            throw new RuntimeException("Ngay " + date + " chua co booking nao.");
        }
        for (OrderBooking orderBooking : bookings){
            OrderBookingDetailDTO dto = new OrderBookingDetailDTO();
            dto.setBookingId(orderBooking.getBookingId());
            dto.setRoomId(orderBooking.getRoom().getRoomId());
            dto.setCheckinDate(orderBooking.getCheckinDate());
            dto.setCheckoutDate(orderBooking.getCheckoutDate());

            // Get all timeslot in Booking
            List<TimeSlot> timeSlotIdBooked = new ArrayList<>();
            int countSlot = orderBooking.getSlot().size();
            for (int i = 0; i < countSlot; i++){
                timeSlotIdBooked.add(orderBooking.getSlot().get(i));
            }
            dto.setSlots(timeSlotIdBooked);
            bookingList.add(dto);
        }
        return bookingList ;
    }

    @Override
    public List<OrderBookingDetailDTO> getBookedSlotByCheckinAndCheckout(String checkin, String checkout, String roomId) {
        // get booking list checkin day and room avaiable
        List<OrderBooking> bookings = orderBookingRepository.findBookingsByInOutDate(checkin, checkout);


        List<OrderBookingDetailDTO> bookingList = new ArrayList<>();

        if ( bookings.isEmpty()){
            throw new RuntimeException("Tu ngay " + checkin +" toi ngay " + checkout  + " chua co booking nao.");
        }
        for (OrderBooking orderBooking : bookings){
            OrderBookingDetailDTO dto = new OrderBookingDetailDTO();
            dto.setBookingId(orderBooking.getBookingId());
            dto.setRoomId(orderBooking.getRoom().getRoomId());
            dto.setCheckinDate(orderBooking.getCheckinDate());
            dto.setCheckoutDate(orderBooking.getCheckoutDate());
            dto.setTotalPrice(orderBooking.getTotalPrice());

            // Get all timeslot in Booking
            List<TimeSlot> timeSlotIdBooked = new ArrayList<>();
            int countSlot = orderBooking.getSlot().size();
            for (int i = 0; i < countSlot; i++){
                timeSlotIdBooked.add(orderBooking.getSlot().get(i));
            }
            dto.setSlots(timeSlotIdBooked);
            bookingList.add(dto);
        }
        return bookingList ;
    }


    @Override
    public OrderBooking createOrderBooking(String jwttoken, String roomId, String checkinDate, List<Integer> slotBooking, String note) {

        String username = jwtService.extractUsername(jwttoken);
        Customer customer =  customerRepository.findCustomerByUsername(username);

        Room room = roomRepository.findById(roomId).get();
        int countSlot = slotBooking.size();
        float totalPrice = room.getPrice() * countSlot;

        // get time slot booked by customer
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
    public OrderBooking createMultiOrderBooking(String jwttoken, String buildingId, String roomId, String checkin, String checkout, List<Integer> slotBooking, MultiValueMap<Integer, Integer> items, String note) {
        String username = jwtService.extractUsername(jwttoken);
        Customer customer = customerRepository.findCustomerByUsername(username);

        Building building = buildingRepository.findById(buildingId).get();

        Room room = roomRepository.findById(roomId).get();


        // get time slot booked by customer
        int countSlot = slotBooking.size();
        List<TimeSlot> timeSlots = new ArrayList<>();
        for (int i = 0; i < countSlot; i++){
            TimeSlot timeSlot = timeSlotRepository.findById(slotBooking.get(i)).get();
            timeSlots.add(timeSlot);
        }

        // process total price
        LocalDate checkinDate = LocalDate.parse(checkin);
        LocalDate checkoutDate = LocalDate.parse(checkout);
        //days between checkin - checkout
        long numberDays = ChronoUnit.DAYS.between(checkinDate, checkoutDate) + 1;
        System.out.println(numberDays);
        float totalPrice = room.getPrice() * countSlot * (int) numberDays;

        OrderBooking orderBooking = new OrderBooking();
        orderBooking.setBookingId(Helper.generateRandomString(0, 5));
        orderBooking.setCustomer(customer);
        orderBooking.setRoom(room);
        orderBooking.setBuilding(building);
        orderBooking.setCheckinDate(checkin);
        orderBooking.setCheckoutDate(checkout);
        orderBooking.setTotalPrice(totalPrice);
        orderBooking.setSlot(timeSlots);
        orderBooking.setCreateAt(Helper.convertLocalDateTime());
        orderBooking.setNote(note);
        OrderBooking result = orderBookingRepository.save(orderBooking);

        if (!items.isEmpty()) {
            // Xử lý items (service id và số lượng)
            for (Map.Entry<Integer, List<Integer>> entry : items.entrySet()) {
                Integer serviceId = entry.getKey();
                List<Integer> quantities = entry.getValue();  // Danh sách số lượng cho cùng một service ID

                // Tìm service tương ứng từ database
                ServiceItems item = itemsRepository.findById(serviceId).orElseThrow(() -> new RuntimeException("Service not found"));

                // Lưu thông tin chi tiết đơn hàng cho từng số lượng
                for (Integer quantity : quantities) {
                    float servicePrice = item.getPrice() * quantity * (int) numberDays;
                    OrderBookingDetail orderBookingDetail = new OrderBookingDetail();
                    orderBookingDetail.setBooking(orderBooking);
                    orderBookingDetail.setService(item);
                    orderBookingDetail.setBookingServiceQuantity(quantity);
                    orderBookingDetail.setBookingServicePrice(servicePrice);
                    totalPrice += servicePrice  ;
                    orderBookingDetailRepository.save(orderBookingDetail);
                }

                orderBooking.setTotalPrice(totalPrice);
                orderBookingRepository.save(orderBooking);
            }

        }
        return result;
    }

    @Override
    public OrderBooking createOrderBookingWithout(String jwttoken, String buildingId, String roomId, String checkin, String checkout, Integer[] slotBooking, String note) {
        String username = jwtService.extractUsername(jwttoken);
        Customer customer = customerRepository.findCustomerByUsername(username);

        Building building = buildingRepository.findById(buildingId).get();

        Room room = roomRepository.findById(roomId).get();


        // get time slot booked by customer
        int countSlot = slotBooking.length;
        List<TimeSlot> timeSlots = new ArrayList<>();
        for (int i = 0; i < countSlot; i++){
            TimeSlot timeSlot = timeSlotRepository.findById(slotBooking[i]).get();
            timeSlots.add(timeSlot);
        }

        // process total price
        LocalDate checkinDate = LocalDate.parse(checkin);
        LocalDate checkoutDate = LocalDate.parse(checkout);
        //days between checkin - checkout
        long numberDays = ChronoUnit.DAYS.between(checkinDate, checkoutDate) + 1;
        System.out.println(numberDays);
        float totalPrice = room.getPrice() * countSlot * (int) numberDays;

        OrderBooking orderBooking = new OrderBooking();
        orderBooking.setBookingId(Helper.generateRandomString(0, 5));
        orderBooking.setCustomer(customer);
        orderBooking.setRoom(room);
        orderBooking.setBuilding(building);
        orderBooking.setCheckinDate(checkin);
        orderBooking.setCheckoutDate(checkout);
        orderBooking.setTotalPrice(totalPrice);
        orderBooking.setSlot(timeSlots);
        orderBooking.setCreateAt(Helper.convertLocalDateTime());
        orderBooking.setNote(note);
        OrderBooking result = orderBookingRepository.save(orderBooking);

        return result;
    }

    @Override
    public List<OrderBookingDetailDTO> getCustomerHistoryBooking(String jwttoken) {
        String userName = jwtService.extractUsername(jwttoken);
        List<OrderBooking> historyBookingList = orderBookingRepository.getCustomerHistoryBooking(userName);
        List<OrderBookingDetailDTO> orderDetail = new ArrayList<>();
        for (OrderBooking orderBooking : historyBookingList){
            OrderBookingDetailDTO dto = new OrderBookingDetailDTO();
            dto.setBookingId(orderBooking.getBookingId());
            dto.setRoomId(orderBooking.getRoom().getRoomId());
            dto.setCheckinDate(orderBooking.getCheckinDate());
            dto.setCheckoutDate(orderBooking.getCheckoutDate());
            dto.setTotalPrice(orderBooking.getTotalPrice());
            dto.setStatus("FINISHED");

            // Get all timeslot in Booking
            List<TimeSlot> timeSlotIdBooked = new ArrayList<>();
            int countSlot = orderBooking.getSlot().size();
            for (int i = 0; i < countSlot; i++){
                timeSlotIdBooked.add(orderBooking.getSlot().get(i));
            }
            dto.setSlots(timeSlotIdBooked);


            // get service items
           List<OrderBookingDetail> bookingDetails = orderBookingDetailRepository.findDetailByBookingId(orderBooking.getBookingId());
           Map<String, Integer> serviceList = new HashMap<>();
           for (OrderBookingDetail bookingDetail : bookingDetails){
               String serviceName = bookingDetail.getService().getServiceName();
               int quantity = bookingDetail.getBookingServiceQuantity();
               serviceList.put(serviceName, quantity);
           }
            dto.setServiceItems(serviceList);
            orderDetail.add(dto);
    }
        return orderDetail;
    }

    @Override
    public OrderBooking createOrderBookingService(String jwttoken, String roomId, String checkinDate, List<Integer> slotBooking, MultiValueMap<Integer, Integer> items, String note) {
        String username = jwtService.extractUsername(jwttoken);
        Customer customer =  customerRepository.findCustomerByUsername(username);
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));

        int countSlot = slotBooking.size();
        float totalPrice = room.getPrice() * countSlot;

        // get time slot booked by customer
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
        orderBooking.setCheckoutDate(checkinDate);
        orderBooking.setTotalPrice(totalPrice);
        orderBooking.setSlot(timeSlots);
        orderBooking.setCreateAt(Helper.convertLocalDateTime());
        orderBooking.setNote(note);
        orderBookingRepository.save(orderBooking);   // save to table order booking


        if (!items.isEmpty()){
            // Xử lý items (service id và số lượng)
            for (Map.Entry<Integer, List<Integer>> entry : items.entrySet()) {
                Integer serviceId = entry.getKey();
                List<Integer> quantities = entry.getValue();  // Danh sách số lượng cho cùng một service ID

                // Tìm service tương ứng từ database
                ServiceItems item = itemsRepository.findById(serviceId).orElseThrow(() -> new RuntimeException("Service not found"));

                // Lưu thông tin chi tiết đơn hàng cho từng số lượng
                for (Integer quantity : quantities) {
                    float servicePrice = item.getPrice() * quantity;
                    OrderBookingDetail orderBookingDetail = new OrderBookingDetail();
                    orderBookingDetail.setBooking(orderBooking);
                    orderBookingDetail.setService(item);
                    orderBookingDetail.setBookingServiceQuantity(quantity);
                    orderBookingDetail.setBookingServicePrice(servicePrice);
                    totalPrice += servicePrice;
                    orderBookingDetailRepository.save(orderBookingDetail);
                }
            }
            orderBooking.setTotalPrice(totalPrice);
            orderBookingRepository.save(orderBooking);
        }

        return orderBooking;
    }


    @Override
    public void updateServiceBooking(String orderBookingId, MultiValueMap<Integer, Integer> items) {
        // get booking
        OrderBooking orderBooking = orderBookingRepository.findById(orderBookingId).orElseThrow();
        int countSlot = orderBooking.getSlot().size();
        float totalPrice = orderBooking.getRoom().getPrice() * countSlot;


        if (!items.isEmpty()) {
            // Xử lý items (service id và số lượng)
            for (Map.Entry<Integer, List<Integer>> entry : items.entrySet()) {
                Integer serviceId = entry.getKey();
                Integer quantities = entry.getValue().get(0);

                OrderBookingDetail orderBookingDetail = orderBookingDetailRepository.findDetailByBookingIdAndServiceId(orderBooking.getBookingId(), serviceId);

                    // If orderBookingDetail already exist
                if (orderBookingDetail != null) {
                    // update quantity in table
                    orderBookingDetail.setBookingServiceQuantity(quantities);
                    float servicePrice = orderBookingDetail.getService().getPrice() * quantities;
                    orderBookingDetail.setBookingServicePrice(servicePrice);
                    totalPrice += servicePrice;
                    orderBookingDetailRepository.save(orderBookingDetail);
                } else {
                    // Add new service
                    OrderBookingDetail neworderBookingDetail = new OrderBookingDetail();
                    ServiceItems item = serviceItemsRepository.findById(serviceId).orElseThrow(() -> new RuntimeException("Service not found"));
                    neworderBookingDetail.setBooking(orderBooking);
                    neworderBookingDetail.setService(item);
                    neworderBookingDetail.setBookingServiceQuantity(quantities);
                    float servicePrice = item.getPrice() * quantities;
                    neworderBookingDetail.setBookingServicePrice(servicePrice);
                    totalPrice += servicePrice;
                    orderBookingDetailRepository.save(neworderBookingDetail);
                }
            }
        }
        orderBooking.setTotalPrice(totalPrice);
        orderBookingRepository.save(orderBooking);
    }

    @Override
    public CustomerServiceDTO getCustomerService(String orderBookingId) {
        CustomerServiceDTO dto = new CustomerServiceDTO();

        // get detail by booking id
        List<OrderBookingDetail> bookingDetails = orderBookingDetailRepository.findDetailByBookingId(orderBookingId);

        Map<String, Integer> serviceList = new HashMap<>();
        for (OrderBookingDetail bookingDetail : bookingDetails){
            String serviceName = bookingDetail.getService().getServiceName();
            int quantity = bookingDetail.getBookingServiceQuantity();

            // add key - value to map
            serviceList.put(serviceName, quantity);
            // o cam: 1
            // may chieu: 2
        }
        dto.setServiceItems(serviceList);
        return dto;
    }


}
