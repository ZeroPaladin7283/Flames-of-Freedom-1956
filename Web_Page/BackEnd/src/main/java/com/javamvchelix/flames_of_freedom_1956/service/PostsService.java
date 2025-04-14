/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javamvchelix.flames_of_freedom_1956.service;

import com.javamvchelix.flames_of_freedom_1956.model.Posts;
import com.javamvchelix.flames_of_freedom_1956.repository.PostsRepository;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author shado
 */
public class PostsService {
    
    protected PostsRepository layer = new PostsRepository();
    
    public JSONObject getAllPosts() {
        JSONObject toReturn = new JSONObject();
        String status = "success";
        int statusCode = 200;
        List<Posts> modelResult = layer.getAllPosts();
        
        if(modelResult == null) {
            status = "ModelException";
            statusCode = 500;
        } else if (modelResult.isEmpty()) {
            status = "NoPostsFound";
            statusCode = 417;
        } else {
            JSONArray result = new JSONArray();
            
            for (Posts actualPost : modelResult) {
                JSONObject toAdd = new JSONObject();
                
                toAdd.put("id", actualPost.getId());
                toAdd.put("title", actualPost.getTitle());
                toAdd.put("category", actualPost.getCategory());
                toAdd.put("image", actualPost.getImage());
                toAdd.put("content", actualPost.getContent());
                toAdd.put("username", actualPost.getUsername());
                toAdd.put("profilePic", actualPost.getBase64Image());
                
                result.put(toAdd);
            }
            
            toReturn.put("result", result);
        }
        
        toReturn.put("status", status);
        toReturn.put("statusCode", statusCode);
        return toReturn;
    }
    
    public JSONObject getPostById(Integer id) {
        JSONObject toReturn = new JSONObject();
        String status = "success";
        int statusCode = 200;
        Posts modelResult = layer.getPostById(id);
        
        if(modelResult == null) {
            status = "ModelException";
            statusCode = 500;
        } else if(modelResult.getTitle() == null) {
            status = "PostNotFound";
            statusCode = 417;
        } else {
            JSONObject post = new JSONObject();
            
            post.put("title", modelResult.getTitle());
            post.put("category", modelResult.getCategoryId());
            post.put("image", modelResult.getImage());
            post.put("content", modelResult.getContent());
            post.put("userId", modelResult.getUserId());
            
            toReturn.put("result", post);
        }
        
        toReturn.put("status", status);
        toReturn.put("statusCode", statusCode);
        return toReturn;
    }
    
    public JSONObject deletePost(Posts p) {
        JSONObject toReturn = new JSONObject();
        String status = "success";
        int statusCode = 200;
        
        Boolean modelResult = layer.deletePost(p);
        
        if(!modelResult) {
            status = "ModelException";
            statusCode = 500;
        }
        
        toReturn.put("status", status);
        toReturn.put("statusCode", statusCode);
        
        return toReturn;
    }
    
    public JSONObject createPost(Posts p) {
        JSONObject toReturn = new JSONObject();
        String status = "success";
        int statusCode = 200;
        
        Boolean modelResult = layer.createPost(p);
        
        if(!modelResult){
            status = "ModelException";
            statusCode = 500;
        }
        
        toReturn.put("status", status);
        toReturn.put("statusCode", statusCode);
        
        return toReturn;
    }
}
