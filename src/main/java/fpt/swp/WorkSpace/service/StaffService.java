package fpt.swp.WorkSpace.service;

import fpt.swp.WorkSpace.models.Staff;
import fpt.swp.WorkSpace.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService implements IStaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public List<Staff> getAllStaff() {
        List<Staff> staffList = staffRepository.findAll();
        if (staffList.isEmpty()) {
            throw new RuntimeException("No staff found");
        }
        return staffList;
    }
}
