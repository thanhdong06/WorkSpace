package fpt.swp.WorkSpace.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "building")
@Data
public class Building {

    @Id
    @Column(name = "buildingId", updatable = false, nullable = false, length = 36)
    private String buildingId;

    @Column(name = "building_name", length = 50, nullable = false)
    private String buildingName;

    @Column(name = "location", length = 50, nullable = false)
    private String buildingLocation;

    @Column(name = "phone_contact", length = 50, nullable = false)
    private String phoneContact;

    @OneToMany(mappedBy = "building")
    private List<Room> rooms;


}
