package fpt.swp.WorkSpace.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "orderbooking")
@Data
public class OrderBooking {

        @Id
        private String bookingId;


        @ManyToOne
        @JoinColumn(name = "room_id")
        @JsonManagedReference
        private Room room;

        @ManyToMany
        @JoinTable(name = "OrderBooking_TimeSlot",
                joinColumns = @JoinColumn(name = "booking_id"),
                inverseJoinColumns = @JoinColumn(name = "timeslot_id"))
        private List<TimeSlot> slot;


        @ManyToOne
        @JoinColumn(name = "customer_id" )
        @JsonManagedReference
        private Customer customer;

        private String createAt;

        @Column(nullable = false)
        private Date checkinDate;

        @Column(nullable = false)
        private float totalPrice;

        private String note;
}
