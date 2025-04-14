/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.javamvchelix.flames_of_freedom_1956.service;

import com.javamvchelix.flames_of_freedom_1956.model.Categories;
import com.javamvchelix.flames_of_freedom_1956.repository.CategoriesRepository;
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
public class CategoriesServiceTest {
    private CategoriesRepository mockRepo;
    private CategoriesService service;
    
    public CategoriesServiceTest() {
    }

    
    @BeforeEach
    public void setUp() {
        mockRepo = mock(CategoriesRepository.class);
        
        service = new CategoriesService() {{
                layer = mockRepo;
        }};
    }

    @Test
    public void testgetCategoriesSuccess() {
        when(mockRepo.getCategories()).thenReturn(Arrays.asList(
            new Categories(3, "Fan Art/Creation"),
            new Categories(1, "Guide")
        ));
        JSONObject result = service.getCategories();
        
        assertEquals("success", result.getString("status"));
        assertEquals(200, result.getInt("statusCode"));
        assertEquals(2, result.getJSONArray("result").length());
    }
    
    @Test
    public void testgetCategoriesNotFound() {
        when(mockRepo.getCategories()).thenReturn(Collections.emptyList());
            JSONObject result = service.getCategories();
            
            assertEquals("CategoriesNotFound", result.getString("status"));
            assertEquals(417, result.getInt("statusCode"));
    }
    
    @Test
    public void testgetAllCategoriesModelError() {
        when(mockRepo.getCategories()).thenReturn(null);
        JSONObject result = service.getCategories();
        
        assertEquals("ModelException", result.getString("status"));
        assertEquals(500, result.getInt("statusCode"));
    }
    
}
