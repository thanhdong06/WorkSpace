package fpt.swp.WorkSpace.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Items")
@Data
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serviceId;

    private String serviceName;
    private Float price;
    private Integer quantity;
    private String serviceType;

     @Enumerated(EnumType.STRING)
    private TimeSlotStatus status = TimeSlotStatus.AVAILABLE;

    @OneToMany(mappedBy = "service")
    private List<OrderBookingDetail> orderBookingDetails;

}
