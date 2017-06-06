package edu.jyu.oa.domain;

import java.util.Date;

import edu.jyu.oa.domain.User;

/**
 * 实体：文章
 * 
 * @author hjj
 * 
 */
public abstract class Article {
	private Long id;
	private String content; // 内容（TEXT类型）
	private Date postTime; // 发表时间
	private String ipAddr; // IP地址
	private User author; // 作者

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

}
