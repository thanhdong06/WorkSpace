package fpt.swp.WorkSpace.models;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter @Setter
public class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Column(name = "username", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false )
    private String password;

    @Column(name = "fullName", nullable = false)
    private String fullName;

    @Column(name = "phonenumber", nullable = false, length = 10)
    private String phoneNumber;

    @Column(name = "dateOfBirth", nullable = false)
    private Date dateOfBirth;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "membershipID", referencedColumnName = "membershipID")
    private UserNumberShip membership;

    @Column(name = "createdDate")
    private Date createdDate;

    private String roleName;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roleName));
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
