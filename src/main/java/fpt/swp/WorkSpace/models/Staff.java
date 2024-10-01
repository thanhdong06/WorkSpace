package fpt.swp.WorkSpace.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "staff")
@NoArgsConstructor
@Getter
@Setter
public class Staff {
    @Id
    @Column(name = "staff_id", length = 30, nullable = false)
    private String staffId;

    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    @Column(name = "email", length = 45, nullable = false)
    private String email;

    @Column(name = "work_shift", length = 30, nullable = false)
    private String workShift;

    @Column(name = "work_days", length = 100, nullable = false)
    private String workDays;

    @Column(name = "building_WsID")
    private String buildingId;

    @Column(name = "user_UserID")
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StaffStatus status;
}
