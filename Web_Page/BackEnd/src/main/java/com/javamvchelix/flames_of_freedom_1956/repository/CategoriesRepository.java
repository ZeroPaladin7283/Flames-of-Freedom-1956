/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javamvchelix.flames_of_freedom_1956.repository;

import com.javamvchelix.flames_of_freedom_1956.model.Categories;
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
public class CategoriesRepository {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.JavaMVCHelix_Flames_of_Freedom_1956_war_1.0-SNAPSHOTPU");
    
    public Categories findCategoriesById(Integer id) {
        EntityManager em = emf.createEntityManager();
        
        try {
            return em.find(Categories.class, id);
        } catch (Exception ex) {
            System.err.println("Hiba: " + ex.getLocalizedMessage());
        } finally {
            em.clear();
            em.close();
        }
        return null;
    }
    
    public List<Categories> getCategories() {
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getCategories");
            spq.execute();
            
            List<Categories> toReturn = new ArrayList();
            List<Object[]> resultList = spq.getResultList();
            
            for(Object[] record : resultList) {
                Categories c = new Categories(
                        Integer.valueOf(record[0].toString()),
                        record[1].toString()
                );
                toReturn.add(c);
            }
            
            return toReturn;
        }catch(Exception e) {
            System.err.println("Hiba: " + e.getLocalizedMessage());
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }
}
