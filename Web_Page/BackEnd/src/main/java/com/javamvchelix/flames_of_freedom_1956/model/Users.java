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
@Table(name = "users")
@NamedQueries({@NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"), 
    @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id"), 
    @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"), 
    @NamedQuery(name = "Users.findByImageId", query = "SELECT u FROM Users u WHERE u.imageId = :imageId"), 
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
    @Basic(optional = false) 
    @NotNull 
    @Column(name = "image_id")
    private int imageId;
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
    
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.JavaMVCHelix_Flames_of_Freedom_1956_war_1.0-SNAPSHOTPU");

    public Users() {
    }

    public Users(Integer id) {
        EntityManager em = emf.createEntityManager();
        
        try {
            Users u = em.find(Users.class, id);
            
            this.id = u.getId();
            this.username = u.getUsername();
            this.email = u.getEmail();
            this.password = u.getPassword();
            this.dateOfBirth = u.getDateOfBirth();
            this.isAdmin = u.getIsAdmin();
            this.registeredAt = u.getRegisteredAt();
            this.isDeleted = u.getIsDeleted();
        } catch (Exception ex) {
            System.err.println("Hiba: " + ex.getLocalizedMessage());
        } finally {
            em.clear();
            em.close();
        }
    }

    public Users(Integer id, String username, int imageId, String githubId, String email, String password, Date dateOfBirth, boolean isAdmin, Date registeredAt, boolean isDeleted, Date deletedAt) {
        this.id = id;
        this.username = username;
        this.imageId = imageId;
        this.githubId = githubId;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.isAdmin = isAdmin;
        this.registeredAt = registeredAt;
        this.isDeleted = isDeleted;
        this.deletedAt = deletedAt;
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

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
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
    
    public Users login(String email, String password) {
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("login");
            
            spq.registerStoredProcedureParameter("emailIn", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("passwordIn", String.class, ParameterMode.IN);
            
            spq.setParameter("emailIn", email);
            spq.setParameter("passwordIn", password);
            
            spq.execute();
            
            List<Object[]> resultList = spq.getResultList();
            Users toReturn = new Users();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
            
            for(Object[] o : resultList) {
                Users u = new Users(
                        Integer.valueOf(o[0].toString()),
                        o[1].toString(),
                        Integer.valueOf(o[2].toString()),
                        o[3] == null ? null : o[3].toString(),
                        o[4].toString(),
                        o[5].toString(),
                        formatter2.parse(o[6].toString()),
                        Boolean.parseBoolean(o[7].toString()),
                        formatter.parse(o[8].toString()),
                        Boolean.parseBoolean(o[9].toString()),
                        o[10] == null ? null : formatter.parse(o[10].toString())
                );
                toReturn = u;
                System.out.println(u);
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
    
    public Boolean registerUser(Users u) {
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("registerUser");
            
            spq.registerStoredProcedureParameter("usernameIn", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("emailIn", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("passwordIn", String.class , ParameterMode.IN);
            spq.registerStoredProcedureParameter("birthIn", Date.class , ParameterMode.IN);
            
            spq.setParameter("usernameIn", u.getUsername());
            spq.setParameter("emailIn", u.getEmail());
            spq.setParameter("passwordIn", u.getPassword());
            spq.setParameter("birthIn", u.getDateOfBirth());
            
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
    
    public static Boolean isUserExists(String email) {
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("isUserExists");
            
            spq.registerStoredProcedureParameter("emailIn", String.class , ParameterMode.IN);
            spq.registerStoredProcedureParameter("resultOut", Boolean.class , ParameterMode.OUT);
            
            spq.setParameter("emailIn", email);
            
            spq.execute();
            
            Boolean result = Boolean.valueOf(spq.getOutputParameterValue("resultOut").toString());
            
            return result;
        } catch (Exception e) {
            System.err.println("Hiba: " + e.getLocalizedMessage());
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }
    
    public Boolean registerAdmin(Users u) {
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("registerAdmin");
            
            spq.registerStoredProcedureParameter("usernameIn", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("emailIn", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("passwordIn", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("dateOfBirthIn", String.class, ParameterMode.IN);
            
            spq.setParameter("usernameIn", u.getUsername());
            spq.setParameter("emailIn", u.getEmail());
            spq.setParameter("passwordIn", u.getPassword());
            spq.setParameter("dateOfBirthIn", u.getDateOfBirth());
            
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
    
    public List<Users> getAllUser() {
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllUser");
            spq.execute();
            
            List<Users> toReturn = new ArrayList();
            List<Object[]> resultList = spq.getResultList();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
            for (Object[] record : resultList) {
                Users u = new Users(
                        Integer.valueOf(record[0].toString()),
                        record[1].toString(),
                        Integer.valueOf(record[2].toString()),
                        record[3] == null ? null : record[3].toString(),
                        record[4].toString(),
                        record[5].toString(),
                        formatter2.parse(record[6].toString()),
                        Boolean.parseBoolean(record[7].toString()),
                        formatter.parse(record[8].toString()),
                        Boolean.parseBoolean(record[9].toString()),
                        record[10] == null ? null : formatter.parse(record[10].toString())
                );
                
                toReturn.add(u);
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
    
    public Users getUserById(Integer id) {
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getUserById");
            
            spq.registerStoredProcedureParameter("idIn", Integer.class , ParameterMode.IN);
            
            spq.setParameter("idIn", id);
            
            spq.execute();
            
            List<Object[]> resultList = spq.getResultList();
            Users toReturn = new Users();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
            
            for(Object[] o : resultList) {
                Users u = new Users(
                        Integer.valueOf(o[0].toString()),
                        o[1].toString(),
                        Integer.valueOf(o[2].toString()),
                        o[3] == null ? null : o[3].toString(),
                        o[4].toString(),
                        o[5].toString(),
                        formatter2.parse(o[6].toString()),
                        Boolean.parseBoolean(o[7].toString()),
                        formatter.parse(o[8].toString()),
                        Boolean.parseBoolean(o[9].toString()),
                        o[10] == null ? null : formatter.parse(o[10].toString())
                );
                toReturn = u;
                System.out.println(u);
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
    
    public List<Users> getDevelopers() {
        EntityManager em = emf.createEntityManager();
        
        try{
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getDevelopers");
            spq.execute();
            
            List<Users> toReturn = new ArrayList();
            List<Object[]> resultList = spq.getResultList();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
            
            for(Object[] record : resultList) {
                Users u = new Users(
                        Integer.valueOf(record[0].toString()),
                        record[1].toString(),
                        Integer.valueOf(record[2].toString()),
                        record[3] == null ? null : record[3].toString(),
                        record[4].toString(),
                        record[5].toString(),
                        formatter2.parse(record[6].toString()),
                        Boolean.parseBoolean(record[7].toString()),
                        formatter.parse(record[8].toString()),
                        Boolean.parseBoolean(record[9].toString()),
                        record[10] == null ? null : formatter.parse(record[10].toString())
                );
                
                toReturn.add(u);
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
