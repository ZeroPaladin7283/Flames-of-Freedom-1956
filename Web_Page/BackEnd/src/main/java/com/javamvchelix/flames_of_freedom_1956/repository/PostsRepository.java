/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javamvchelix.flames_of_freedom_1956.repository;

import com.javamvchelix.flames_of_freedom_1956.model.Posts;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author shado
 */
public class PostsRepository {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.JavaMVCHelix_Flames_of_Freedom_1956_war_1.0-SNAPSHOTPU");
    
    public Posts findByPostsById(Integer id) {
        EntityManager em = emf.createEntityManager();
        
        try {
            return em.find(Posts.class, id);
        } catch (Exception ex) {
            System.err.println("Hiba: " + ex.getLocalizedMessage());
        } finally {
            em.clear();
            em.close();
        }
        return null;
    }
    
    public List<Posts> getAllPosts() {
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllPosts");
            spq.execute();
            
            List<Posts> toReturn = new ArrayList();
            List<Object[]> resultList = spq.getResultList();
            
            for(Object[] record : resultList) {
                Posts p = new Posts(
                        Integer.valueOf(record[0].toString()),
                        record[1].toString(),
                        record[2].toString(),
                        record[3] == null ? null : record[3].toString(),
                        record[4].toString(),
                        record[5].toString(),
                        record[6] != null ? (byte[]) record[6] : null
                );
                
                toReturn.add(p);
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
    
    public Posts getPostById(Integer id) {
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getPostById");
            
            spq.registerStoredProcedureParameter("idIn", Integer.class, ParameterMode.IN);
            
            spq.setParameter("idIn", id);
            
            spq.execute();
            
            List<Object[]> resultList = spq.getResultList();
            Posts toReturn = new Posts();
            
            for(Object[] o : resultList) {
                Posts p = new Posts(
                        o[0].toString(),
                        Integer.valueOf(o[1].toString()),
                        o[2] == null ? null : o[2].toString(),
                        o[3].toString(),
                        Integer.valueOf(o[4].toString())
                );
                
                toReturn = p;
                System.out.println(p);
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
    
    public Boolean deletePost(Posts p) {
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deletePost");
            
            spq.registerStoredProcedureParameter("idIn", Integer.class, ParameterMode.IN);
            
            spq.setParameter("idIn", p.getId());
            
            spq.execute();
            
            return true;
        } catch(Exception e) {
            System.err.println("Hiba: " + e.getLocalizedMessage());
            return false;
        } finally {
            em.clear();
            em.close();
        }
    }
    
    public Boolean createPost(Posts p) {
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("createPost");
            
            spq.registerStoredProcedureParameter("titleIn", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("categoryIdIn", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("contentIn", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("userIdIn", Integer.class, ParameterMode.IN);
            
            spq.setParameter("titleIn", p.getTitle());
            spq.setParameter("categoryIdIn", p.getCategoryId());
            spq.setParameter("contentIn", p.getContent());
            spq.setParameter("userIdIn", p.getUserId());
            
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
