package fpt.swp.WorkSpace.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fpt.swp.WorkSpace.auth.LoginRequest;
import fpt.swp.WorkSpace.auth.RegisterRequest;
import fpt.swp.WorkSpace.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.*;
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void register_ShouldReturnOK_WhenValidRequest() throws Exception {
        // Tạo một yêu cầu đăng ký hợp lệ
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUserName("bao02");
        registerRequest.setPassword("123456");
        registerRequest.setFullName("Quoc Bao");
        registerRequest.setPhoneNumber("091273485");
        registerRequest.setRole("CUSTOMER");

        // Chuyển request thành JSON
        String requestJson = objectMapper.writeValueAsString(registerRequest);

        // Thực hiện POST request và kiểm tra phản hồi
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk()) // Kiểm tra mã trạng thái HTTP là 200
                .andExpect(jsonPath("$.statusCode").value(200));
    }

    @Test
    public void register_ShouldReturnBadRequest_WhenInvalidRequest() throws Exception {
        // Tạo yêu cầu không hợp lệ (ví dụ thiếu username)
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setPassword("123456"); // Thiếu username

        String requestJson = objectMapper.writeValueAsString(registerRequest);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest()) // Kiểm tra mã trạng thái HTTP 400
                .andExpect(jsonPath("$.statusCode").value(400));
    }

    @Test
    public void login_ShouldReturnOK_WhenValidLogin() throws Exception {


        // Tạo yêu cầu login hợp lệ
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserName("bao1");
        loginRequest.setPassword("123456");

        String requestJson = objectMapper.writeValueAsString(loginRequest);

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk()) // Kiểm tra mã trạng thái HTTP 200
                .andExpect(jsonPath("$.data.roleName").value("CUSTOMER")); // Kiểm tra roleName
    }

    @Test
    public void login_ShouldReturnNotFound_WhenUserNotFound() throws Exception {
        // Tạo yêu cầu login không tồn tại
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserName("nonexistentuser");
        loginRequest.setPassword("wrongpassword");

        String requestJson = objectMapper.writeValueAsString(loginRequest);

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isNotFound()) // Kiểm tra mã trạng thái HTTP 404
                .andExpect(jsonPath("$.statusCode").value(404)); // Kiểm tra statusCode là 404
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void login_ShouldReturnNullPointerException_WhenUserNotFound() throws Exception {
        // Tạo yêu cầu login không tồn tại
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserName("nonexistentuser");
        loginRequest.setPassword("wrongpassword");

        String requestJson = objectMapper.writeValueAsString(loginRequest);

        authService.login(loginRequest);

//        mockMvc.perform(post("/api/auth/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(requestJson))
//                .andExpect(status().isNotFound()) // Kiểm tra mã trạng thái HTTP 404
//                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NullPointerException)); // Kiểm tra nếu có NullPointerException
    }



}