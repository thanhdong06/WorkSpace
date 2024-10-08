package fpt.swp.WorkSpace.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "orderbookingdetail")
@Data
public class OrderBookingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingDetailId;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    @JsonBackReference
    private OrderBooking booking;

    @ManyToOne
    @JoinColumn(name = "service_id")
    @JsonBackReference
    private Items service;

    private int bookingServiceQuantity;
    private float bookingServicePrice;


}
