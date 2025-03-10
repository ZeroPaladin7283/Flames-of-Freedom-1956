/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javamvchelix.flames_of_freedom_1956.service;

import com.javamvchelix.flames_of_freedom_1956.model.Comments;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author shado
 */
public class CommentsService {
    private Comments layer = new Comments();
    
    public JSONObject getCommentsData() {
        JSONObject toReturn = new JSONObject();
        String status = "success";
        int statusCode = 200;
        List<Comments> modelResult = layer.getCommentsData();
        
        if(modelResult == null) {
            status = "ModelException";
            statusCode = 500;
        } else if(modelResult.isEmpty()){
            status = "NoCommentsFound";
            statusCode = 417;
        } else {
            JSONArray result = new JSONArray();
            
            for(Comments actualComment : modelResult) {
                JSONObject toAdd = new JSONObject();
                
                toAdd.put("id", actualComment.getId());
                toAdd.put("title", actualComment.getTitle());
                toAdd.put("username", actualComment.getUsername());
                toAdd.put("content", actualComment.getContent());
                toAdd.put("filePath", actualComment.getFilePath());
                toAdd.put("createdAt", actualComment.getCreatedAt());
                toAdd.put("isDeleted", actualComment.getIsDeleted());
                toAdd.put("deletedAt", actualComment.getDeletedAt());
                
                result.put(toAdd);
            }
            
            toReturn.put("result", result);
        }
        
        toReturn.put("status", status);
        toReturn.put("statusCode", statusCode);
        return toReturn;
    }
}
