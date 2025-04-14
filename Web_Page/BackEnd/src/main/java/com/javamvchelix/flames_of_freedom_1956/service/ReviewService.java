/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javamvchelix.flames_of_freedom_1956.service;

import com.javamvchelix.flames_of_freedom_1956.model.Reviews;
import com.javamvchelix.flames_of_freedom_1956.repository.ReviewRepository;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author shado
 */
public class ReviewService {
    
    protected  ReviewRepository layer = new ReviewRepository();
    
    
    public JSONObject getReviews() {
        JSONObject toReturn = new JSONObject();
        String status = "success";
        int statusCode = 200;
        List<Reviews> modelResult = layer.getReviews();
        
        if(modelResult == null) {
            status = "ModelException";
            statusCode = 500;
        } else if (modelResult.isEmpty()) {
            status = "NoReviewsFound";
            statusCode = 417;
        } else {
            JSONArray result = new JSONArray();
            
            for(Reviews actualReview : modelResult){
                JSONObject toAdd = new JSONObject();
                
                toAdd.put("id", actualReview.getId());
                toAdd.put("username", actualReview.getUsername());
                toAdd.put("profilePic", actualReview.getBase64Image());
                toAdd.put("review", actualReview.getReview());
                
                result.put(toAdd);
            }
            
            toReturn.put("result", result);
        }
        
        toReturn.put("status", status);
        toReturn.put("statusCode", statusCode);
        return toReturn;
    }
}
