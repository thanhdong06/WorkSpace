package fpt.swp.WorkSpace.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.hibernate.mapping.Join;
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

    private String creationTime;

    private String roomImg;

    @Column(name = "staff_at_room", nullable = false)
    private String staffAtRoom;

    private String description;

    private String status;

    @ManyToOne
    @JoinColumn(name = "buildingId")
    @JsonManagedReference// Ngăn ngừa vòng lặp
    private Building building;

    @ManyToOne
    @JoinColumn(name = "roomTypeID")
    @JsonManagedReference// Ngăn ngừa vòng lặp
    private RoomType roomType;

    @OneToMany(mappedBy = "room")
    @JsonIgnore
    private List<OrderBooking> bookingList; ;




}
