/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.javamvchelix.flames_of_freedom_1956.service;

import com.javamvchelix.flames_of_freedom_1956.model.Reviews;
import com.javamvchelix.flames_of_freedom_1956.repository.ReviewRepository;
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
public class ReviewServiceTest {
    
    private ReviewRepository mockRepo;
    private ReviewService service;
    
    public ReviewServiceTest() {
    }
    
    @BeforeEach
    public void setUp() {
        mockRepo = mock(ReviewRepository.class);
        
        service = new ReviewService() {{
                layer = mockRepo;
        }};
    }

    @Test
    public void testgetReviewsSuccess() {
        byte[] profilePic = "randomPfP".getBytes();
        
        Reviews newReview = new Reviews(6, "randomUsername", profilePic, "randomReview");
        
        when(mockRepo.getReviews()).thenReturn(Arrays.asList(newReview));
        
        JSONObject result = service.getReviews();
        
        assertEquals("success", result.getString("status"));
        assertEquals(200, result.getInt("statusCode"));
    }
    
    @Test
    public void testgetReviewsNotFound() {
        when(mockRepo.getReviews()).thenReturn(Collections.emptyList());
        JSONObject result = service.getReviews();
        
        assertEquals("NoReviewsFound", result.getString("status"));
        assertEquals(417, result.getInt("statusCode"));
    }
    
    @Test
    public void testgetReviewsModelError(){
        when(mockRepo.getReviews()).thenReturn(null);
        JSONObject result = service.getReviews();
        
        assertEquals("ModelException", result.getString("status"));
        assertEquals(500, result.getInt("statusCode"));
    }
    
}
