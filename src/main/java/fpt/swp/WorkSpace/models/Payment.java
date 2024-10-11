package fpt.swp.WorkSpace.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
public class Payment {
    @Id
    @Column(name = "payment_id", length = 36, nullable = false)
    private String paymentId;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private String status;  // pending, completed, failed

    @Column(name = "payment_method", length = 50)
    private String paymentMethod;

    @Column(name = "orderbooking_OBID", length = 10)
    private String orderBookingId;

    @Column(name = "transaction_id", length = 45)
    private String transactionId;
}
