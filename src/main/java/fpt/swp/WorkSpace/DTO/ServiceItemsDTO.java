package fpt.swp.WorkSpace.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceItemsDTO {
    private int serviceId;
    private String serviceName;
    private float price;
    private int quantity;
    private String serviceType;
}
