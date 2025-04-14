/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.javamvchelix.flames_of_freedom_1956.service;

import com.javamvchelix.flames_of_freedom_1956.model.Users;
import com.javamvchelix.flames_of_freedom_1956.repository.UsersRepository;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author shado
 */
public class UsersServiceTest {
    
    private UsersRepository mockRepo;
    private UsersService service;
    
    public UsersServiceTest() {
    }
    
    @BeforeEach
    public void setUp() {
        mockRepo = mock(UsersRepository.class);
        
        service = new UsersService() {{
                layer = mockRepo;
        }};
    }

    @Test
    public void testLoginSuccess() {
        byte[] profilePic = "randomPfP".getBytes();
        
        String email = "test@example.com";
        String password = "password123";
        
        when(mockRepo.login(email, password)).thenReturn(
                new Users(1, "testuser", profilePic, "github123", email, password, new Date(), true, new Date(), false, null)
        );
        JSONObject result = service.login(email, password);
        
        assertEquals("success", result.getString("status"));
        assertEquals(200, result.getInt("statusCode"));
        assertEquals(11, result.getJSONObject("result").length());
    }

    @Test
    public void testLoginUserIsDeleted() {
        byte[] profilePic = "randomPfP".getBytes();
        
        String email = "test@example.com";
        String password = "password123";
        when(mockRepo.login(email, password)).thenReturn(
                new Users(1, "testuser", profilePic, "github123", email, password, new Date(), true, new Date(), true, new Date())
        );
        JSONObject result = service.login(email, password);
        
        assertEquals("userIsDeleted", result.getString("status"));
        assertEquals(417, result.getInt("statusCode"));
    }
    
    @Test
    public void testLoginUserNotFound(){
        byte[] profilePic = "randomPfP".getBytes();
        
        String email = "test@example.com";
        String password = "password123";
        when(mockRepo.login(email, password)).thenReturn(
                new Users(null, "testuser", profilePic, "github123", email, password, new Date(), true, new Date(), false, new Date())
        );
        JSONObject result = service.login(email, password);
        
        assertEquals("userNotFound", result.getString("status"));
        assertEquals(404, result.getInt("statusCode"));
    }
    
    @Test
    public void testLoginModelError(){
        String email = "test@example.com";
        String password = "password123";
        when(mockRepo.login(email, password)).thenReturn(null);
        JSONObject result = service.login(email, password);
        
        assertEquals("modelException", result.getString("status"));
        assertEquals(500, result.getInt("statusCode"));
    }
    
    @Test
    public void testRegisterUser() {
        Users registeredUser = new Users();
        registeredUser.setUsername("randomUser");
        registeredUser.setEmail("randomEmail@gmail.com");
        registeredUser.setPassword("randomPass1234!");
        registeredUser.setDateOfBirth(new Date());
        
        when(mockRepo.registerUser(registeredUser)).thenReturn(true);
        
        JSONObject result = service.registerUser(registeredUser);
        
        assertEquals("success", result.getString("status"));
        assertEquals(200, result.getInt("statusCode"));
    }
}
