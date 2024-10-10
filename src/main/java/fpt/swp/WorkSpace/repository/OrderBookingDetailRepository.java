package fpt.swp.WorkSpace.repository;

import fpt.swp.WorkSpace.models.OrderBookingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface OrderBookingDetailRepository extends JpaRepository<OrderBookingDetail, Integer> {

    @Query("select b from OrderBookingDetail b where b.booking.bookingId = ?1")
    List<OrderBookingDetail> findDetailByBookingId(String bookingId);
}
