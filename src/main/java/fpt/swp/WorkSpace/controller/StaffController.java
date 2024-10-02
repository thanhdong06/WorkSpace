package fpt.swp.WorkSpace.controller;

import fpt.swp.WorkSpace.models.Staff;
import fpt.swp.WorkSpace.response.ResponseHandler;
import fpt.swp.WorkSpace.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StaffController {

    @Autowired
    private IStaffService staffService;

    @GetMapping("/manager/get-all-staff")
    public ResponseEntity<Object>getAllStaff(){

        try {
            List<Staff> staffList = staffService.getAllStaff();
            return ResponseHandler.responseBuilder("Success", HttpStatus.OK, staffList);
        }catch (Exception e){
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
}
