/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.javamvchelix.flames_of_freedom_1956.controller;

import com.javamvchelix.flames_of_freedom_1956.config.JWT;
import com.javamvchelix.flames_of_freedom_1956.model.Posts;
import com.javamvchelix.flames_of_freedom_1956.service.PostsService;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
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
@Path("posts")
public class PostsController {

    @Context
    private UriInfo context;
    private PostsService layer = new PostsService();

    /**
     * Creates a new instance of PostsController
     */
    public PostsController() {
    }

    /**
     * Retrieves representation of an instance of com.javamvchelix.flames_of_freedom_1956.controller.PostsController
     * @return an instance of java.lang.String
     */
    @GET 
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of PostsController
     * @param content representation for the resource
     */
    @PUT 
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
    @GET
    @Path("getAllPosts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPosts() {
        JSONObject obj = layer.getAllPosts();
        return Response.status(obj.getInt("statusCode")).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("getPostById")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPostById(@QueryParam("id") Integer postId) {
        JSONObject obj = layer.getPostById(postId);
        return Response.status(obj.getInt("statusCode")).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("deletePost")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePost(String bodyString){
        JSONObject body = new JSONObject (bodyString);
        
        Posts p = new Posts(
                body.getInt("id")
        );
        JSONObject obj = layer.deletePost(p);
        return Response.status(obj.getInt("statusCode")).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("createPost")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPost(String bodyString){
        JSONObject body = new JSONObject (bodyString);
        
        Posts p = new Posts(
                body.getString("titleIn"),
                body.getInt("categoryIdIn"),
                body.getString("contentIn"),
                body.getInt("userIdIn")
        );
        
        JSONObject obj = layer.createPost(p);
        return Response.status(obj.getInt("statusCode")).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
    }
}
