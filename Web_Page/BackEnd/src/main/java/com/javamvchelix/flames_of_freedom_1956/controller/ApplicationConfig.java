/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javamvchelix.flames_of_freedom_1956.controller;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author shado
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.javamvchelix.flames_of_freedom_1956.controller.CommentsController.class);
        resources.add(com.javamvchelix.flames_of_freedom_1956.controller.LogsController.class);
        resources.add(com.javamvchelix.flames_of_freedom_1956.controller.PostsController.class);
        resources.add(com.javamvchelix.flames_of_freedom_1956.controller.UsersController.class);
    }
    
}
