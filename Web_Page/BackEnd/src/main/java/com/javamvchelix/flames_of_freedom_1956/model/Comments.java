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
@Table(name = "comments")
@NamedQueries({@NamedQuery(name = "Comments.findAll", query = "SELECT c FROM Comments c"), 
    @NamedQuery(name = "Comments.findById", query = "SELECT c FROM Comments c WHERE c.id = :id"), 
    @NamedQuery(name = "Comments.findByPostId", query = "SELECT c FROM Comments c WHERE c.postId = :postId"), 
    @NamedQuery(name = "Comments.findByUserId", query = "SELECT c FROM Comments c WHERE c.userId = :userId"), 
    @NamedQuery(name = "Comments.findByImageId", query = "SELECT c FROM Comments c WHERE c.imageId = :imageId"), 
    @NamedQuery(name = "Comments.findByCreatedAt", query = "SELECT c FROM Comments c WHERE c.createdAt = :createdAt"), 
    @NamedQuery(name = "Comments.findByIsDeleted", query = "SELECT c FROM Comments c WHERE c.isDeleted = :isDeleted"), 
    @NamedQuery(name = "Comments.findByDeletedAt", query = "SELECT c FROM Comments c WHERE c.deletedAt = :deletedAt")})
public class Comments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Basic(optional = false) 
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false) 
    @NotNull 
    @Column(name = "post_id")
    private int postId;
    @Basic(optional = false) 
    @NotNull 
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false) 
    @NotNull 
    @Lob 
    @Size(min = 1, max = 65535) 
    @Column(name = "content")
    private String content;
    @Basic(optional = false) 
    @NotNull 
    @Column(name = "image_id")
    private int imageId;
    @Basic(optional = false) 
    @NotNull 
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
    private String title;
    private String username;
    private String filePath;
    
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.JavaMVCHelix_Flames_of_Freedom_1956_war_1.0-SNAPSHOTPU");

    public Comments() {
    }

    public Comments(Integer id) {
        EntityManager em = emf.createEntityManager();
        
        try {
            Comments c = em.find(Comments.class, id);
            
            this.id = c.getId();
            this.postId = c.getPostId();
            this.userId = c.getUserId();
            this.content = c.getContent();
            this.imageId = c.getImageId();
            this.createdAt = c.getCreatedAt();
            this.isDeleted = c.getIsDeleted();
        } catch (Exception ex) {
            System.err.println("Hiba: " + ex.getLocalizedMessage());
        } finally {
            em.clear();
            em.close();
        }
    }

    public Comments(Integer id, int postId, int userId, String content, int imageId, Date createdAt, boolean isDeleted) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.imageId = imageId;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
    }
    
    public Comments(Integer id, String title, String username, String content, String filePath, Date createdAt, boolean isDeleted, Date deletedAt) {
        this.id = id;
        this.title = title;
        this.username = username;
        this.content = content;
        this.filePath = filePath;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
        this.deletedAt = deletedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
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
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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
        if (!(object instanceof Comments)) {
            return false;
        }
        Comments other = (Comments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.javamvchelix.flames_of_freedom_1956.Comments[ id=" + id + " ]";
    }
    
    public List<Comments> getCommentsData() {
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getCommentsData");
            spq.execute();
            
            List<Comments> toReturn = new ArrayList();
            List<Object[]> resultList = spq.getResultList();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            for(Object[] record : resultList) {
                Comments c = new Comments(
                        Integer.valueOf(record[0].toString()),
                        record[1].toString(),
                        record[2].toString(),
                        record[3].toString(),
                        record[4] == null ? null : record[4].toString(),
                        formatter.parse(record[5].toString()),
                        Boolean.parseBoolean(record[6].toString()),
                        record[7] == null ? null : formatter.parse(record[7].toString())
                );
                
                toReturn.add(c);
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
}
