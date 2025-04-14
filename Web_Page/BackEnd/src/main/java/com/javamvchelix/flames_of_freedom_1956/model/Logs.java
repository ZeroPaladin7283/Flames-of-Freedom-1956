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
@Table(name = "logs")
@NamedQueries({@NamedQuery(name = "Logs.findAll", query = "SELECT l FROM Logs l"), 
    @NamedQuery(name = "Logs.findById", query = "SELECT l FROM Logs l WHERE l.id = :id"), 
    @NamedQuery(name = "Logs.findByAdminId", query = "SELECT l FROM Logs l WHERE l.adminId = :adminId"), 
    @NamedQuery(name = "Logs.findByLog", query = "SELECT l FROM Logs l WHERE l.log = :log"),
    @NamedQuery(name = "Logs.findByStatus", query= "SELECT l FROM Logs l WHERE l.status = :status"),
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
    @Column(name = "admin_id")
    private int adminId;
    @Basic(optional = false) 
    @NotNull 
    @Lob 
    @Size(min = 1, max = 65535) 
    @Column(name = "log")
    private String log;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "status")
    private String status;
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
    @Transient
    private String username;
    @Transient
    private byte[] profilePic;
    @Transient
    private String base64Image;
    
    public Logs() {
    }
    
    public Logs(Integer id, int adminId, String log, String status) {
        this.id = id;
        this.adminId = adminId;
        this.log = log;
        this.status = status;
    }
    
    public Logs(Integer id, byte[] profilePic, String username, String log, String status) {
        this.id = id;
        this.base64Image = profilePic != null ? Base64.getEncoder().encodeToString(profilePic) : null;
        this.username = username;
        this.log = log;
        this.status = status;
    }
    
    public Logs(int adminId, String log) {
        this.adminId = adminId;
        this.log = log;
    }
    
    public Logs(Integer id){
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
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

    public byte[] getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(byte[] profilePic) {
        this.profilePic = profilePic;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
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
}
