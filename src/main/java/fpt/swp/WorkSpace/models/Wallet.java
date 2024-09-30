package fpt.swp.WorkSpace.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "wallet")
@NoArgsConstructor
@Getter
@Setter
public class Wallet {
    @Id
    @Column(name = "walletId", length = 36, nullable = false)
    private String walletId;

    @Column(name = "Amount",nullable = false)
    private int amount;

    @OneToOne(mappedBy = "wallet")
    private Customer customer;
}
