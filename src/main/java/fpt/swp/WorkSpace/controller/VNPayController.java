package fpt.swp.WorkSpace.controller;

import fpt.swp.WorkSpace.service.VNPAYService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/vnpay")
public class VNPayController {
    @Autowired
    private VNPAYService vnPayService;

    @PostMapping("/createOrderTopUp")
    public String createOrderTopUp(@RequestParam int amount, @RequestParam String userId, @RequestParam String urlReturn, HttpServletRequest request) {
        return vnPayService.createOrderTopUp(request, amount, userId, urlReturn);
    }

    @GetMapping("/return/orderReturn")
    public ResponseEntity<Map<String, Object>> orderReturn(HttpServletRequest request) {
        return vnPayService.orderReturn(request);
    }
}
