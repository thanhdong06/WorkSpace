package fpt.swp.WorkSpace.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ServiceItemsDTO {
    private int serviceId;
    private String serviceName;
    private String[] serviceImg;
    private String price;
    private int quantity;
    private String serviceType;
}
