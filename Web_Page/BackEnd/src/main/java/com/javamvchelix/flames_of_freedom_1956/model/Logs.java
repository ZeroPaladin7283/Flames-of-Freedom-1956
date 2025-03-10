/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javamvchelix.flames_of_freedom_1956.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author shado
 */
@Entity
@Table(name = "logs")
@NamedQueries({@NamedQuery(name = "Logs.findAll", query = "SELECT l FROM Logs l"), 
    @NamedQuery(name = "Logs.findById", query = "SELECT l FROM Logs l WHERE l.id = :id"), 
    @NamedQuery(name = "Logs.findByUserId", query = "SELECT l FROM Logs l WHERE l.userId = :userId"), 
    @NamedQuery(name = "Logs.findByCreatedAt", query = "SELECT l FROM Logs l WHERE l.createdAt = :createdAt"), 
    @NamedQuery(name = "Logs.findByIsDeleted", query = "SELECT l FROM Logs l WHERE l.isDeleted = :isDeleted"), 
    @NamedQuery(name = "Logs.findByDeletedAt", query = "SELECT l FROM Logs l WHERE l.deletedAt = :deletedAt")})
public class Logs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Basic(optional = false) 
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false) 
    @NotNull 
    @Lob 
    @Size(min = 1, max = 65535) 
    @Column(name = "log")
    private String log;
    @Column(name = "created_at") 
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @Basic(optional = false) 
    @NotNull 
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @Column(name = "deleted_at") 
    @Temporal(TemporalType.DATE)
    private Date deletedAt;

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.JavaMVCHelix_Flames_of_Freedom_1956_war_1.0-SNAPSHOTPU");
    
    public Logs() {
    }

    public Logs(Integer id) {
        EntityManager em = emf.createEntityManager();
        
        try {
            Logs l = em.find(Logs.class, id);
            
            this.id = l.getId();
            this.userId = l.getUserId();
            this.log = l.getLog();
            this.isDeleted = l.getIsDeleted();
        } catch (Exception ex) {
            System.err.println("Hiba: " + ex.getLocalizedMessage());
        } finally {
            em.clear();
            em.close();
        }
    }
    
    public Logs(Integer id, int userId, String log, Date createdAt, boolean isDeleted, Date deletedAt) {
        this.id = id;
        this.userId = userId;
        this.log = log;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
        this.deletedAt = deletedAt;
    }

    public Logs(Integer id, int userId, String log, boolean isDeleted) {
        this.id = id;
        this.userId = userId;
        this.log = log;
        this.isDeleted = isDeleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logs)) {
            return false;
        }
        Logs other = (Logs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.javamvchelix.flames_of_freedom_1956.Logs[ id=" + id + " ]";
    }
    
    public List<Logs> getAllLogs() {
        EntityManager em = emf.createEntityManager();
        
        try{
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllLogs");
            spq.execute();
            
            List<Logs> toReturn = new ArrayList();
            List<Object[]> resultList = spq.getResultList();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            for(Object[] record : resultList) {
                Logs l = new Logs(
                        Integer.valueOf(record[0].toString()),
                        Integer.valueOf(record[1].toString()),
                        record[2].toString(),
                        formatter.parse(record[3].toString()),
                        Boolean.parseBoolean(record[4].toString()),
                        record[5] == null ? null : formatter.parse(record[5].toString())
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
            
            spq.registerStoredProcedureParameter("idIn", String.class, ParameterMode.IN);
            
            spq.setParameter("idIn", id);
            
            spq.execute();
            
            List<Object[]> resultList = spq.getResultList();
            Logs toReturn = new Logs();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            for(Object[] o : resultList) {
                Logs l = new Logs(
                        Integer.valueOf(o[0].toString()),
                        Integer.valueOf(o[1].toString()),
                        o[2].toString(),
                        formatter.parse(o[3].toString()),
                        Boolean.parseBoolean(o[4].toString()),
                        o[5] == null ? null : formatter.parse(o[5].toString())
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
}
