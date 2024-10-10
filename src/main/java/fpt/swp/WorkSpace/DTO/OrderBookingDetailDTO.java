package fpt.swp.WorkSpace.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import fpt.swp.WorkSpace.models.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.List;

public class OrderBookingDetailDTO {
    private int bookingDetailId;
    private OrderBooking booking;
    private ServiceItems service;
    private int bookingServiceQuantity;
    private float bookingServicePrice;
}
