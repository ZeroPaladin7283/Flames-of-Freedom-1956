/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javamvchelix.flames_of_freedom_1956.service;

import com.javamvchelix.flames_of_freedom_1956.model.Logs;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author shado
 */
public class LogsService {
    
    private Logs layer = new Logs();
    
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
                toAdd.put("userId", actualLog.getUserId());
                toAdd.put("log", actualLog.getLog());
                toAdd.put("createdAt", actualLog.getCreatedAt());
                toAdd.put("isDeleted", actualLog.getIsDeleted());
                toAdd.put("deletedAt", actualLog.getDeletedAt());
                
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
        Logs modelResult = new Logs(id);
        
        if(modelResult.getLog() == null) {
            status = "NoLogsFound";
            statusCode = 417;
        } else {
            JSONObject log = new JSONObject();
                
                log.put("id", modelResult.getId());
                log.put("getUserId", modelResult.getUserId());
                log.put("log", modelResult.getLog());
                log.put("createdAt", modelResult.getCreatedAt());
                log.put("isDeleted", modelResult.getIsDeleted());
                log.put("deletedAt", modelResult.getDeletedAt());
                
                toReturn.put("result", log);
        }
        
        toReturn.put("status", status);
        toReturn.put("statusCode", statusCode);
        return toReturn;
    }
}
