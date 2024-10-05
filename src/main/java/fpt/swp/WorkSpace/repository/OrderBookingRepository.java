package fpt.swp.WorkSpace.repository;

import fpt.swp.WorkSpace.models.OrderBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface OrderBookingRepository extends JpaRepository<OrderBooking, String> {
    @Query("SELECT b FROM OrderBooking b where ( b.checkinDate = ?1 and b.room.roomId = ?2) ")
    List<OrderBooking> getTimeSlotBookedByRoomAndDate(Date checkinDate, int roomId );
}
