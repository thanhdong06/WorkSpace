package fpt.swp.WorkSpace.controller;

import fpt.swp.WorkSpace.auth.AuthenticationResponse;
import fpt.swp.WorkSpace.auth.RegisterRequest;
import fpt.swp.WorkSpace.models.Staff;
import fpt.swp.WorkSpace.response.APIResponse;
import fpt.swp.WorkSpace.response.StaffRequest;
import fpt.swp.WorkSpace.response.StaffResponse;
import fpt.swp.WorkSpace.response.UpdateStaffRequest;
import fpt.swp.WorkSpace.service.StaffService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/staffs")
public class
StaffController {
    @Autowired
    private StaffService staffService;

    @PostMapping()
    public ResponseEntity<AuthenticationResponse> createStaff(@Valid @RequestBody StaffRequest request) {
        AuthenticationResponse response = staffService.createStaff(request);
        if (response.getStatusCode() == 400) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return ResponseEntity.ok(response);
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
    public ResponseEntity<APIResponse<Staff>> updateManager(@PathVariable String id,@RequestBody UpdateStaffRequest request) {
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
