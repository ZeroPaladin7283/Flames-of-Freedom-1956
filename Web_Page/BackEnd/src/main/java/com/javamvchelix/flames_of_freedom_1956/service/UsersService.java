/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javamvchelix.flames_of_freedom_1956.service;

import com.javamvchelix.flames_of_freedom_1956.config.JWT;
import com.javamvchelix.flames_of_freedom_1956.model.Users;
import com.javamvchelix.flames_of_freedom_1956.repository.UsersRepository;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author shado
 */
public class UsersService {
    
    protected UsersRepository layer = new UsersRepository();
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    
    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return  matcher.matches();
    }
    
    public static boolean isValidPassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        
        boolean hasNumber = false;
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasSpecialChar = false;
        
        for(char c : password.toCharArray()) {
            if(Character.isDigit(c)) {
                hasNumber = true;
            } else if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if ("!@#$%^&*()_+-=[]{}|;':,.<>?/`~".indexOf(c) != -1) {
                hasSpecialChar = true;
            }
        }
        
        return hasNumber && hasUpperCase && hasLowerCase && hasSpecialChar;
    }
    
    public JSONObject login(String email, String password) {
        JSONObject toReturn = new JSONObject();
        String status = "success";
        int statusCode = 200;
        
        if (isValidEmail(email)) {
            Users modelResult = layer.login(email, password);
            
            if(modelResult == null) {
                status = "modelException";
                statusCode = 500;
            } else {
                if(modelResult.getId() == null) {
                    status = "userNotFound";
                    statusCode = 404;
                } else if(modelResult.getIsDeleted()) {
                    status = "userIsDeleted";
                    statusCode = 417;
                } else {
                    JSONObject result = new JSONObject();
                    result.put("id", modelResult.getId());
                    result.put("username", modelResult.getUsername());
                    result.put("profilePic", modelResult.getBase64Image());
                    result.put("github_id", modelResult.getGithubId());
                    result.put("email", modelResult.getEmail());
                    result.put("password", modelResult.getPassword());
                    result.put("dateOfBirth", modelResult.getDateOfBirth());
                    result.put("isAdmin", modelResult.getIsAdmin());
                    result.put("registered_at", modelResult.getRegisteredAt());
                    result.put("is_deleted", modelResult.getIsDeleted());
                    result.put("deleted_at", modelResult.getDeletedAt());
                    result.put("jwt", JWT.createJWT(modelResult));
                    
                    toReturn.put("result", result);
                }
            }
        } else {
            status = "invalidEmail";
            statusCode = 417;
        }
        
        toReturn.put("status", status);
        toReturn.put("statusCode", statusCode);
        return toReturn;
    }
    
    public JSONObject registerUser(Users u) {
        JSONObject toReturn = new JSONObject();
        String status = "success";
        int statusCode = 200;
        
        if(isValidEmail(u.getEmail())) {
            if(isValidPassword(u.getPassword())) {
                boolean userIsExists = UsersRepository.isUserExists(u.getEmail());
                if(UsersRepository.isUserExists(u.getEmail()) == null) {
                    status = "ModelException";
                    statusCode = 500;
                } else if(userIsExists == true) {
                    status = "UserAlreadyExists";
                    statusCode = 417;
                } else {
                    boolean registerUser = layer.registerUser(u);
                    if (registerUser == false) {
                        status = "fail";
                        statusCode = 417;
                    }
                }
            } else {
                status = "InvalidPassword";
                statusCode = 417;
            }
        } else {
            status = "InvalidEmail";
            statusCode = 417;
        }
        
        toReturn.put("status", status);
        toReturn.put("statusCode", statusCode);
        return toReturn;
    }
    
    public JSONObject registerAdmin(Users u, String jwt) {
        JSONObject toReturn = new JSONObject();
        String status = "success";
        int statusCode = 200;
        
        if(JWT.isAdmin(jwt)) {
            if(isValidPassword(u.getPassword())) {
                if(isValidEmail(u.getEmail())) {
                    boolean userIsExists = UsersRepository.isUserExists(u.getEmail());
                    if(UsersRepository.isUserExists(u.getEmail()) == null) {
                        status = "ModelException";
                        statusCode = 500;
                    } else if(userIsExists == true) {
                        status = "UserAlreadyExists";
                        statusCode = 417;
                    } else {
                        boolean registerAdmin = layer.registerAdmin(u);
                        if(registerAdmin == false) {
                            status = "fail";
                            statusCode = 417;
                        }
                    }
                } else {
                    status = "InvalidEmail";
                    statusCode = 417;
                }
            } else {
                status = "InvalidPassword";
                statusCode = 417;
            }
        } else {
            status = "PermissionError";
            statusCode = 417;
        }
        
        toReturn.put("status", status);
        toReturn.put("statusCode", statusCode);
        return toReturn;
    }
    
    public JSONObject getAllUser() {
        JSONObject toReturn = new JSONObject();
        String status = "success";
        int statusCode = 200;
        List<Users> modelResult = layer.getAllUser();
        
        if(modelResult == null) {
            status = "ModelException";
            statusCode = 500;
        } else if (modelResult.isEmpty()) {
            status = "NoUsersFound";
            statusCode = 417;
        } else {
            JSONArray result = new JSONArray();
            
            for (Users actualUser : modelResult) {
                JSONObject toAdd = new JSONObject();
                
                toAdd.put("id", actualUser.getId());
                toAdd.put("username", actualUser.getUsername());
                toAdd.put("profilePic", actualUser.getBase64Image());
                toAdd.put("githubId", actualUser.getGithubId());
                toAdd.put("email", actualUser.getEmail());
                toAdd.put("password", actualUser.getPassword());
                toAdd.put("dateOfBirth", actualUser.getDateOfBirth());
                toAdd.put("isAdmin", actualUser.getIsAdmin());
                toAdd.put("registeredAt", actualUser.getRegisteredAt());
                
                result.put(toAdd);
            }
            
            toReturn.put("result", result);
        }
        
        toReturn.put("status", status);
        toReturn.put("statusCode", statusCode);
        return toReturn;
    }
    
    public JSONObject getUserById(Integer id) {
        JSONObject toReturn = new JSONObject();
        String status = "success";
        int statusCode = 200;
        Users modelResult = layer.getUserById(id);
        
        if(modelResult.getId() == null) {
            status = "UserNotFound";
            statusCode = 417;
        } else {
            JSONObject user = new JSONObject();
            
            user.put("id", modelResult.getId());
            user.put("username", modelResult.getUsername());
            user.put("profilePic", modelResult.getBase64Image());
            user.put("githubId", modelResult.getGithubId());
            user.put("email", modelResult.getEmail());
            user.put("password", modelResult.getPassword());
            user.put("dateOfBirth", modelResult.getDateOfBirth());
            user.put("isAdmin", modelResult.getIsAdmin());
            user.put("registeredAt", modelResult.getRegisteredAt());
            
            toReturn.put("result", user);
        }
        
        toReturn.put("status", status);
        toReturn.put("statusCode", statusCode);
        return toReturn;
    }
    
    public JSONObject getDevelopers() {
        JSONObject toReturn = new JSONObject();
        String status = "success";
        int statusCode = 200;
        List<Users> modelResult = layer.getDevelopers();
        
        if(modelResult == null) {
            status = "ModelException";
            statusCode = 500;
        } else if(modelResult.isEmpty()) {
            status = "NoDevelopersFound";
            statusCode = 417;
        } else {
            JSONArray result = new JSONArray();
            
            for(Users actualUser : modelResult) {
                JSONObject toAdd = new JSONObject();
                
                toAdd.put("id", actualUser.getId());
                toAdd.put("username", actualUser.getUsername());
                toAdd.put("profilePic", actualUser.getBase64Image());
                toAdd.put("githubId", actualUser.getGithubId());
                toAdd.put("email", actualUser.getEmail());
                toAdd.put("password", actualUser.getPassword());
                toAdd.put("dateOfBirth", actualUser.getDateOfBirth());
                toAdd.put("isAdmin", actualUser.getIsAdmin());
                toAdd.put("registeredAt", actualUser.getRegisteredAt());
                
                result.put(toAdd);
            }
            
            toReturn.put("result", result);
        }
        
        toReturn.put("status", status);
        toReturn.put("statusCode", statusCode);
        return toReturn;
    }
    
    public JSONObject changeInfo(Users u) {
        JSONObject toReturn = new JSONObject();
        String status = "success";
        int statusCode = 200;
        
        Boolean modelResult = layer.changeInfo(u);
        
        if(!modelResult){
            status = "ModelException";
            statusCode = 500;
        }
        
        toReturn.put("status", status);
        toReturn.put("statusCode", statusCode);
        
        return toReturn;
    }
    
    public JSONObject changePassword(Users u) {
        JSONObject toReturn = new JSONObject();
        String status = "success";
        int statusCode = 200;

        Boolean modelResult = layer.changePassword(u);

        if (!modelResult) {
            status = "ModelException";
            statusCode = 500;
        }

        toReturn.put("status", status);
        toReturn.put("statusCode", statusCode);

        return toReturn;
    }
}
