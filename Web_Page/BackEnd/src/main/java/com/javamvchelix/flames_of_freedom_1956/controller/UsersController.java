/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.javamvchelix.flames_of_freedom_1956.controller;

import com.javamvchelix.flames_of_freedom_1956.config.JWT;
import com.javamvchelix.flames_of_freedom_1956.model.Users;
import com.javamvchelix.flames_of_freedom_1956.repository.UsersRepository;
import com.javamvchelix.flames_of_freedom_1956.service.UsersService;
import java.sql.Date;
import java.util.Map;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
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
@Path("users")
public class UsersController {

    @Context
    private UriInfo context;
    private UsersService layer = new UsersService();

    /**
     * Creates a new instance of UsersController
     */
    public UsersController() {
    }
    
    @GET 
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of UsersController
     * @param content representation for the resource
     */
    @PUT 
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
    @POST
    @Path("registerUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(String bodyString) {
        JSONObject body = new JSONObject(bodyString);
        
        Users u = new Users(
                body.getString("username"),
                body.getString("email"),
                body.getString("password"),
                Date.valueOf(body.getString("dateOfBirth"))
        );
        
        JSONObject obj = layer.registerUser(u);
        return Response.status(obj.getInt("statusCode")).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("registerAdmin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerAdmin(@HeaderParam("token") String jwt, String bodyString) {
        JSONObject body = new JSONObject(bodyString);
        
        Users u = new Users(
                body.getString("username"),
                body.getString("email"),
                body.getString("password"),
                Date.valueOf(body.getString("dateOfBirth"))
        );
        
        JSONObject obj = layer.registerAdmin(u, jwt);
        return Response.status(obj.getInt("statusCode")).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(String bodyString) {
        JSONObject body = new JSONObject(bodyString);
        
        JSONObject obj = layer.login(body.getString("email"), body.getString("password"));
        return Response.status(obj.getInt("statusCode")).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("getAllUser")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUser() {
        JSONObject obj = layer.getAllUser();
        return Response.status(obj.getInt("statusCode")).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("getUserById")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@QueryParam("id") Integer userId) {
        JSONObject obj = layer.getUserById(userId);
        return Response.status(obj.getInt("statusCode")).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("getDevelopers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDevelopers() {
        JSONObject obj = layer.getDevelopers();
        return Response.status(obj.getInt("statusCode")).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("changeInfo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeInfo(String bodyString){
        JSONObject body = new JSONObject(bodyString);
        
        Users u = new Users(
                body.getInt("id"),
                body.getString("newUsername"),
                body.getString("newEmail"),
                body.getString("newPassword"),
                body.getString("imageIn")
        );
        
        JSONObject obj = layer.changeInfo(u);
        return Response.status(obj.getInt("statusCode")).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("sendEmail")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendEmail(String bodyString) {
        JSONObject body = new JSONObject(bodyString);
        Boolean obj = UsersRepository.sendEmail(
                body.getString("to"), 
                body.getBoolean("ccMe"), 
                body.getString("subject"),
                body.getString("content")
        );
        return Response.status(200).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("sendSuccessReg")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendSuccessReg(String bodyString) {
        JSONObject body = new JSONObject(bodyString);
        Boolean obj = UsersRepository.sendSuccessReg(
                body.getString("to"),
                body.getBoolean("ccMe")
        );
        return Response.status(200).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("temporaryPass")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendTemporaryPass(String bodyString) {
        JSONObject body = new JSONObject(bodyString);
        
        Map<String, Object> response = UsersRepository.sendTemporaryPass(
                body.getString("to"),
                body.getBoolean("ccMe")
        );
        return Response.status(200).entity(response).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("changePassword")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response changePassword(String bodyString){
        JSONObject body = new JSONObject(bodyString);
        
        Users u = new Users(
                body.getString("emailIn"),
                body.getString("passwordIn")
        );
        
        JSONObject obj = layer.changePassword(u);
        return Response.status(obj.getInt("statusCode")).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
    }
}
