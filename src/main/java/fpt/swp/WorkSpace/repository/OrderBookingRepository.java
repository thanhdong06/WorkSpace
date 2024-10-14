package fpt.swp.WorkSpace.repository;

import fpt.swp.WorkSpace.models.OrderBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface OrderBookingRepository extends JpaRepository<OrderBooking, String> {
//    @Query("SELECT b FROM OrderBooking b where ( b.checkinDate = ?1 and b.room.roomId = ?2) ")
//    List<OrderBooking> getTimeSlotBookedByRoomAndDate(String checkinDate, String roomId );
//
//    @Query("SELECT b FROM OrderBooking b where ( b.checkinDate = ?1 ) ")
//    List<OrderBooking> getTimeSlotBookedByDate(String checkinDate);

    @Query("SELECT b FROM OrderBooking b WHERE (b.checkinDate <= ?1 AND b.checkoutDate >= ?1) AND (b.room.roomId = ?2)")
    List<OrderBooking> findTimeSlotBookedByRoomAndDate(String checkinDate, String roomId );

    // check date between check in and check out date
    @Query("SELECT b FROM OrderBooking b WHERE  b.checkinDate <= ?1 AND b.checkoutDate >= ?1 ")
    List<OrderBooking> findBookingsByDate(String booking);

    @Query("SELECT b FROM OrderBooking b where (b.customer.user.userName = ?1) ")
    List<OrderBooking> getCustomerHistoryBooking(String username );

}
