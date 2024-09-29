package fpt.swp.WorkSpace.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Entity
@Table(name = "room")
@Data
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;

    private String roomName;
    private float price;
    private int quantity;
    private String roomImg;
    private String status;

    @ManyToOne
    @JoinColumn(name = "buildingId")
    @JsonBackReference // Ngăn ngừa vòng lặp
    private Building building;

    @ManyToOne
    @JoinColumn(name = "roomTypeID")
    @JsonBackReference // Ngăn ngừa vòng lặp
    private RoomType roomType;




}
