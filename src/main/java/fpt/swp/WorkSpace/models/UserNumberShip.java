package fpt.swp.WorkSpace.models;

import jakarta.persistence.*;

@Entity
@Table(name = "membership")
public class UserNumberShip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int membershipID;

    @Column(name = "membership_name")
    private String membershipName;


    private double discount;


    private String utilities;

    @OneToOne(mappedBy = "membership")
    private AppUser appUser;

}
