/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javamvchelix.flames_of_freedom_1956.service;

import com.javamvchelix.flames_of_freedom_1956.model.Categories;
import com.javamvchelix.flames_of_freedom_1956.repository.CategoriesRepository;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author shado
 */
public class CategoriesService {
    protected CategoriesRepository layer = new CategoriesRepository();
    
    public JSONObject getCategories() {
        JSONObject toReturn = new JSONObject();
        String status = "success";
        int statusCode = 200;
        List<Categories> modelResult = layer.getCategories();
        
        if(modelResult == null) {
            status = "ModelException";
            statusCode = 500;
        } else if(modelResult.isEmpty()){
            status = "CategoriesNotFound";
            statusCode = 417;
        } else {
            JSONArray result = new JSONArray();
            
            for(Categories actualCategory : modelResult) {
                JSONObject toAdd = new JSONObject();
                
                toAdd.put("id", actualCategory.getId());
                toAdd.put("category", actualCategory.getCategory());
                
                result.put(toAdd);
            }
            
            toReturn.put("result", result);
        }
        
        toReturn.put("status", status);
        toReturn.put("statusCode", statusCode);
        return toReturn;
    }
}
