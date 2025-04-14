/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javamvchelix.flames_of_freedom_1956.model;

import java.io.Serializable;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
@Table(name = "users")
@NamedQueries({@NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"), 
    @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id"), 
    @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"), 
    @NamedQuery(name = "Users.findByProfilePic", query = "SELECT u FROM Users u WHERE u.profilePic = :profilePic"), 
    @NamedQuery(name = "Users.findByGithubId", query = "SELECT u FROM Users u WHERE u.githubId = :githubId"), 
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"), 
    @NamedQuery(name = "Users.findByDateOfBirth", query = "SELECT u FROM Users u WHERE u.dateOfBirth = :dateOfBirth"), 
    @NamedQuery(name = "Users.findByIsAdmin", query = "SELECT u FROM Users u WHERE u.isAdmin = :isAdmin"), 
    @NamedQuery(name = "Users.findByRegisteredAt", query = "SELECT u FROM Users u WHERE u.registeredAt = :registeredAt"), 
    @NamedQuery(name = "Users.findByIsDeleted", query = "SELECT u FROM Users u WHERE u.isDeleted = :isDeleted"), 
    @NamedQuery(name = "Users.findByDeletedAt", query = "SELECT u FROM Users u WHERE u.deletedAt = :deletedAt")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Basic(optional = false) 
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false) 
    @NotNull 
    @Size(min = 1, max = 100) 
    @Column(name = "username")
    private String username;
    @Lob
    @Column(name = "profilePic")
    private byte[] profilePic;
    @Size(max = 100) 
    @Column(name = "github_id")
    private String githubId;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false) 
    @NotNull 
    @Size(min = 1, max = 100) 
    @Column(name = "email")
    private String email;
    @Basic(optional = false) 
    @NotNull 
    @Lob 
    @Size(min = 1, max = 65535) 
    @Column(name = "password")
    private String password;
    @Basic(optional = false) 
    @NotNull 
    @Column(name = "date_of_birth") 
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Basic(optional = false) 
    @NotNull 
    @Column(name = "is_admin")
    private boolean isAdmin;
    @Column(name = "registered_at") 
    @Temporal(TemporalType.TIMESTAMP)
    private Date registeredAt;
    @Basic(optional = false) 
    @NotNull 
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @Column(name = "deleted_at") 
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;
    @Transient
    private String base64Image;

    public Users() {
    }
    
    public Users(Integer id) {
        this.id = id;
    }

    public Users(Integer id, String username, byte[] profilePic, String githubId, String email, String password, Date dateOfBirth, boolean isAdmin, Date registeredAt, boolean isDeleted, Date deletedAt) {
        this.id = id;
        this.username = username;
        this.base64Image = profilePic != null ? Base64.getEncoder().encodeToString(profilePic) : null;
        this.githubId = githubId;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.isAdmin = isAdmin;
        this.registeredAt = registeredAt;
        this.isDeleted = isDeleted;
        this.deletedAt = deletedAt;
    }
    
    public Users(Integer id, String username, byte[] profilePic, String githubId, String email, String password, Date dateOfBirth, boolean isAdmin, Date registeredAt) {
        this.id = id;
        this.username = username;
        this.base64Image = profilePic != null ? Base64.getEncoder().encodeToString(profilePic) : null;
        this.githubId = githubId;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.isAdmin = isAdmin;
        this.registeredAt = registeredAt;
    }
    
    public Users(String username, String email, String password, Date dateOfBirth) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }
    
    public Users(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    
    public Users(Integer id, String username, String email, String password, byte[] profilePic) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.base64Image = profilePic != null ? Base64.getEncoder().encodeToString(profilePic) : null;
    }
    
    public Users(Integer id, String username, String email, String password, String profilePic) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.base64Image = profilePic;
    }

    public Users(Integer id, String username, byte[] profilePic, String githubId, String email, String password, Date dateOfBirth, Date registeredAt) {
        this.id = id;
        this.username = username;
        this.base64Image = profilePic != null ? Base64.getEncoder().encodeToString(profilePic) : null;
        this.githubId = githubId;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.registeredAt = registeredAt;
    }
    
    public Users(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte [] getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(byte [] profilePic) {
        this.profilePic = profilePic;
    }

    public String getGithubId() {
        return githubId;
    }

    public void setGithubId(String githubId) {
        this.githubId = githubId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Date getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.javamvchelix.flames_of_freedom_1956.Users[ id=" + id + " ]";
    }
}
