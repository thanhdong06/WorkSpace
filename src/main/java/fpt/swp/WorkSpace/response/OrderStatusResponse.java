package fpt.swp.WorkSpace.response;

import fpt.swp.WorkSpace.models.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderStatusResponse {
    private String orderId;
    private BookingStatus orderStatus;
}
