package edu.jyu.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.jyu.oa.base.DaoSupportImpl;
import edu.jyu.oa.cfg.Configuration;
import edu.jyu.oa.domain.Forum;
import edu.jyu.oa.domain.PageBean;
import edu.jyu.oa.domain.Reply;
import edu.jyu.oa.domain.Topic;
import edu.jyu.oa.service.ReplyService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class ReplyServiceImpl extends DaoSupportImpl<Reply> implements ReplyService {

	public List<Reply> findByTopic(Topic topic) {
		return getSession().createQuery(//
				// 排序：最新的回复排到最后面
				"FROM Reply r WHERE r.topic=? ORDER BY r.postTime ASC")//
				.setParameter(0, topic)//
				.list();
	}

	@Override
	public void save(Reply reply) {
		// 1，设置属性并保存
		reply.setDeleted(false); // 默认为未删除
		reply.setPostTime(new Date()); // 当前时间
		getSession().save(reply);

		// 2，更新相关的信息
		Topic topic = reply.getTopic();
		Forum forum = topic.getForum();

		forum.setArticleCount(forum.getArticleCount() + 1); // 版块的文章数量（主题+回复）
		topic.setReplyCount(topic.getReplyCount() + 1); // 主题的回复数量
		topic.setLastUpdateTime(reply.getPostTime()); // 主题的最后更新时间（主题发表的时间或最后回复的时间）
		topic.setLastReply(reply); // 主题的最后发表的回复

		getSession().update(topic);
		getSession().update(forum);
	}

	public PageBean getPageBeanByTopic(int pageNum, Topic topic) {
		// 获取pageSize信息
		int pageSize = Configuration.getPageSize();

		// 查询一页的数据列表
		List list = getSession().createQuery(//
				"FROM Reply r WHERE r.topic=? ORDER BY r.postTime ASC")//
				.setParameter(0, topic)//
				.setFirstResult((pageNum - 1) * pageSize)//
				.setMaxResults(pageSize)//
				.list();

		// 查询总记录数
		Long count = (Long) getSession().createQuery(//
				"SELECT COUNT(*) FROM Reply r WHERE r.topic=?")//
				.setParameter(0, topic)//
				.uniqueResult();

		return new PageBean(pageNum, pageSize, count.intValue(), list);
	}

}
