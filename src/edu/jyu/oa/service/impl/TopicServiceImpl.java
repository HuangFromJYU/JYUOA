package edu.jyu.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.jyu.oa.base.DaoSupportImpl;
import edu.jyu.oa.cfg.Configuration;
import edu.jyu.oa.domain.Forum;
import edu.jyu.oa.domain.PageBean;
import edu.jyu.oa.domain.Topic;
import edu.jyu.oa.service.TopicService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class TopicServiceImpl extends DaoSupportImpl<Topic> implements TopicService {

	public List<Topic> findByForum(Forum forum) {
		return getSession().createQuery(//
				// 排序：最新状态的排到前面，置顶帖在最上面。
				"FROM Topic t WHERE t.forum=? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC, t.lastUpdateTime DESC")//
				.setParameter(0, forum)//
				.list();
	}

	@Override
	public void save(Topic topic) {
		// 1，设置属性并保存
		topic.setType(Topic.TYPE_NORMAL); // 普通帖
		topic.setReplyCount(0);
		topic.setLastReply(null);
		topic.setPostTime(new Date()); // 当前时间
		topic.setLastUpdateTime(topic.getPostTime()); // 默认为主题的发表时间
		getSession().save(topic); // 保存

		// 2，更新相关的信息
		Forum forum = topic.getForum();

		forum.setTopicCount(forum.getTopicCount() + 1); // 主题数量
		forum.setArticleCount(forum.getArticleCount() + 1); // 文章数量（主题+回复）
		forum.setLastTopic(topic); // 最后发表的主题

		getSession().update(forum);
	}

	public PageBean getPageBeanByForum(int pageNum, Forum forum) {
		// 获取pageSize信息
		int pageSize = Configuration.getPageSize();

		// 查询一页的数据列表
		List list = getSession().createQuery(//
				"FROM Topic t WHERE t.forum=? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC, t.lastUpdateTime DESC")//
				.setParameter(0, forum)//
				.setFirstResult((pageNum - 1) * pageSize)//
				.setMaxResults(pageSize)//
				.list();

		// 查询总记录数
		Long count = (Long) getSession().createQuery(//
				"SELECT COUNT(*) FROM Topic t WHERE t.forum=?")//
				.setParameter(0, forum)//
				.uniqueResult();

		return new PageBean(pageNum, pageSize, count.intValue(), list);
	}

}
