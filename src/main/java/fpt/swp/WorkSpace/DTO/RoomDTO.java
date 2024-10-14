package fpt.swp.WorkSpace.DTO;

import com.fasterxml.jackson.annotation.*;
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
    private String[] roomImg;
    private String description;
    private String building;
    private String roomType;

    @JsonProperty("price")
    public Float getPrice() {
        return price != 0 ? price : null;
    }

}
