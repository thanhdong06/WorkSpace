package fpt.swp.WorkSpace.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@NoArgsConstructor
public class Transaction {
    @Id
    @Column(name = "transaction_id", length = 36, nullable = false)
    private String transactionId;

    @Column(nullable = false)
    private float amount;

    @Column(nullable = false)
    private String status;  // completed, failed, pending

    @ManyToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "payment_id")
    private Payment payment;
}
