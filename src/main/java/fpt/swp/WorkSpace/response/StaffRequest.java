package fpt.swp.WorkSpace.response;

import fpt.swp.WorkSpace.models.StaffStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffRequest {
    private String staffId;
    private String fullName;
    private String phoneNumber;
    private Date dateOfBirth;
    private LocalDateTime createAt;
    private String email;
    private String workShift;
    private String workDays;

    private String buildingId;
    private String userId;
    private StaffStatus status;
}
