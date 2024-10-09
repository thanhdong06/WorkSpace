package fpt.swp.WorkSpace.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomDTO {
    private int roomId;
    private String roomName;
    private float price;
    private String creationTime;
    private String roomImg;
    private String description;
    private String status;
}
