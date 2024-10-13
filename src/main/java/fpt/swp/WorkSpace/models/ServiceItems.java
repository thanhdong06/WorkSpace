package fpt.swp.WorkSpace.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "serviceitems")
@Data
public class ServiceItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serviceId;

    private String serviceName;
    private Float price;
    private Integer quantity;
    private String serviceType;

    private String serviceImg;

     @Enumerated(EnumType.STRING)
    private TimeSlotStatus status = TimeSlotStatus.AVAILABLE;

    @OneToMany(mappedBy = "service")
    private List<OrderBookingDetail> orderBookingDetails;

}
