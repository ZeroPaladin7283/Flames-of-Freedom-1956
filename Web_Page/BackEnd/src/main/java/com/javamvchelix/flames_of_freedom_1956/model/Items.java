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
@Table(name = "items")
@NamedQueries({@NamedQuery(name = "Items.findAll", query = "SELECT i FROM Items i"), @NamedQuery(name = "Items.findById", query = "SELECT i FROM Items i WHERE i.id = :id"), @NamedQuery(name = "Items.findByName", query = "SELECT i FROM Items i WHERE i.name = :name"), @NamedQuery(name = "Items.findByType", query = "SELECT i FROM Items i WHERE i.type = :type"), @NamedQuery(name = "Items.findByDamage", query = "SELECT i FROM Items i WHERE i.damage = :damage"), @NamedQuery(name = "Items.findByDescription", query = "SELECT i FROM Items i WHERE i.description = :description"), @NamedQuery(name = "Items.findByIsDeleted", query = "SELECT i FROM Items i WHERE i.isDeleted = :isDeleted"), @NamedQuery(name = "Items.findByDeletedAt", query = "SELECT i FROM Items i WHERE i.deletedAt = :deletedAt")})
public class Items implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Basic(optional = false) @Column(name = "id")
    private Integer id;
    @Basic(optional = false) @NotNull @Size(min = 1, max = 100) @Column(name = "name")
    private String name;
    @Basic(optional = false) @NotNull @Size(min = 1, max = 100) @Column(name = "type")
    private String type;
    @Basic(optional = false) @NotNull @Column(name = "damage")
    private int damage;
    @Basic(optional = false) @NotNull @Size(min = 1, max = 255) @Column(name = "description")
    private String description;
    @Basic(optional = false) @NotNull @Column(name = "is_deleted")
    private boolean isDeleted;
    @Column(name = "deleted_at") @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

    public Items() {
    }

    public Items(Integer id) {
        this.id = id;
    }

    public Items(Integer id, String name, String type, int damage, String description, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.damage = damage;
        this.description = description;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof Items)) {
            return false;
        }
        Items other = (Items) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.javamvchelix.flames_of_freedom_1956.Items[ id=" + id + " ]";
    }
    
}
