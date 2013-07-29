/*
 * Created on 2011-08-20
 */
package com.ligelong.hibernate.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ligelong.hibernate.core.BaseEntity;

/**
 * <code>UserRoleEntity</code>
 *
 * @author David Gong
 */
@Entity
@Table(name = "tb_lgl_user_role")
public class UserRoleEntity extends BaseEntity {
    private Integer    id;    
    private UserEntity user;  
    private Integer    aclid; 
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    
    @ManyToOne
    @JoinColumn(name="userid") 
    public UserEntity getUser() {
        return user;
    }
    public void setUser(UserEntity user) {
        this.user = user;
    }
    
    @Column(name = "aclid")
    public Integer getAclid() {
        return this.aclid;
    }
    public void setAclid(Integer aclid) {
        this.aclid = aclid;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return this.getClass().getSimpleName() + "[id=" + this.id 
                + ",user=" + (this.user==null?null:user.getId())
                + ",aclid=" + this.aclid + "]";
    }
}
