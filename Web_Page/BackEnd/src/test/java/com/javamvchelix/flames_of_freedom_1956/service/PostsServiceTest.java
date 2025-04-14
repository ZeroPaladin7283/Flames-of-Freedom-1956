/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.javamvchelix.flames_of_freedom_1956.service;

import com.javamvchelix.flames_of_freedom_1956.model.Posts;
import com.javamvchelix.flames_of_freedom_1956.repository.PostsRepository;
import java.util.Arrays;
import java.util.Collections;
import org.json.JSONObject;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author shado
 */
public class PostsServiceTest {
    
    private PostsRepository mockRepo;
    private PostsService service;
    
    public PostsServiceTest() {
    }
    
    @BeforeEach
    public void setUp() {
        mockRepo = mock(PostsRepository.class);
        
        service = new PostsService() {{
                layer = mockRepo;
        }};
    }

    @Test
    public void testGetAllPostsSuccess() {
        byte[] profilePic = "randomPfP".getBytes();

        Posts newPost = new Posts(3, "randomTitle", "randomCategory", "imageLink", "randomContent", "randomUsername", profilePic);

        when(mockRepo.getAllPosts()).thenReturn(Arrays.asList(newPost));

        JSONObject result = service.getAllPosts();

        assertEquals("success", result.getString("status"));
        assertEquals(200, result.getInt("statusCode"));
        assertEquals(1, result.getJSONArray("result").length());
    }
    
    @Test
    public void testGetAllPostsNotFound() {
        when(mockRepo.getAllPosts()).thenReturn(Collections.emptyList());
        JSONObject result = service.getAllPosts();
        
        assertEquals("NoPostsFound", result.getString("status"));
        assertEquals(417, result.getInt("statusCode"));
    }
    
    @Test
    public void testGetAllPostsModelError(){
        when(mockRepo.getAllPosts()).thenReturn(null);
        JSONObject result = service.getAllPosts();
        
        assertEquals("ModelException", result.getString("status"));
        assertEquals(500, result.getInt("statusCode"));
    }
    
    @Test
    public void testgetPostByIdSuccess(){
        
        when(mockRepo.getPostById(Integer.SIZE)).thenReturn(
                new Posts("randomTitle", 3, "imageLink", "randomContent", 15)
        );
        JSONObject result = service.getPostById(Integer.SIZE);
        
        assertEquals("success", result.getString("status"));
        assertEquals(200, result.getInt("statusCode"));
        assertEquals(5, result.getJSONObject("result").length());
    }
    
    @Test
    public void testgetPostByIdNotFound(){
        when(mockRepo.getPostById(Integer.SIZE)).thenReturn(new Posts());
        JSONObject result = service.getPostById(Integer.SIZE);
        
        assertEquals("PostNotFound", result.getString("status"));
        assertEquals(417, result.getInt("statusCode"));
    }
    
    @Test
    public void testgetPostByIdModelError(){
        when(mockRepo.getPostById(Integer.SIZE)).thenReturn(null);
        JSONObject result = service.getPostById(Integer.SIZE);
        
        assertEquals("ModelException", result.getString("status"));
        assertEquals(500, result.getInt("statusCode"));
    }
    
    @Test
    public void testdeletePost() {
        Posts deletedPost = new Posts();
        deletedPost.setId(32);
        
        when(mockRepo.deletePost(deletedPost)).thenReturn(true);
        
        JSONObject result = service.deletePost(deletedPost);
        
        assertEquals("success", result.getString("status"));
        assertEquals(200, result.getInt("statusCode"));
    }
    
    @Test
    public void testdeleteLogModelError(){
        Posts deletedPost = new Posts();
        deletedPost.setId(32);
        
        when(mockRepo.deletePost(deletedPost)).thenReturn(false);

        JSONObject result = service.deletePost(deletedPost);

        assertEquals("ModelException", result.getString("status"));
        assertEquals(500, result.getInt("statusCode"));
    }
    
        @Test
        public void testcreatePostSuccess(){
            Posts createdPost = new Posts();
            createdPost.setTitle("RandomTitle");
            createdPost.setCategoryId(3);
            createdPost.setContent("RandomContent");
            createdPost.setUserId(5);

            when(mockRepo.createPost(createdPost)).thenReturn(true);

            JSONObject result = service.createPost(createdPost);

            assertEquals("success", result.getString("status"));
            assertEquals(200, result.getInt("statusCode"));
        }
    
    @Test
    public void testcreatePostModelError() {
        Posts createdPost = new Posts();
        createdPost.setTitle("RandomTitle");
        createdPost.setCategoryId(3);
        createdPost.setContent("RandomContent");
        createdPost.setUserId(5);

        when(mockRepo.createPost(createdPost)).thenReturn(false);

        JSONObject result = service.createPost(createdPost);

        assertEquals("ModelException", result.getString("status"));
        assertEquals(500, result.getInt("statusCode"));
    }
}
