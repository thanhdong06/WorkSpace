package fpt.swp.WorkSpace.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "roomtype")
@Data
public class RoomType {

    @Id
    private String id;

    private String roomTypeName;

    @OneToMany(mappedBy = "roomType")
    private List<Room> rooms;







}
