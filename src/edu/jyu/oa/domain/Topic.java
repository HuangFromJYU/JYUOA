package edu.jyu.oa.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import edu.jyu.oa.domain.Article;
import edu.jyu.oa.domain.Forum;
import edu.jyu.oa.domain.Reply;

/**
 * 实体：主帖
 * 
 * @author hjj
 * 
 */
public class Topic extends Article {

	/** 普通帖 */
	public static final int TYPE_NORMAL = 0;

	/** 精华帖 */
	public static final int TYPE_BEST = 1;

	/** 置顶帖 */
	public static final int TYPE_TOP = 2;

	private String title; // 标题
	private int type; // 类型

	private int replyCount; // 回复数量
	private Date lastUpdateTime; // 最后文章的发表时间
	private Reply lastReply; // 最后回复

	private Forum forum;
	private Set<Reply> replies = new HashSet<Reply>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Reply getLastReply() {
		return lastReply;
	}

	public void setLastReply(Reply lastReply) {
		this.lastReply = lastReply;
	}

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}

	public Set<Reply> getReplies() {
		return replies;
	}

	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}

}
