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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author shado
 */
@Entity
@Table(name = "posts")
@NamedQueries({@NamedQuery(name = "Posts.findAll", query = "SELECT p FROM Posts p"), 
    @NamedQuery(name = "Posts.findById", query = "SELECT p FROM Posts p WHERE p.id = :id"), 
    @NamedQuery(name = "Posts.findByTitle", query = "SELECT p FROM Posts p WHERE p.title = :title"), 
    @NamedQuery(name = "Posts.findByCategoryId", query = "SELECT p FROM Posts p WHERE p.categoryId = :categoryId"), 
    @NamedQuery(name = "Posts.findByImageId", query = "SELECT p FROM Posts p WHERE p.imageId = :imageId"), 
    @NamedQuery(name = "Posts.findByUserId", query = "SELECT p FROM Posts p WHERE p.userId = :userId"), 
    @NamedQuery(name = "Posts.findByCreatedAt", query = "SELECT p FROM Posts p WHERE p.createdAt = :createdAt"), 
    @NamedQuery(name = "Posts.findByIsDeleted", query = "SELECT p FROM Posts p WHERE p.isDeleted = :isDeleted"), 
    @NamedQuery(name = "Posts.findByDeletedAt", query = "SELECT p FROM Posts p WHERE p.deletedAt = :deletedAt")})
public class Posts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Basic(optional = false) 
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false) 
    @NotNull 
    @Size(min = 1, max = 100) 
    @Column(name = "title")
    private String title;
    @Basic(optional = false) 
    @NotNull 
    @Column(name = "category_id")
    private int categoryId;
    @Basic(optional = false) 
    @NotNull 
    @Column(name = "image_id")
    private int imageId;
    @Basic(optional = false) 
    @NotNull 
    @Lob @Size(min = 1, max = 65535) 
    @Column(name = "content")
    private String content;
    @Basic(optional = false) 
    @NotNull 
    @Column(name = "user_id")
    private int userId;
    @Column(name = "created_at") 
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false) 
    @NotNull 
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @Column(name = "deleted_at") 
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;
    @Transient
    private String username;
    @Transient
    private String filePath;
    @Transient
    private String category;
    
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.JavaMVCHelix_Flames_of_Freedom_1956_war_1.0-SNAPSHOTPU");

    public Posts() {
    }

    public Posts(Integer id) {
        EntityManager em = emf.createEntityManager();
        
        try {
            Posts p = em.find(Posts.class, id);
            
            this.id = p.getId();
            this.title = p.getTitle();
            this.categoryId = p.getCategoryId();
            this.imageId = p.getImageId();
            this.content = p.getContent();
            this.userId = p.getUserId();
            this.createdAt = p.getCreatedAt();
            this.isDeleted = p.getIsDeleted();
        } catch (Exception ex) {
            System.err.println("Hiba: " + ex.getLocalizedMessage());
        } finally {
            em.clear();
            em.close();
        }
    }
    
    public Posts(Integer id, String title, int categoryId, int imageId, String content, int userId, Date createdAt, boolean isDeleted, Date deletedAt) {
        this.id = id;
        this.title = title;
        this.categoryId = categoryId;
        this.imageId = imageId;
        this.content = content;
        this.userId = userId;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
        this.deletedAt = deletedAt;
    }
    
    public Posts(Integer id, String title, String category, String filePath, String content, String username, Date createdAt, boolean isDeleted, Date deletedAt) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.filePath = filePath;
        this.content = content;
        this.username = username;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
        this.deletedAt = deletedAt;
    }

    public Posts(Integer id, String title, int categoryId, int imageId, String content, int userId, boolean isDeleted) {
        this.id = id;
        this.title = title;
        this.categoryId = categoryId;
        this.imageId = imageId;
        this.content = content;
        this.userId = userId;
        this.isDeleted = isDeleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getFilePath() {
        return filePath;
    }
    
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
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
        if (!(object instanceof Posts)) {
            return false;
        }
        Posts other = (Posts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.javamvchelix.flames_of_freedom_1956.Posts[ id=" + id + " ]";
    }
    
    public List<Posts> getAllPosts() {
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllPosts");
            spq.execute();
            
            List<Posts> toReturn = new ArrayList();
            List<Object[]> resultList = spq.getResultList();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            for(Object[] record : resultList) {
                Posts p = new Posts(
                        Integer.valueOf(record[0].toString()),
                        record[1].toString(),
                        Integer.valueOf(record[2].toString()),
                        Integer.valueOf(record[3].toString()),
                        record[4].toString(),
                        Integer.valueOf(record[5].toString()),
                        formatter.parse(record[6].toString()),
                        Boolean.parseBoolean(record[7].toString()),
                        record[8] == null ? null : formatter.parse(record[8].toString())
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
    
    public List<Posts> getPostData() {
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getPostData");
            spq.execute();
            
            List<Posts> toReturn = new ArrayList();
            List<Object[]> resultList = spq.getResultList();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            for(Object[] record : resultList) {
                Posts p = new Posts(
                        Integer.valueOf(record[0].toString()),
                        record[1].toString(),
                        record[2].toString(),
                        record[3] == null ? null : record[3].toString(),
                        record[4].toString(),
                        record[5].toString(),
                        formatter.parse(record[6].toString()),
                        Boolean.parseBoolean(record[7].toString()),
                        record[8] == null ? null : formatter.parse(record[8].toString())
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
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            for(Object[] o : resultList) {
                Posts p = new Posts(
                        Integer.valueOf(o[0].toString()),
                        o[1].toString(),
                        Integer.valueOf(o[2].toString()),
                        Integer.valueOf(o[3].toString()),
                        o[4].toString(),
                        Integer.valueOf(o[5].toString()),
                        formatter.parse(o[6].toString()),
                        Boolean.parseBoolean(o[7].toString()),
                        o[8] == null ? null : formatter.parse(o[8].toString())
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
    
}
