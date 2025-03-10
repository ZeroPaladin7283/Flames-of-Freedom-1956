/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javamvchelix.flames_of_freedom_1956.service;

import com.javamvchelix.flames_of_freedom_1956.model.Posts;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author shado
 */
public class PostsService {
    
    private Posts layer = new Posts();
    
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
                toAdd.put("categoryId", actualPost.getCategoryId());
                toAdd.put("imageId", actualPost.getImageId());
                toAdd.put("content", actualPost.getContent());
                toAdd.put("userId", actualPost.getUserId());
                toAdd.put("createdAt", actualPost.getCreatedAt());
                toAdd.put("isDeleted", actualPost.getIsDeleted());
                toAdd.put("deletedAt", actualPost.getDeletedAt());
                
                result.put(toAdd);
            }
            
            toReturn.put("result", result);
        }
        
        toReturn.put("status", status);
        toReturn.put("statusCode", statusCode);
        return toReturn;
    }
    
    public JSONObject getPostData() {
        JSONObject toReturn = new JSONObject();
        String status = "success";
        int statusCode = 200;
        List<Posts> modelResult = layer.getPostData();
        
        if(modelResult == null) {
            status = "ModelException";
            statusCode = 500;
        } else if(modelResult.isEmpty()) {
            status = "NoPostDataFound";
            statusCode = 417;
        } else {
            JSONArray result = new JSONArray();
            
            for(Posts actualPost : modelResult) {
                JSONObject toAdd = new JSONObject();
                
                toAdd.put("id", actualPost.getId());
                toAdd.put("title", actualPost.getTitle());
                toAdd.put("category", actualPost.getCategory());
                toAdd.put("filePath", actualPost.getFilePath());
                toAdd.put("content", actualPost.getContent());
                toAdd.put("username", actualPost.getUsername());
                toAdd.put("createdAt", actualPost.getCreatedAt());
                toAdd.put("isDeleted", actualPost.getIsDeleted());
                toAdd.put("deletedAt", actualPost.getDeletedAt());
                
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
        Posts modelResult = new Posts(id);
        
        if(modelResult.getTitle() == null) {
            status = "PostNotFound";
            statusCode = 417;
        } else {
            JSONObject post = new JSONObject();
            
            post.put("id", modelResult.getId());
            post.put("title", modelResult.getTitle());
            post.put("category", modelResult.getCategoryId());
            post.put("file_path", modelResult.getImageId());
            post.put("content", modelResult.getContent());
            post.put("username", modelResult.getUserId());
            post.put("createdAt", modelResult.getCreatedAt());
            post.put("isDeleted", modelResult.getIsDeleted());
            post.put("deletedAt", modelResult.getDeletedAt());
            
            toReturn.put("result", post);
        }
        
        toReturn.put("status", status);
        toReturn.put("statusCode", statusCode);
        return toReturn;
    }
}
