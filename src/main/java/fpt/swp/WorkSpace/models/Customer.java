package fpt.swp.WorkSpace.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity

@NoArgsConstructor
@Table(name = "customer")
@Getter
@Setter

public class Customer  {

    @Id
    private String userId;
    @OneToOne
    @MapsId // This ensures the `userId` is shared as the primary key
    @JoinColumn(name = "user_id", referencedColumnName = "user_Id")
    @JsonIgnore
    private User user;

    @Column(nullable = false)
    private String fullName;

    private String phoneNumber;
    private String email;
    private Date dateOfBirth;
    private String roleName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "membershipID", referencedColumnName = "membershipID")
    private UserNumberShip membership;

    public String getRoleName() {
        return user.getRoleName();
    }
}
