/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javamvchelix.flames_of_freedom_1956.repository;

import com.javamvchelix.flames_of_freedom_1956.model.Reviews;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author shado
 */
public class ReviewRepository {
    /* Bemásolom, az ID-s constructort, illetve az EMF-es Szart*/
    /* Az összes függvényt */
    /* return em.find(osztály neve.class, id); az id-s contructorban */
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.JavaMVCHelix_Flames_of_Freedom_1956_war_1.0-SNAPSHOTPU");
    
    public Reviews findReviewsById(Integer id) {
        EntityManager em = emf.createEntityManager();
        
        try{
            return em.find(Reviews.class, id);
        } catch (Exception ex) {
            System.err.println("Hba: " + ex.getLocalizedMessage());
        } finally {
            em.clear();
            em.close();
        }
        return null;
    }
    
    public List<Reviews> getReviews(){
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getReviews");
            spq.execute();
            
            List<Reviews> toReturn = new ArrayList();
            List<Object[]> resultList = spq.getResultList();
            
            for(Object[] record : resultList) {
                Reviews r = new Reviews(
                        Integer.valueOf(record[0].toString()),
                        record[1].toString(),
                        record[2] != null ? (byte[]) record[2] : null,
                        record[3].toString()
                );
                
                toReturn.add(r);
            }
            
            return toReturn;
        }catch (Exception e) {
            System.err.println("Hiba: " + e.getLocalizedMessage());
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }
}
