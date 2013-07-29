/*
 * Created on 2011-08-20
 */
package com.ligelong.hibernate.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ligelong.hibernate.core.BaseEntity;

/**
 * <code>PostTagEntity</code>
 *
 * @author David Gong
 */
@Entity
@Table(name = "tb_lgl_post_tag")
public class PostTagEntity extends BaseEntity {

    private Integer id;
    private String name;
    private Integer status;
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return this.status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return this.getClass().getSimpleName() + 
                "[id=" + this.id + ", name=" + this.name + 
                ", status=" + this.status + "]";
    }
}
