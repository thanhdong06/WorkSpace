package fpt.swp.WorkSpace.service;

import fpt.swp.WorkSpace.auth.AuthenticationResponse;
import fpt.swp.WorkSpace.models.OrderBooking;
import fpt.swp.WorkSpace.models.Staff;
import fpt.swp.WorkSpace.models.TimeSlot;
import fpt.swp.WorkSpace.models.User;
import fpt.swp.WorkSpace.repository.*;
import fpt.swp.WorkSpace.response.OrderBookingStaffTracking;
import fpt.swp.WorkSpace.response.StaffRequest;
import fpt.swp.WorkSpace.response.StaffResponse;
import fpt.swp.WorkSpace.response.UpdateStaffRequest;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private OrderBookingRepository orderBookingRepository;

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public AuthenticationResponse createStaff(StaffRequest request) {
        AuthenticationResponse response = new AuthenticationResponse();
        User newUser = new User();
        try {
            User findUser = repository.findByuserName(request.getUserName());
            if (findUser != null){
                throw new RuntimeException("user already exists");
            }
            newUser.setUserId(generateStaffId()); // Generate the User ID
            newUser.setUserName(request.getUserName());
            newUser.setPassword(passwordEncoder.encode(request.getPassword()));
            newUser.setCreationTime(LocalDateTime.now());
            newUser.setRoleName(request.getRole());

            // Save the User entity
            User savedUser = repository.save(newUser);

            // Create the new Staff entity
            Staff newStaff = new Staff();
            newStaff.setUser(savedUser);
            System.out.println(newStaff.getUser());

            newStaff.setCreateAt(LocalDateTime.now());
            newStaff.setBuildingId(request.getBuildingId());

            // Save the Staff entity
            staffRepository.save(newStaff);
            if (savedUser.getUserId() != null ) {
                response.setStatus("Success");
                response.setStatusCode(200);
                response.setMessage("User and Staff Saved Successfully");
                response.setData(savedUser);
            }
        }
       catch (Exception e ){
           response.setStatus("Error");
           response.setStatusCode(400);
           response.setMessage(e.getMessage());       }
        return response;
    }

    public Page<StaffResponse> getAllStaffs(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Staff> staffs = staffRepository.findAll(pageable);

        return staffs.map(staff -> {
            StaffResponse response = new StaffResponse();
            response.setUserId(staff.getUserId());
            response.setFullName(staff.getFullName());
            response.setPhoneNumber(staff.getPhoneNumber());
            response.setEmail(staff.getEmail());

            if (staff.getDateOfBirth() != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                response.setDateOfBirth(dateFormat.format(staff.getDateOfBirth()));
            }

            if (staff.getCreateAt() != null) {
                response.setCreateAt(staff.getCreateAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
            response.setWorkShift(staff.getWorkShift());
            response.setWorkDays(staff.getWorkDays());
            response.setBuildingId(staff.getBuildingId());
            response.setStatus(staff.getStatus());
            return response;
        });
    }

    public StaffResponse getStaffById(String staffId) {
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        return modelMapper.map(staff, StaffResponse.class);
    }

    public Staff updateStaff(String staffId, UpdateStaffRequest request) {
        Staff existedStaff = staffRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        if(request.getFullName() != null){
            existedStaff.setFullName(request.getFullName());
        }
        if(request.getPhoneNumber() != null ){
            existedStaff.setPhoneNumber(request.getPhoneNumber());
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
        if(request.getBuildingId() != null){
            existedStaff.setBuildingId(request.getBuildingId());
        }
        if(request.getStatus() != null){
            existedStaff.setStatus(request.getStatus());
        }
        return staffRepository.save(existedStaff);
    }

    public void deleteStaff(String userId) {
        Optional<Staff> staffOptional = staffRepository.findById(userId);
        if (staffOptional.isPresent()) {
            staffRepository.deleteById(userId);
        } else {
            throw new EntityNotFoundException("Staff with ID " + userId + " not found.");
        }    }


    public String generateStaffId() {
        // Query the latest customer and extract their ID to increment
        long latestCustomerId = staffRepository.count();
        if (latestCustomerId != 0) {

            long newId = latestCustomerId + 1;
            return "STAFF" + String.format("%04d", newId); // Format to 4 digits
        } else {
            return "STAFF0001"; // Start from CUS0001 if no customers exist
        }
    }

    public Page<OrderBookingStaffTracking> getOrderBookingsByCustomerId(String userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<OrderBooking> bookings = orderBookingRepository.findByCustomerCustomerId(userId, pageable);

        return bookings.map(booking -> {
            OrderBookingStaffTracking response = new OrderBookingStaffTracking();
            response.setCustomerId(booking.getCustomer().getUserId());
            response.setCustomerName(booking.getCustomer().getFullName());
            response.setBookingId(booking.getBookingId());
            response.setRoomId(booking.getRoom().getRoomId());

            List<Integer> serviceIds = booking.getOrderBookingDetails()
                    .stream()
                    .map(detail -> detail.getService().getServiceId())
                    .collect(Collectors.toList());
            response.setServiceIds(serviceIds);

            List<Integer> slotIds = booking.getSlot()
                    .stream()
                    .map(TimeSlot::getTimeSlotId)
                    .collect(Collectors.toList());

            response.setSlotIds(slotIds);
            response.setStatus(booking.getStatus());

            return response;
        });
    }

}
