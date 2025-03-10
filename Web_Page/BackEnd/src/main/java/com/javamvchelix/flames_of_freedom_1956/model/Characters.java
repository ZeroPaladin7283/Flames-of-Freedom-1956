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
@Table(name = "characters")
@NamedQueries({@NamedQuery(name = "Characters.findAll", query = "SELECT c FROM Characters c"), @NamedQuery(name = "Characters.findById", query = "SELECT c FROM Characters c WHERE c.id = :id"), @NamedQuery(name = "Characters.findByName", query = "SELECT c FROM Characters c WHERE c.name = :name"), @NamedQuery(name = "Characters.findByHealth", query = "SELECT c FROM Characters c WHERE c.health = :health"), @NamedQuery(name = "Characters.findByDamage", query = "SELECT c FROM Characters c WHERE c.damage = :damage"), @NamedQuery(name = "Characters.findByIsBoss", query = "SELECT c FROM Characters c WHERE c.isBoss = :isBoss"), @NamedQuery(name = "Characters.findByIsDeleted", query = "SELECT c FROM Characters c WHERE c.isDeleted = :isDeleted"), @NamedQuery(name = "Characters.findByDeletedAt", query = "SELECT c FROM Characters c WHERE c.deletedAt = :deletedAt")})
public class Characters implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Basic(optional = false) @Column(name = "id")
    private Integer id;
    @Basic(optional = false) @NotNull @Size(min = 1, max = 100) @Column(name = "name")
    private String name;
    @Basic(optional = false) @NotNull @Column(name = "health")
    private int health;
    @Basic(optional = false) @NotNull @Column(name = "damage")
    private int damage;
    @Basic(optional = false) @NotNull @Column(name = "is_boss")
    private boolean isBoss;
    @Basic(optional = false) @NotNull @Column(name = "is_deleted")
    private boolean isDeleted;
    @Column(name = "deleted_at") @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

    public Characters() {
    }

    public Characters(Integer id) {
        this.id = id;
    }

    public Characters(Integer id, String name, int health, int damage, boolean isBoss, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.isBoss = isBoss;
        this.isDeleted = isDeleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean getIsBoss() {
        return isBoss;
    }

    public void setIsBoss(boolean isBoss) {
        this.isBoss = isBoss;
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
        if (!(object instanceof Characters)) {
            return false;
        }
        Characters other = (Characters) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.javamvchelix.flames_of_freedom_1956.Characters[ id=" + id + " ]";
    }
    
}
