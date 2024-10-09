package fpt.swp.WorkSpace.controller;

import fpt.swp.WorkSpace.repository.ServiceItemsRepository;
import fpt.swp.WorkSpace.response.ResponseHandler;
import fpt.swp.WorkSpace.service.IServiceItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ServiceItemsController {
    @Autowired
    private IServiceItemService serviceItemService;

    @GetMapping("/get-service-items")
    public ResponseEntity<Object> getServiceItems() {
        return ResponseHandler.responseBuilder("Ok", HttpStatus.OK, serviceItemService.getAllRoomService() );
    }
    }
