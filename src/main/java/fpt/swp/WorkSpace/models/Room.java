package fpt.swp.WorkSpace.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
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
    private LocalDateTime creationTime;
    private String roomImg;
    private String status;

    @ManyToOne
    @JoinColumn(name = "buildingId")
    @JsonBackReference // Ngăn ngừa vòng lặp
    private Building building;

    @ManyToOne
    @JoinColumn(name = "roomTypeID")
    @JsonManagedReference // Ngăn ngừa vòng lặp
    private RoomType roomType;




}
