package edu.jyu.oa.service;

import edu.jyu.oa.base.DaoSupport;
import edu.jyu.oa.domain.Application;
import edu.jyu.oa.domain.Forum;

public interface ForumService extends DaoSupport<Forum> {

	/**
	 * 上移，最上面的不能上移
	 * 
	 * @param id
	 *            当前要移动的版块的id
	 */
	void moveUp(Long id);

	/**
	 * 下移，最下面的不能下移
	 * 
	 * @param id
	 *            当前要移动的版块的id
	 */
	void moveDown(Long id);


}
