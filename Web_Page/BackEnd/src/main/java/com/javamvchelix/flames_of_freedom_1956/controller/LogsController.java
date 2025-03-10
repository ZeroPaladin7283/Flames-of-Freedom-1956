/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.javamvchelix.flames_of_freedom_1956.controller;

import com.javamvchelix.flames_of_freedom_1956.config.JWT;
import com.javamvchelix.flames_of_freedom_1956.service.LogsService;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author shado
 */
@Path("logs")
public class LogsController {
    
    private LogsService layer = new LogsService();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of LogsController
     */
    public LogsController() {
    }

    /**
     * Retrieves representation of an instance of com.javamvchelix.flames_of_freedom_1956.controller.LogsController
     * @return an instance of java.lang.String
     */
    @GET @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of LogsController
     * @param content representation for the resource
     */
    @PUT @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
    @GET
    @Path("getAllLogs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLogs() {
        
        JSONObject obj = layer.getAllLogs();
        return  Response.status(obj.getInt("statusCode")).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("getLogById")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogById(@QueryParam("id") Integer logId) {
        JSONObject obj = layer.getLogById(logId);
        return Response.status(obj.getInt("statusCode")).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
    }
}
