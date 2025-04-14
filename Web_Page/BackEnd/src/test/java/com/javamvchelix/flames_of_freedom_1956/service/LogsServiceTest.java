/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.javamvchelix.flames_of_freedom_1956.service;

import com.javamvchelix.flames_of_freedom_1956.model.Logs;
import com.javamvchelix.flames_of_freedom_1956.repository.LogsRepository;
import java.util.Arrays;
import java.util.Collections;
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
public class LogsServiceTest {
    private LogsRepository mockRepo;
    private LogsService service;
    
    public LogsServiceTest() {
    }
    
    @BeforeEach
    public void setUp() {
        mockRepo = mock(LogsRepository.class);
        
        service = new LogsService() {{
                layer = mockRepo;
        }};
    }
    
    @Test
    public void testgetAllLogsSuccess() {
        when(mockRepo.getAllLogs()).thenReturn(Arrays.asList(
              new Logs(1, 16, "Game freezing bug", "SOLVED"),
              new Logs(7, 16, "Enemies won't take damage", "SOLVED"),
              new Logs(13, 19, "Textures disappear at random points", "PENDING")
        ));
        JSONObject result = service.getAllLogs();
        
        assertEquals("success", result.getString("status"));
        assertEquals(200, result.getInt("statusCode"));
        assertEquals(3, result.getJSONArray("result").length());
    }
    
    @Test
    public void testgetAllLogsNotFound() {
        when(mockRepo.getAllLogs()).thenReturn(Collections.emptyList());
        JSONObject result = service.getAllLogs();
        
        assertEquals("NoLogsFound", result.getString("status"));
        assertEquals(417, result.getInt("statusCode"));
    }
    
    @Test
    public void testgetAllLogsModelError(){
        when(mockRepo.getAllLogs()).thenReturn(null);
        JSONObject result = service.getAllLogs();
        
        assertEquals("ModelException", result.getString("status"));
        assertEquals(500, result.getInt("statusCode"));
    }
    
    @Test
    public void testgetLogByIdSuccess(){
        when(mockRepo.getLogById(Integer.SIZE)).thenReturn(
                new Logs(3, 16, "Game crashes when the player tries to open the menu", "SOLVED")
        );
        JSONObject result = service.getLogById(Integer.SIZE);
        
        assertEquals("success", result.getString("status"));
        assertEquals(200, result.getInt("statusCode"));
        assertEquals(4, result.getJSONObject("result").length());
    }
    
    @Test
    public void testgetLogByIdNotFound(){
        when(mockRepo.getLogById(Integer.SIZE)).thenReturn(new Logs());
        JSONObject result = service.getLogById(Integer.SIZE);
        
        assertEquals("NoLogsFound", result.getString("status"));
        assertEquals(417, result.getInt("statusCode"));
    }
    
    @Test
    public void testgetLogByIdModelError(){
        when(mockRepo.getLogById(Integer.SIZE)).thenReturn(null);
        JSONObject result = service.getLogById(Integer.SIZE);
        
        assertEquals("ModelException", result.getString("status"));
        assertEquals(500, result.getInt("statusCode"));
    }
    
    
    @Test
    public void testdeleteLogSuccess(){
        Logs deletedLog = new Logs();
        deletedLog.setId(32);
        
        when(mockRepo.deleteLog(deletedLog)).thenReturn(true);
        
        JSONObject result = service.deleteLog(deletedLog);
        
        assertEquals("success", result.getString("status"));
        assertEquals(200, result.getInt("statusCode"));
    }
    
    @Test
    public void testdeleteLogModelError(){
        Logs deletedLog = new Logs();
        deletedLog.setId(32);
        
        when(mockRepo.deleteLog(deletedLog)).thenReturn(false);

        JSONObject result = service.deleteLog(deletedLog);

        assertEquals("ModelException", result.getString("status"));
        assertEquals(500, result.getInt("statusCode"));
    }
    
    @Test
    public void testcreateLogSuccess(){
        Logs createdLog = new Logs();
        createdLog.setAdminId(16);
        createdLog.setLog("New Log Entry");
        
        when(mockRepo.createLog(createdLog)).thenReturn(true);
        
        JSONObject result = service.createLog(createdLog);
        
        assertEquals("success", result.getString("status"));
        assertEquals(200, result.getInt("statusCode"));
    }
    
    @Test
    public void testCreateLogModelError() {
        Logs createdLog = new Logs();
        createdLog.setAdminId(19);
        createdLog.setLog("New log entry");

        when(mockRepo.createLog(createdLog)).thenReturn(false);

        JSONObject result = service.createLog(createdLog);

        assertEquals("ModelException", result.getString("status"));
        assertEquals(500, result.getInt("statusCode"));
    }
}
