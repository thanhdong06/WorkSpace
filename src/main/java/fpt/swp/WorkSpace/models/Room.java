package fpt.swp.WorkSpace.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "room")
public class Room {

    @Id
    private String roomId;
    private String roomName;
    private float price;
    private int quantity;

}
