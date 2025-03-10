/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.javamvchelix.flames_of_freedom_1956.controller;

import com.javamvchelix.flames_of_freedom_1956.config.JWT;
import com.javamvchelix.flames_of_freedom_1956.service.CommentsService;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author shado
 */
@Path("comments")
public class CommentsController {

    @Context
    private UriInfo context;
    private CommentsService layer = new CommentsService();

    /**
     * Creates a new instance of CommentsController
     */
    public CommentsController() {
    }

    /**
     * Retrieves representation of an instance of com.javamvchelix.flames_of_freedom_1956.controller.CommentsController
     * @return an instance of java.lang.String
     */
    @GET @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of CommentsController
     * @param content representation for the resource
     */
    @PUT @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
    @GET
    @Path("getCommentsData")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCommentsData(@HeaderParam("token") String jwt) {
        int isValid = JWT.validateJWT(jwt);
        
        if(isValid == 1) {
            JSONObject obj = layer.getCommentsData();
            return Response.status(obj.getInt("statusCode")).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        } else if(isValid == 2) {
            JSONObject obj = layer.getCommentsData();
            return Response.status(obj.getInt("statusCode")).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(401).entity("TokenExpired").type(MediaType.APPLICATION_JSON).build();
        }
    }
}
