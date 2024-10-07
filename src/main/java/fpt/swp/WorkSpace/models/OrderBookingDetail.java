package fpt.swp.WorkSpace.models;

import jakarta.persistence.*;

@Entity
@Table(name = "orderbookingdetail")
public class OrderBookingDetail {
    @Id
    private Integer bookingDetailId;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private OrderBooking booking;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Items service;

    private Integer bookingServiceQuantity;
    private Float bookingServicePrice;


}
