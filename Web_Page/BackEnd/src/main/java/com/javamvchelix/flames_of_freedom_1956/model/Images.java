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
@Table(name = "images")
@NamedQueries({@NamedQuery(name = "Images.findAll", query = "SELECT i FROM Images i"), @NamedQuery(name = "Images.findById", query = "SELECT i FROM Images i WHERE i.id = :id"), @NamedQuery(name = "Images.findByFileName", query = "SELECT i FROM Images i WHERE i.fileName = :fileName"), @NamedQuery(name = "Images.findByFilePath", query = "SELECT i FROM Images i WHERE i.filePath = :filePath"), @NamedQuery(name = "Images.findByUploadedAt", query = "SELECT i FROM Images i WHERE i.uploadedAt = :uploadedAt"), @NamedQuery(name = "Images.findByIsDeleted", query = "SELECT i FROM Images i WHERE i.isDeleted = :isDeleted"), @NamedQuery(name = "Images.findByDeletedAt", query = "SELECT i FROM Images i WHERE i.deletedAt = :deletedAt")})
public class Images implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Basic(optional = false) @Column(name = "id")
    private Integer id;
    @Basic(optional = false) @NotNull @Size(min = 1, max = 255) @Column(name = "file_name")
    private String fileName;
    @Basic(optional = false) @NotNull @Size(min = 1, max = 255) @Column(name = "file_path")
    private String filePath;
    @Basic(optional = false) @NotNull @Column(name = "uploaded_at") @Temporal(TemporalType.TIMESTAMP)
    private Date uploadedAt;
    @Basic(optional = false) @NotNull @Column(name = "is_deleted")
    private boolean isDeleted;
    @Column(name = "deleted_at") @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

    public Images() {
    }

    public Images(Integer id) {
        this.id = id;
    }

    public Images(Integer id, String fileName, String filePath, Date uploadedAt, boolean isDeleted) {
        this.id = id;
        this.fileName = fileName;
        this.filePath = filePath;
        this.uploadedAt = uploadedAt;
        this.isDeleted = isDeleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(Date uploadedAt) {
        this.uploadedAt = uploadedAt;
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
        if (!(object instanceof Images)) {
            return false;
        }
        Images other = (Images) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.javamvchelix.flames_of_freedom_1956.Images[ id=" + id + " ]";
    }
    
}
