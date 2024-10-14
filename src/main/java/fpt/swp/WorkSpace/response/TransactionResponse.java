package fpt.swp.WorkSpace.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionResponse {
    private String transactionId;
    private float amount;
    private String status;
    private String type;
    private String paymentId;
}
