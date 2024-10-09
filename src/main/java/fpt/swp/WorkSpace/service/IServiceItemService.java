package fpt.swp.WorkSpace.service;

import fpt.swp.WorkSpace.DTO.ServiceItemsDTO;
import fpt.swp.WorkSpace.models.ServiceItems;

import java.util.List;

public interface IServiceItemService {
    List<ServiceItemsDTO> getAllRoomService();
    ServiceItems addItem(ServiceItems items);
}
