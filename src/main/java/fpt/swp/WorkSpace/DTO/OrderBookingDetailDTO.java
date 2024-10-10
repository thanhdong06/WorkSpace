package fpt.swp.WorkSpace.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import fpt.swp.WorkSpace.models.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class OrderBookingDetailDTO {

        private String bookingId;
        private String roomId;
        private List<Integer> slotId;
        private String customerId;
        private String createAt;
        private String checkinDate;
        private float totalPrice;
        private String note;
        private String status;
        private Map<String, Integer> serviceItems;


    }
