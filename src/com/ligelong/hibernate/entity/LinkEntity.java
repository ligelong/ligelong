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
 * <code>AdEntity</code>
 *
 * @author David Gong
 */
@Entity
@Table(name = "tb_lgl_link")
public class LinkEntity extends BaseEntity {
    private Integer id;
    private String title;
    private String link;
    private Integer type;
    private Integer parentid;
    private Integer order;
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "title")
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "link")
    public String getLink() {
        return this.link;
    }
    public void setLink(String link) {
        this.link = link;
    }

    @Column(name = "type")
    public Integer getType() {
        return this.type;
    }
    public void setType(Integer type) {
        this.type = type;
    }

    @Column(name = "parentid")
    public Integer getParentid() {
        return this.parentid;
    }
    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    @Column(name = "showorder")
    public Integer getOrder() {
        return this.order;
    }
    public void setOrder(Integer order) {
        this.order = order;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
       return this.getClass().getSimpleName() + "[id=" + this.id + ", title=" + this.title + "]"; 
    }
    
    public static enum Type{
		POSITION(0,"广告位"),
		LINK(1,"广告链接");
		private Integer value;
		private String desc;
		private Type(Integer value, String desc) {
			this.value = value;
			this.desc = desc;
		}
		public Integer getValue() {
			return this.value;
		}
		public String getDesc() {
			return this.desc;
		}
	}
}
