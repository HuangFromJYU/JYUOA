package edu.jyu.oa.domain;

import java.util.HashSet;
import java.util.Set;

import edu.jyu.oa.domain.Topic;

/**
 * 实体：版块
 * 
 * @author hjj
 * 
 */
public class Forum {
	private Long id;
	private String name;
	private String description;
	private int position; // 排序用的位置号

	private int topicCount; // 主题数量
	private int articleCount; // 文章数量（主帖数 + 回帖数）
	private Topic lastTopic; // 最新主题
	private Set<Topic> topics = new HashSet<Topic>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getTopicCount() {
		return topicCount;
	}

	public void setTopicCount(int topicCount) {
		this.topicCount = topicCount;
	}

	public int getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}

	public Topic getLastTopic() {
		return lastTopic;
	}

	public void setLastTopic(Topic lastTopic) {
		this.lastTopic = lastTopic;
	}

	public Set<Topic> getTopics() {
		return topics;
	}

	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}

}
