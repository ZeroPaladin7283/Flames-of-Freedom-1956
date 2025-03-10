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
@Table(name = "tutorials_x_characters")
@NamedQueries({@NamedQuery(name = "TutorialsXCharacters.findAll", query = "SELECT t FROM TutorialsXCharacters t"), @NamedQuery(name = "TutorialsXCharacters.findById", query = "SELECT t FROM TutorialsXCharacters t WHERE t.id = :id"), @NamedQuery(name = "TutorialsXCharacters.findByTutorialId", query = "SELECT t FROM TutorialsXCharacters t WHERE t.tutorialId = :tutorialId"), @NamedQuery(name = "TutorialsXCharacters.findByCharacterId", query = "SELECT t FROM TutorialsXCharacters t WHERE t.characterId = :characterId"), @NamedQuery(name = "TutorialsXCharacters.findByIsDeleted", query = "SELECT t FROM TutorialsXCharacters t WHERE t.isDeleted = :isDeleted"), @NamedQuery(name = "TutorialsXCharacters.findByDeletedAt", query = "SELECT t FROM TutorialsXCharacters t WHERE t.deletedAt = :deletedAt")})
public class TutorialsXCharacters implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Basic(optional = false) @Column(name = "id")
    private Integer id;
    @Basic(optional = false) @NotNull @Column(name = "tutorial_id")
    private int tutorialId;
    @Basic(optional = false) @NotNull @Column(name = "character_id")
    private int characterId;
    @Basic(optional = false) @NotNull @Column(name = "is_deleted")
    private boolean isDeleted;
    @Column(name = "deleted_at") @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

    public TutorialsXCharacters() {
    }

    public TutorialsXCharacters(Integer id) {
        this.id = id;
    }

    public TutorialsXCharacters(Integer id, int tutorialId, int characterId, boolean isDeleted) {
        this.id = id;
        this.tutorialId = tutorialId;
        this.characterId = characterId;
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

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
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
        if (!(object instanceof TutorialsXCharacters)) {
            return false;
        }
        TutorialsXCharacters other = (TutorialsXCharacters) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.javamvchelix.flames_of_freedom_1956.TutorialsXCharacters[ id=" + id + " ]";
    }
    
}
