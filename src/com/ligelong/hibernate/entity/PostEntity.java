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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ligelong.hibernate.core.BaseEntity;

/**
 * <code>Post</code>
 *
 * @author David Gong
 */
@Entity
@Table(name = "tb_lgl_post")
public class PostEntity extends BaseEntity {
    private Integer    id;
    private String     title;
    private UserEntity creator;
    private Timestamp  createtime;
    private String     content;
    private Integer    status;
    private Long       upcount;
    private Long       downcount;
    private Long       commentcount;
    
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

    @ManyToOne
    @JoinColumn(name="userid") 
    public UserEntity getCreator() {
        return this.creator;
    }
    public void setCreator(UserEntity creator) {
        this.creator = creator;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createtime")
    public Date getCreatetime() {
        return this.createtime;
    }
    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public String gainTextFromContent() {
		return content;
	}
	public void putTextToContent(String content) {
		this.content = content;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "upcount")
	public Long getUpcount() {
		return upcount;
	}
	public void setUpcount(Long upcount) {
		this.upcount = upcount;
	}

	@Column(name = "downcount")
	public Long getDowncount() {
		return downcount;
	}
	public void setDowncount(Long downcount) {
		this.downcount = downcount;
	}
	
	@Column(name = "commentcount")
	public Long getCommentcount() {
		return commentcount;
	}
	public void setCommentcount(Long commentcount) {
		this.commentcount = commentcount;
	}
	
	/*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return this.getClass().getSimpleName() + "[id=" + this.id + ", title=" + this.title + "]";
    }
}
