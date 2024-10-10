package fpt.swp.WorkSpace.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import fpt.swp.WorkSpace.models.Building;
import fpt.swp.WorkSpace.models.OrderBooking;
import fpt.swp.WorkSpace.models.RoomType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomDTO {
    private String roomId;
    private String roomName;
    private float price;
    private String creationTime;
    private String roomImg;
    private String description;
    private String status;

    private Building building;
    private RoomType roomType;
    private List<OrderBooking> bookingList; ;
}
