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
@Table(name = "bug_reports")
@NamedQueries({@NamedQuery(name = "BugReports.findAll", query = "SELECT b FROM BugReports b"), @NamedQuery(name = "BugReports.findById", query = "SELECT b FROM BugReports b WHERE b.id = :id"), @NamedQuery(name = "BugReports.findByUserId", query = "SELECT b FROM BugReports b WHERE b.userId = :userId"), @NamedQuery(name = "BugReports.findByStatus", query = "SELECT b FROM BugReports b WHERE b.status = :status"), @NamedQuery(name = "BugReports.findByBugCategoryId", query = "SELECT b FROM BugReports b WHERE b.bugCategoryId = :bugCategoryId"), @NamedQuery(name = "BugReports.findByReportedAt", query = "SELECT b FROM BugReports b WHERE b.reportedAt = :reportedAt"), @NamedQuery(name = "BugReports.findByIsDeleted", query = "SELECT b FROM BugReports b WHERE b.isDeleted = :isDeleted"), @NamedQuery(name = "BugReports.findByDeletedAt", query = "SELECT b FROM BugReports b WHERE b.deletedAt = :deletedAt")})
public class BugReports implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Basic(optional = false) @Column(name = "id")
    private Integer id;
    @Basic(optional = false) @NotNull @Column(name = "user_id")
    private int userId;
    @Basic(optional = false) @NotNull @Lob @Size(min = 1, max = 65535) @Column(name = "description")
    private String description;
    @Basic(optional = false) @NotNull @Size(min = 1, max = 50) @Column(name = "status")
    private String status;
    @Basic(optional = false) @NotNull @Column(name = "bug_category_id")
    private int bugCategoryId;
    @Basic(optional = false) @NotNull @Column(name = "reported_at") @Temporal(TemporalType.TIMESTAMP)
    private Date reportedAt;
    @Basic(optional = false) @NotNull @Column(name = "is_deleted")
    private boolean isDeleted;
    @Column(name = "deleted_at") @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

    public BugReports() {
    }

    public BugReports(Integer id) {
        this.id = id;
    }

    public BugReports(Integer id, int userId, String description, String status, int bugCategoryId, Date reportedAt, boolean isDeleted) {
        this.id = id;
        this.userId = userId;
        this.description = description;
        this.status = status;
        this.bugCategoryId = bugCategoryId;
        this.reportedAt = reportedAt;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBugCategoryId() {
        return bugCategoryId;
    }

    public void setBugCategoryId(int bugCategoryId) {
        this.bugCategoryId = bugCategoryId;
    }

    public Date getReportedAt() {
        return reportedAt;
    }

    public void setReportedAt(Date reportedAt) {
        this.reportedAt = reportedAt;
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
        if (!(object instanceof BugReports)) {
            return false;
        }
        BugReports other = (BugReports) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.javamvchelix.flames_of_freedom_1956.BugReports[ id=" + id + " ]";
    }
    
}
