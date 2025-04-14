/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javamvchelix.flames_of_freedom_1956.repository;

import com.javamvchelix.flames_of_freedom_1956.model.Logs;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class LogsRepository {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.JavaMVCHelix_Flames_of_Freedom_1956_war_1.0-SNAPSHOTPU");
    
    public Logs findLogsById(Integer id) {
        EntityManager em = emf.createEntityManager();
        
        try {
            return em.find(Logs.class, id);
        } catch (Exception ex) {
            System.err.println("Hiba: " + ex.getLocalizedMessage());
        } finally {
            em.clear();
            em.close();
        }
        return null;
    }
    
    public List<Logs> getAllLogs() {
        EntityManager em = emf.createEntityManager();
        
        try{
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllLogs");
            spq.execute();
            
            List<Logs> toReturn = new ArrayList();
            List<Object[]> resultList = spq.getResultList();
            
            for(Object[] record : resultList) {
                Logs l = new Logs(
                        Integer.valueOf(record[0].toString()),
                        record[1] != null ? (byte[]) record[1] : null,
                        record[2].toString(),
                        record[3].toString(),
                        record[4].toString()
                );
                
                toReturn.add(l);
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
    
    public Logs getLogById(Integer id) {
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getLogById");
            
            spq.registerStoredProcedureParameter("idIn", Integer.class, ParameterMode.IN);
            
            spq.setParameter("idIn", id);
            
            spq.execute();
            
            List<Object[]> resultList = spq.getResultList();
            Logs toReturn = new Logs();
            
            for(Object[] o : resultList) {
                Logs l = new Logs(
                        Integer.valueOf(o[0].toString()),
                        Integer.valueOf(o[1].toString()),
                        o[2].toString(),
                        o[3].toString()
                );
                toReturn = l;
                System.out.println(l);
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
    
    public Boolean deleteLog(Logs l) {
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteLog");
            
            spq.registerStoredProcedureParameter("idIn", Integer.class, ParameterMode.IN);
            
            spq.setParameter("idIn", l.getId());
            
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
    
    public Boolean createLog(Logs l) {
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("createLog");
            
            spq.registerStoredProcedureParameter("adminIdIn", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("logIn", String.class, ParameterMode.IN);
            
            spq.setParameter("adminIdIn", l.getAdminId());
            spq.setParameter("logIn", l.getLog());
            
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
