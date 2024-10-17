package fpt.swp.WorkSpace.DTO;

import com.fasterxml.jackson.annotation.*;

import lombok.Data;



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



}
