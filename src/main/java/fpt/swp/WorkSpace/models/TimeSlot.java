package fpt.swp.WorkSpace.models;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "timeslot")
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int timeSlotId;
    private Date startTime;
    private Date endTime;
    private int timeSlot;
}
