package fpt.swp.WorkSpace.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "roomtype")
public class RoomType {

    @Id
    private int id;
    private String roomTypeName;

    @OneToMany

    private List<Room> rooms;



}
