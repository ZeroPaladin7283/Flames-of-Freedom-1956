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
@Table(name = "comment_ratings")
@NamedQueries({@NamedQuery(name = "CommentRatings.findAll", query = "SELECT c FROM CommentRatings c"), @NamedQuery(name = "CommentRatings.findById", query = "SELECT c FROM CommentRatings c WHERE c.id = :id"), @NamedQuery(name = "CommentRatings.findByCommentId", query = "SELECT c FROM CommentRatings c WHERE c.commentId = :commentId"), @NamedQuery(name = "CommentRatings.findByRating", query = "SELECT c FROM CommentRatings c WHERE c.rating = :rating"), @NamedQuery(name = "CommentRatings.findByIsDeleted", query = "SELECT c FROM CommentRatings c WHERE c.isDeleted = :isDeleted"), @NamedQuery(name = "CommentRatings.findByDeletedAt", query = "SELECT c FROM CommentRatings c WHERE c.deletedAt = :deletedAt")})
public class CommentRatings implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Basic(optional = false) @Column(name = "id")
    private Integer id;
    @Basic(optional = false) @NotNull @Column(name = "comment_id")
    private int commentId;
    @Basic(optional = false) @NotNull @Column(name = "rating")
    private int rating;
    @Basic(optional = false) @NotNull @Column(name = "is_deleted")
    private boolean isDeleted;
    @Column(name = "deleted_at") @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

    public CommentRatings() {
    }

    public CommentRatings(Integer id) {
        this.id = id;
    }

    public CommentRatings(Integer id, int commentId, int rating, boolean isDeleted) {
        this.id = id;
        this.commentId = commentId;
        this.rating = rating;
        this.isDeleted = isDeleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
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
        if (!(object instanceof CommentRatings)) {
            return false;
        }
        CommentRatings other = (CommentRatings) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.javamvchelix.flames_of_freedom_1956.CommentRatings[ id=" + id + " ]";
    }
    
}
