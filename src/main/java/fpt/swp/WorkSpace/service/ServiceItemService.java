package fpt.swp.WorkSpace.service;

import fpt.swp.WorkSpace.DTO.ServiceItemsDTO;
import fpt.swp.WorkSpace.models.ServiceItems;
import fpt.swp.WorkSpace.repository.ServiceItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceItemService implements IServiceItemService {

    @Autowired
    private ServiceItemsRepository serviceItemsRepository;

    @Override
    public List<ServiceItemsDTO> getAllRoomService() {
        List<ServiceItems> list = serviceItemsRepository.findAll();
        List<ServiceItemsDTO> serviceItemsDTOList = new ArrayList<>();
        for (ServiceItems serviceItems : list) {
            ServiceItemsDTO serviceItemsDTO = new ServiceItemsDTO();
            serviceItemsDTO.setServiceId(serviceItems.getServiceId());
            serviceItemsDTO.setServiceName(serviceItems.getServiceName());
            serviceItemsDTO.setPrice(serviceItems.getPrice());
            serviceItemsDTO.setServiceType(serviceItems.getServiceType());
            serviceItemsDTOList.add(serviceItemsDTO);
        }
        return serviceItemsDTOList;
    }

    @Override
    public ServiceItems addItem(ServiceItems items) {
        return null;

    }
}
