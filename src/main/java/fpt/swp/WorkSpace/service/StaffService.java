package fpt.swp.WorkSpace.service;

import fpt.swp.WorkSpace.models.Staff;
import fpt.swp.WorkSpace.repository.BuildingRepository;
import fpt.swp.WorkSpace.repository.StaffRepository;
import fpt.swp.WorkSpace.repository.UserRepo;
import fpt.swp.WorkSpace.response.StaffRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserRepo userRepository;

    public Staff createStaff(StaffRequest request) {
        boolean buildingExists = buildingRepository.existsById(request.getBuildingId());
        if (!buildingExists) {
            throw new RuntimeException("Building not found");
        }

        boolean userExists = userRepository.existsById(request.getUserId());
        if (!userExists) {
            throw new RuntimeException("User not found");
        }
        Staff staff = new Staff();
        staff.setStaffId(request.getStaffId());
        staff.setFullName(request.getFullName());
        staff.setPhoneNumber(request.getPhoneNumber());
        staff.setDateOfBirth(request.getDateOfBirth());
        staff.setCreateAt(LocalDateTime.now());
        staff.setEmail(request.getEmail());
        staff.setWorkShift(request.getWorkShift());
        staff.setWorkDays(request.getWorkDays());
        staff.setUserId(request.getUserId());
        staff.setBuildingId(request.getBuildingId());
        return staffRepository.save(staff);
    }

    public Page<Staff> getAllStaffs(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return staffRepository.findAll(pageable);
    }

    public Staff getStaffById(String staffId) {
        return staffRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found"));
    }

    public Staff updateStaff(String staffId, StaffRequest request) {
        Staff existedStaff = staffRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        if(request.getFullName() != null){
            existedStaff.setFullName(request.getFullName());
        }
        if(request.getPhoneNumber() != null){
            existedStaff.setPhoneNumber(request.getPhoneNumber());
        }
        if (request.getDateOfBirth() != null) {
            existedStaff.setDateOfBirth(request.getDateOfBirth());
        }
        if(request.getEmail() != null){
            existedStaff.setEmail(request.getEmail());
        }
        if(request.getWorkShift() != null){
            existedStaff.setWorkShift(request.getWorkShift());
        }
        if(request.getWorkDays() != null){
            existedStaff.setWorkDays(request.getWorkDays());
        }
        if(request.getUserId() != null){
            existedStaff.setUserId(request.getUserId());
        }
        if(request.getBuildingId() != null){
            existedStaff.setBuildingId(request.getBuildingId());
        }
        return staffRepository.save(existedStaff);
    }

    public void deleteStaff(String staffId) {
        if (!staffRepository.existsById(staffId)) {
            throw new RuntimeException("Staff not found");
        }
        staffRepository.deleteById(staffId);
    }
}
