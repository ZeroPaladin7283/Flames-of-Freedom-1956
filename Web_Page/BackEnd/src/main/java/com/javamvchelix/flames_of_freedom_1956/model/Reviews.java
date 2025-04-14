/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javamvchelix.flames_of_freedom_1956.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author shado
 */
@Entity
@Table(name = "reviews")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Reviews.findAll", query = "SELECT r FROM Reviews r"), 
    @NamedQuery(name = "Reviews.findById", query = "SELECT r FROM Reviews r WHERE r.id = :id"), 
    @NamedQuery(name = "Reviews.findByUserId", query = "SELECT r FROM Reviews r WHERE r.userId = :userId"), 
    @NamedQuery(name = "Reviews.findByCreatedAt", query = "SELECT r FROM Reviews r WHERE r.createdAt = :createdAt"), 
    @NamedQuery(name = "Reviews.findByIsDeleted", query = "SELECT r FROM Reviews r WHERE r.isDeleted = :isDeleted"), 
    @NamedQuery(name = "Reviews.findByDeletedAt", query = "SELECT r FROM Reviews r WHERE r.deletedAt = :deletedAt")})
public class Reviews implements Serializable {

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
    @Column(name = "review")
    private String review;
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
    private String username;
    @Transient
    private byte[] profilePic;
    @Transient
    private String base64Image;

    public Reviews() {
    }
    
    public Reviews(Integer id){
        this.id = id;
    }

    public Reviews(Integer id, int userId, String review, Date createdAt, boolean isDeleted) {
        this.id = id;
        this.userId = userId;
        this.review = review;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
    }
    
    public Reviews(Integer id, String username, byte[] profilePic, String review){
        this.id = id;
        this.username = username;
        this.base64Image = profilePic != null ? Base64.getEncoder().encodeToString(profilePic) : null;
        this.review = review;
    }
    
    public Reviews(Integer id, String username, String profilePic, String review, Date createdAt) {
        this.id = id;
        this.username = username;
        this.base64Image = profilePic;
        this.review = review;
        this.createdAt = createdAt;
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

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
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
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public byte[] getProfilePic(){
        return profilePic;
    }
    
    public void setProfilePic(byte[] profilePic){
        this.profilePic = profilePic;
    }
    
    public String getBase64Image() {
        return base64Image;
    }
    
    public void setBase64Image(String base64Image){
        this.base64Image = base64Image;
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
        if (!(object instanceof Reviews)) {
            return false;
        }
        Reviews other = (Reviews) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.javamvchelix.flames_of_freedom_1956.model.Reviews[ id=" + id + " ]";
    }
    
}
