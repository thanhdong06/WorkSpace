package fpt.swp.WorkSpace.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Items")
@Data
public class Items {
    @Id
    private String serviceId;

    private String serviceName;
    private Float price;
    private Integer quantity;
    private String serviceType;

     @Enumerated(EnumType.STRING)
    private Status status = Status.AVAILABLE;

    @OneToMany(mappedBy = "service")
    private List<OrderBookingDetail> orderBookingDetails;

}
