/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javamvchelix.flames_of_freedom_1956.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "tutorials")
@NamedQueries({@NamedQuery(name = "Tutorials.findAll", query = "SELECT t FROM Tutorials t"), @NamedQuery(name = "Tutorials.findById", query = "SELECT t FROM Tutorials t WHERE t.id = :id"), @NamedQuery(name = "Tutorials.findByTitle", query = "SELECT t FROM Tutorials t WHERE t.title = :title"), @NamedQuery(name = "Tutorials.findByCreatedAt", query = "SELECT t FROM Tutorials t WHERE t.createdAt = :createdAt"), @NamedQuery(name = "Tutorials.findByIsDeleted", query = "SELECT t FROM Tutorials t WHERE t.isDeleted = :isDeleted"), @NamedQuery(name = "Tutorials.findByDeletedAt", query = "SELECT t FROM Tutorials t WHERE t.deletedAt = :deletedAt")})
public class Tutorials implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Basic(optional = false) @Column(name = "id")
    private Integer id;
    @Basic(optional = false) @NotNull @Size(min = 1, max = 100) @Column(name = "title")
    private String title;
    @Basic(optional = false) @NotNull @Lob @Size(min = 1, max = 65535) @Column(name = "content")
    private String content;
    @Column(name = "created_at") @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false) @NotNull @Column(name = "is_deleted")
    private boolean isDeleted;
    @Column(name = "deleted_at") @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

    public Tutorials() {
    }

    public Tutorials(Integer id) {
        this.id = id;
    }

    public Tutorials(Integer id, String title, String content, boolean isDeleted) {
        this.id = id;
        this.title = title;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        if (!(object instanceof Tutorials)) {
            return false;
        }
        Tutorials other = (Tutorials) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.javamvchelix.flames_of_freedom_1956.Tutorials[ id=" + id + " ]";
    }
    
}
