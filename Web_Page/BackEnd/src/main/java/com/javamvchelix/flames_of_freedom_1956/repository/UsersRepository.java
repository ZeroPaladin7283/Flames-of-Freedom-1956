/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javamvchelix.flames_of_freedom_1956.repository;

import com.javamvchelix.flames_of_freedom_1956.model.Users;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author shado
 */
public class UsersRepository {
    /* Bemásolom, az ID-s constructort, illetve az EMF-es Szart*/
    /* Az összes függvényt */
    /* return em.find(osztály neve.class, id); az id-s contructorban */
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.JavaMVCHelix_Flames_of_Freedom_1956_war_1.0-SNAPSHOTPU");
    
    public Users findUsersById(Integer id) {
        EntityManager em = emf.createEntityManager();
        
        try {
            return em.find(Users.class, id);
        } catch (Exception ex) {
            System.err.println("Hiba: " + ex.getLocalizedMessage());
        } finally {
            em.clear();
            em.close();
        }
        return null;
    }
    
    public Users login(String email, String password) {
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("login");
            
            spq.registerStoredProcedureParameter("emailIn", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("passwordIn", String.class, ParameterMode.IN);
            
            spq.setParameter("emailIn", email);
            spq.setParameter("passwordIn", password);
            
            spq.execute();
            
            List<Object[]> resultList = spq.getResultList();
            Users toReturn = new Users();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
            
            for(Object[] o : resultList) {
                Users u = new Users(
                        Integer.valueOf(o[0].toString()),
                        o[1].toString(),
                        o[2] != null ? (byte[]) o[2]: null,
                        o[3] == null ? null : o[3].toString(),
                        o[4].toString(),
                        o[5].toString(),
                        formatter2.parse(o[6].toString()),
                        Boolean.parseBoolean(o[7].toString()),
                        formatter.parse(o[8].toString()),
                        Boolean.parseBoolean(o[9].toString()),
                        o[10] == null ? null : formatter.parse(o[10].toString())
                );
                toReturn = u;
                System.out.println(u);
            }
            
            System.out.println(toReturn);
            return toReturn;
        } catch (Exception ex) {
            System.err.println("Hiba: " + ex.getLocalizedMessage());
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }
    
    public Boolean registerUser(Users u) {
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("registerUser");
            
            spq.registerStoredProcedureParameter("usernameIn", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("emailIn", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("passwordIn", String.class , ParameterMode.IN);
            spq.registerStoredProcedureParameter("birthIn", Date.class , ParameterMode.IN);
            
            spq.setParameter("usernameIn", u.getUsername());
            spq.setParameter("emailIn", u.getEmail());
            spq.setParameter("passwordIn", u.getPassword());
            spq.setParameter("birthIn", u.getDateOfBirth());
            
            spq.execute();
            
            return true;
        } catch (Exception e) {
            System.err.println("Hiba: " + e.getLocalizedMessage());
            return false;
        } finally {
            em.clear();
            em.close();
        }
    }
    
    public Boolean registerAdmin(Users u) {
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("registerAdmin");
            
            spq.registerStoredProcedureParameter("usernameIn", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("emailIn", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("passwordIn", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("dateOfBirthIn", Date.class, ParameterMode.IN);
            
            spq.setParameter("usernameIn", u.getUsername());
            spq.setParameter("emailIn", u.getEmail());
            spq.setParameter("passwordIn", u.getPassword());
            spq.setParameter("dateOfBirthIn", u.getDateOfBirth());
            
            spq.execute();
            
            return true;
        } catch (Exception e) {
            System.err.println("Hiba: " + e.getLocalizedMessage());
            return false;
        } finally {
            em.clear();
            em.close();
        }
    }
    
    public static Boolean isUserExists(String email) {
        EntityManager em = emf.createEntityManager();

        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("isUserExists");

            spq.registerStoredProcedureParameter("emailIn", String.class , ParameterMode.IN);
            spq.registerStoredProcedureParameter("resultOut", Boolean.class , ParameterMode.OUT);

            spq.setParameter("emailIn", email);

            spq.execute();

            Object resultObj = spq.getOutputParameterValue("resultOut");

            if (resultObj == null) {
                return Boolean.FALSE;
            }

            return (Boolean) resultObj;
        } catch (Exception e) {
            System.err.println("Hiba: " + e.getLocalizedMessage());
            return Boolean.FALSE;
        } finally {
            em.clear();
            em.close();
        }
    }
    
    public List<Users> getAllUser() {
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllUser");
            spq.execute();
            
            List<Users> toReturn = new ArrayList();
            List<Object[]> resultList = spq.getResultList();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
            for (Object[] record : resultList) {
                Users u = new Users(
                        Integer.valueOf(record[0].toString()),
                        record[1].toString(),
                        record[2] != null ? (byte[]) record[2] : null,
                        record[3] == null ? null : record[3].toString(),
                        record[4].toString(),
                        record[5].toString(),
                        formatter2.parse(record[6].toString()),
                        Boolean.parseBoolean(record[7].toString()),
                        formatter.parse(record[8].toString())
                );
                
                toReturn.add(u);
            }
            
            return toReturn;
        } catch (Exception e) {
            System.err.println("Hiba: " + e.getLocalizedMessage());
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }
    
    public Users getUserById(Integer id) {
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getUserById");
            
            spq.registerStoredProcedureParameter("idIn", Integer.class , ParameterMode.IN);
            spq.setParameter("idIn", id);
            
            spq.execute();
            
            List<Object[]> resultList = spq.getResultList();
            Users toReturn = new Users();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
            
            for(Object[] record : resultList) {
                Users u = new Users(
                        Integer.valueOf(record[0].toString()),
                        record[1].toString(),
                        record[2] != null ? (byte[]) record[2] : null,
                        record[3] == null ? null : record[3].toString(),
                        record[4].toString(),
                        record[5].toString(),
                        formatter2.parse(record[6].toString()),
                        Boolean.parseBoolean(record[7].toString()),
                        formatter.parse(record[8].toString())
                );
                toReturn = u;
                System.out.println(u);
            }
            System.out.println(toReturn);
            return toReturn;
        } catch (Exception ex) {
            System.err.println("Hiba: " + ex.getLocalizedMessage());
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }
    
    public List<Users> getDevelopers() {
        EntityManager em = emf.createEntityManager();
        
        try{
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getDevelopers");
            spq.execute();
            
            List<Users> toReturn = new ArrayList();
            List<Object[]> resultList = spq.getResultList();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
            
            for(Object[] record : resultList) {
                Users u = new Users(
                        Integer.valueOf(record[0].toString()),
                        record[1].toString(),
                        record[2] != null ? (byte[]) record[2] : null,
                        record[3] == null ? null : record[3].toString(),
                        record[4].toString(),
                        record[5].toString(),
                        formatter2.parse(record[6].toString()),
                        formatter.parse(record[7].toString())
                );
                
                toReturn.add(u);
            }
            
            return toReturn;
        } catch (Exception e) {
            System.err.println("Hiba: " + e.getLocalizedMessage());
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }
    
    public Boolean changeInfo(Users u) {
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("changeInfo");
            
            spq.registerStoredProcedureParameter("idIn", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("newUsernameIn", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("newEmailIn", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("newPasswordIn", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("imageIn", byte[].class, ParameterMode.IN);
            
            byte[] imageBytes = Base64.getDecoder().decode(u.getBase64Image());
            
            spq.setParameter("idIn", u.getId());
            spq.setParameter("newUsernameIn", u.getUsername());
            spq.setParameter("newEmailIn", u.getEmail());
            spq.setParameter("newPasswordIn", u.getPassword());
            spq.setParameter("imageIn", imageBytes);
            
            spq.execute();
            
            return true;
        } catch (Exception e) {
            System.err.println("Hiba: " + e.getLocalizedMessage());
            return false;
        } finally {
            em.clear();
            em.close();
        }
    }
    
    public static Boolean sendEmail(String to, boolean ccMe, String subject, String content){
        try{
            final String from = "FlamesOfFreedom1956@gmail.com";
            final String password = "ysun piml flco rjpo";
            
            String host = "smtp.gmail.com";
            
            Properties properties = System.getProperties();
            
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");
            
            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });
            session.setDebug(true);
            
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            
            if(ccMe) {
                message.addRecipient(Message.RecipientType.CC, new InternetAddress(from));
            }
            
            message.setSubject(subject);
            message.setContent(content, "text/html;charset=utf-8");
            Transport.send(message);
            
            return true;
        } catch(Exception ex) {
            System.err.println("Hiba: " + ex.getLocalizedMessage());
            return false;
        }
    }
    
    public static Boolean sendSuccessReg(String to, boolean ccMe){
        try{
            final String from = "FlamesOfFreedom1956@gmail.com";
            final String password = "ysun piml flco rjpo";
            
            String host = "smtp.gmail.com";

            Properties properties = System.getProperties();

            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");
            
            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });
            session.setDebug(true);
            
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Thank you for joining us!");
            
            String msg = "<!DOCTYPE html>" +
                    "<html>" +
                    "<head>" +
                    "<style>" +
                    "body { font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; }" +
                    ".email-container { max-width: 600px; background: white; padding: 20px; border-radius: 10px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1); }" +
                    ".header { text-align: center; }" +
                    ".header img { max-width: 150px; }" +
                    ".content { font-size: 16px; color: #333; line-height: 1.6; }" +
                    ".cta-button { display: block; width: 200px; margin: 20px auto; padding: 10px; text-align: center; background-color: #007bff; color: white; text-decoration: none; font-weight: bold; border-radius: 5px; }" +
                    ".footer { text-align: center; font-size: 12px; color: #666; margin-top: 20px; }" +
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "<div class='email-container'>" +
                    "<div class='header'>" +
                    "<h2>Welcome to Flames of Freedom 1956!</h2>" +
                    "</div>" +
                    "<div class='content'>" +
                    "<p>Thank you for your interest in our game <strong>Flames of Freedom 1956</strong>!</p>" +
                    "<p>Your registration to our webpage was successful, and we hope to see you playing when the full version comes out.</p>" +
                    "<p>Feel free to visit our community page to interact with other players, share feedback, and report possible bugs.</p>" +
                    "</div>" +
                    "<div class='footer'>" +
                    "<p>&copy; 2025 Flames of Freedom 1956. All rights reserved.</p>" +
                    "</div>" +
                    "</div>" +
                    "</body>" +
                    "</html>";
            message.setContent(msg, "text/html;charset=utf-8");
            
            Transport.send(message);
            
            return true;
        } catch(Exception ex){
            System.err.println("Hiba: " + ex.getLocalizedMessage());
            return false;
        }
    }
    
    public static Map<String, Object> sendTemporaryPass(String to, boolean ccMe) {
        Map<String, Object> response = new HashMap<>();
        try  {
            final String from = "FlamesOfFreedom1956@gmail.com";
            final String password = "ysun piml flco rjpo";
            String host = "smtp.gmail.com";

            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");
            
            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });
            session.setDebug(true);
            
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Temporary Password");
            
            String temporaryPassword = generateTemporaryPassword();
            
            String msg = "<!DOCTYPE html>" +
                    "<html>" +
                    "<head>" +
                    "<style>" +
                    "body { font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; }" +
                    ".email-container { max-width: 600px; background: white; padding: 20px; border-radius: 10px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1); }" +
                    ".header { text-align: center; }" +
                    ".header img { max-width: 150px; }" +
                    ".content { font-size: 16px; color: #333; line-height: 1.6; text-align: center; }" +
                    ".verification-code { font-size: 24px; font-weight: bold; color: #007bff; margin: 20px 0; }" +
                    ".footer { text-align: center; font-size: 12px; color: #666; margin-top: 20px; }" +
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "<div class='email-container'>" +
                    "<div class='header'>" +
                    "<h2>Temporary Password</h2>" +
                    "</div>" +
                    "<div class='content'>" +
                    "<p>You requested a password reset. Use the following temporary password to log in to your account:</p>" +
                    "<p class='verification-code'>" + temporaryPassword + "</p>" +
                    "<p>If you didn’t request a password reset, you can ignore this email.</p>" +
                    "</div>" +
                    "<div class='footer'>" +
                    "<p>&copy; 2025 Flames of Freedom 1956. All rights reserved.</p>" +
                    "</div>" +
                    "</div>" +
                    "</body>" +
                    "</html>";
            message.setContent(msg, "text/html;charset=utf-8");
            Transport.send(message);
            
            response.put("success", true);
            response.put("password", temporaryPassword);
        } catch(Exception ex) {
            response.put("success", false);
            response.put("error", ex.getLocalizedMessage());
        }
        return response;
    }
    
//    public static String generateVerificationCode() {
//        Random random = new Random();
//        int code = 100000 + random.nextInt(900000);
//        return String.valueOf(code);
//    }
    
    private static String generateTemporaryPassword() {
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digits = "0123456789";
        String specialChars = "!@#$%^&*()-_.";
        String allChars = upperCase + digits + specialChars + "abcdefghijklmnopqrstuvwxyz";
        
        SecureRandom random = new SecureRandom();
        StringBuilder randomPassword = new StringBuilder();
        
        randomPassword.append(upperCase.charAt(random.nextInt(upperCase.length())));
        randomPassword.append(digits.charAt(random.nextInt(digits.length())));
        randomPassword.append(specialChars.charAt(random.nextInt(specialChars.length())));
        
        for(int i = 3; i < 12 + random.nextInt(13); i++) {
            randomPassword.append(allChars.charAt(random.nextInt(allChars.length())));
        }
        
        return randomPassword.toString();
    }
    
    public static Boolean changePassword(Users u) {
        EntityManager em = emf.createEntityManager();

        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("forgotpass");  

            spq.registerStoredProcedureParameter("emailIn", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("passwordIn", String.class, ParameterMode.IN);

            spq.setParameter("emailIn", u.getEmail());
            spq.setParameter("passwordIn", u.getPassword());

            spq.execute();

            return true;
        } catch (Exception e) {
            System.err.println("Hiba: " + e.getLocalizedMessage());
            return false;
        } finally {
            em.clear();
            em.close();
        }
    }
}
