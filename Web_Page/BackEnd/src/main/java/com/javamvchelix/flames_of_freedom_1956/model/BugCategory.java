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
@Table(name = "bug_category")
@NamedQueries({@NamedQuery(name = "BugCategory.findAll", query = "SELECT b FROM BugCategory b"), @NamedQuery(name = "BugCategory.findById", query = "SELECT b FROM BugCategory b WHERE b.id = :id"), @NamedQuery(name = "BugCategory.findByBugCategory", query = "SELECT b FROM BugCategory b WHERE b.bugCategory = :bugCategory"), @NamedQuery(name = "BugCategory.findByIsDeleted", query = "SELECT b FROM BugCategory b WHERE b.isDeleted = :isDeleted"), @NamedQuery(name = "BugCategory.findByDeletedAt", query = "SELECT b FROM BugCategory b WHERE b.deletedAt = :deletedAt")})
public class BugCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Basic(optional = false) @Column(name = "id")
    private Integer id;
    @Basic(optional = false) @NotNull @Size(min = 1, max = 100) @Column(name = "bug_category")
    private String bugCategory;
    @Basic(optional = false) @NotNull @Column(name = "is_deleted")
    private boolean isDeleted;
    @Column(name = "deleted_at") @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

    public BugCategory() {
    }

    public BugCategory(Integer id) {
        this.id = id;
    }

    public BugCategory(Integer id, String bugCategory, boolean isDeleted) {
        this.id = id;
        this.bugCategory = bugCategory;
        this.isDeleted = isDeleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBugCategory() {
        return bugCategory;
    }

    public void setBugCategory(String bugCategory) {
        this.bugCategory = bugCategory;
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
        if (!(object instanceof BugCategory)) {
            return false;
        }
        BugCategory other = (BugCategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.javamvchelix.flames_of_freedom_1956.BugCategory[ id=" + id + " ]";
    }
    
}
