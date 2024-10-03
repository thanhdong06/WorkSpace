package fpt.swp.WorkSpace.controller;

import fpt.swp.WorkSpace.models.Staff;
import fpt.swp.WorkSpace.response.APIResponse;
import fpt.swp.WorkSpace.response.StaffRequest;
import fpt.swp.WorkSpace.response.StaffResponse;
import fpt.swp.WorkSpace.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/staffs")
public class StaffController {
    @Autowired
    private StaffService staffService;

    @PostMapping()
    public ResponseEntity<APIResponse<Staff>> createStaff(@RequestBody StaffRequest request) {
        try {
            Staff createStaff = staffService.createStaff(request);
            APIResponse<Staff> response = new APIResponse<>("Staff create successfully", createStaff);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (RuntimeException e) {
            APIResponse<Staff> response = new APIResponse<>(e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping
    public Page<StaffResponse> getAllStaffs(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return staffService.getAllStaffs(page, size);
    }

    @GetMapping("/{id}")
    public StaffResponse getStaffById(@PathVariable String id) {
        return staffService.getStaffById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Staff>> updateManager(@PathVariable String id,@RequestBody StaffRequest request) {
        try {
            Staff updateStaff = staffService.updateStaff(id, request);
            APIResponse<Staff> response = new APIResponse<>("Staff updated successfully", updateStaff);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (RuntimeException e) {
            APIResponse<Staff> response = new APIResponse<>(e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteStaff(@PathVariable String id) {
        try {
            staffService.deleteStaff(id);
            APIResponse<Void> response = new APIResponse<>("Staff deleted successfully", null);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (RuntimeException e) {
            APIResponse<Void> response = new APIResponse<>(e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
