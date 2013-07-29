/**
 * 
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
 *<code>CommentEntity</code>
 *
 * @author David Gong
 */
@Entity
@Table(name = "tb_lgl_comment")
public class CommentEntity extends BaseEntity {
	private Integer id;
    private PostEntity post;
    private String content;
    private UserEntity user;
    private Timestamp createtime;
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    
    @ManyToOne
    @JoinColumn(name="postid")
	public PostEntity getPost() {
		return post;
	}
	public void setPost(PostEntity post) {
		this.post = post;
	}
	
	@Column(name = "content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
    @ManyToOne
    @JoinColumn(name="userid")
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createtime")
    public Date getCreatetime() {
        return this.createtime;
    }
    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }
}
