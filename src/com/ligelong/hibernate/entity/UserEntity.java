/*
 * Created on 2011-08-20
 */
package com.ligelong.hibernate.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ligelong.hibernate.core.BaseEntity;

/**
 * <code>UserEntity</code>
 *
 * @author David Gong
 */
@Entity
@Table(name = "tb_lgl_user")
public class UserEntity extends BaseEntity {

    private Integer           id;                //
    private String            username;          //
    private String            password;          //
    private String            email;             //
    private String            phone;             //
    private Integer           status;            //
    private Timestamp         createtime;        //
    private Timestamp         lastlogintime;     //
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "username", unique = true)
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email")
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "phone")
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return this.status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createtime")
    public Date getCreatetime() {
        return this.createtime;
    }
    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastlogintime")
    public Date getLastlogintime() {
        return this.lastlogintime;
    }
    public void setLastlogintime(Timestamp lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return this.getClass().getSimpleName() + "[id=" + this.id + ",username=" + this.username + "]";
    }
}
