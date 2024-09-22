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
import java.util.Collections;


@Entity
@Table(name = "customer")
@NoArgsConstructor
@Getter @Setter
public class User implements UserDetails  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "username", nullable = false, unique = true)
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
        return Collections.singleton(new SimpleGrantedAuthority(roleName));
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
