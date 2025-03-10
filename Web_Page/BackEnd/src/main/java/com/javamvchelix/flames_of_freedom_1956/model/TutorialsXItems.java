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

/**
 *
 * @author shado
 */
@Entity
@Table(name = "tutorials_x_items")
@NamedQueries({@NamedQuery(name = "TutorialsXItems.findAll", query = "SELECT t FROM TutorialsXItems t"), @NamedQuery(name = "TutorialsXItems.findById", query = "SELECT t FROM TutorialsXItems t WHERE t.id = :id"), @NamedQuery(name = "TutorialsXItems.findByTutorialId", query = "SELECT t FROM TutorialsXItems t WHERE t.tutorialId = :tutorialId"), @NamedQuery(name = "TutorialsXItems.findByItemId", query = "SELECT t FROM TutorialsXItems t WHERE t.itemId = :itemId"), @NamedQuery(name = "TutorialsXItems.findByIsDeleted", query = "SELECT t FROM TutorialsXItems t WHERE t.isDeleted = :isDeleted"), @NamedQuery(name = "TutorialsXItems.findByDeletedAt", query = "SELECT t FROM TutorialsXItems t WHERE t.deletedAt = :deletedAt")})
public class TutorialsXItems implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Basic(optional = false) @Column(name = "id")
    private Integer id;
    @Basic(optional = false) @NotNull @Column(name = "tutorial_id")
    private int tutorialId;
    @Basic(optional = false) @NotNull @Column(name = "item_id")
    private int itemId;
    @Basic(optional = false) @NotNull @Column(name = "is_deleted")
    private boolean isDeleted;
    @Column(name = "deleted_at") @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

    public TutorialsXItems() {
    }

    public TutorialsXItems(Integer id) {
        this.id = id;
    }

    public TutorialsXItems(Integer id, int tutorialId, int itemId, boolean isDeleted) {
        this.id = id;
        this.tutorialId = tutorialId;
        this.itemId = itemId;
        this.isDeleted = isDeleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTutorialId() {
        return tutorialId;
    }

    public void setTutorialId(int tutorialId) {
        this.tutorialId = tutorialId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
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
        if (!(object instanceof TutorialsXItems)) {
            return false;
        }
        TutorialsXItems other = (TutorialsXItems) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.javamvchelix.flames_of_freedom_1956.TutorialsXItems[ id=" + id + " ]";
    }
    
}
