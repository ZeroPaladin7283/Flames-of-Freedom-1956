/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javamvchelix.flames_of_freedom_1956.service;

import com.javamvchelix.flames_of_freedom_1956.model.Logs;
import com.javamvchelix.flames_of_freedom_1956.repository.LogsRepository;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author shado
 */
public class LogsService {
    
    protected LogsRepository layer = new LogsRepository();
    
    public JSONObject getAllLogs() {
        JSONObject toReturn = new JSONObject();
        String status = "success";
        int statusCode = 200;
        
        List<Logs> modelResult = layer.getAllLogs();
        
        if(modelResult == null) {
            status = "ModelException";
            statusCode = 500;
        } else if (modelResult.isEmpty()) {
            status = "NoLogsFound";
            statusCode = 417;
        } else {
            JSONArray result = new JSONArray();
            
            for(Logs actualLog : modelResult) {
                JSONObject toAdd = new JSONObject();
                
                toAdd.put("id", actualLog.getId());
                toAdd.put("profilePic", actualLog.getBase64Image());
                toAdd.put("username", actualLog.getUsername());
                toAdd.put("log", actualLog.getLog());
                toAdd.put("status", actualLog.getStatus());
                
                result.put(toAdd);
            }
            
            toReturn.put("result", result);
        }
        
        toReturn.put("status", status);
        toReturn.put("statusCode", statusCode);
        return toReturn;
    }
    
    public JSONObject getLogById(Integer id) {
        JSONObject toReturn = new JSONObject();
        String status = "success";
        int statusCode = 200;
        Logs modelResult = layer.getLogById(id);
        
        if(modelResult == null) {
            status = "ModelException";
            statusCode = 500;
        } else if(modelResult.getLog() == null) {
            status = "NoLogsFound";
            statusCode = 417;
        } else {
            JSONObject log = new JSONObject();
                
                log.put("id", modelResult.getId());
                log.put("getAdminId", modelResult.getAdminId());
                log.put("log", modelResult.getLog());
                log.put("status", modelResult.getStatus());
                
                toReturn.put("result", log);
        }
        
        toReturn.put("status", status);
        toReturn.put("statusCode", statusCode);
        return toReturn;
    }
    
    public JSONObject deleteLog(Logs l) {
        JSONObject toReturn = new JSONObject();
        String status = "success";
        int statusCode = 200;
        
        Boolean modelResult = layer.deleteLog(l);
        
        if(!modelResult) {
            status = "ModelException";
            statusCode = 500;
        }
        
        toReturn.put("status", status);
        toReturn.put("statusCode", statusCode);
        
        return toReturn;
    }
    
    public JSONObject createLog(Logs l) {
        JSONObject toReturn = new JSONObject();
        String status = "success";
        int statusCode = 200;
        
        Boolean modelResult = layer.createLog(l);
        
        if(!modelResult){
            status = "ModelException";
            statusCode = 500;
        }
        
        toReturn.put("status", status);
        toReturn.put("statusCode", statusCode);
        
        return toReturn;
    }
}
