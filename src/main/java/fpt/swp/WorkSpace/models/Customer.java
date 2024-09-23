package fpt.swp.WorkSpace.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

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
    private User user;

    @Column(nullable = false)
    private String fullName;

    private String phoneNumber;
    private String email;
    private String dateOfBirth;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "membershipID", referencedColumnName = "membershipID")
    private UserNumberShip membership;






}
