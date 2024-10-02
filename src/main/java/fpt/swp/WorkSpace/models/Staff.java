package fpt.swp.WorkSpace.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "Staff")
@Data

public class Staff {
    @Id
    @Column(name = "staffId", length = 30, nullable = false)
    private String StaffId;

    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "Create_at", nullable = false)
    private LocalDateTime createAt;

    @Column(name = "Email", length = 50, nullable = false)
    private String email;

    @Column(name = "building_WsID")
    private String buildingId;

    @Column(name = "user_UserID")
    private String userId;

    private String workday;

    private String workshift;

    private String status;


}
