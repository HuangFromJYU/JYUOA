package edu.jyu.oa.util;

import java.util.List;

import org.junit.Test;

import edu.jyu.oa.domain.Forum;
import edu.jyu.oa.domain.Topic;
import edu.jyu.oa.util.QueryHelper;

public class QueryHelperTest {

	/**
	 * 0 表示全部主题<br>
	 * 1 表示全部精华贴
	 */
	private int viewType = 1;

	/**
	 * 0 表示默认排序(所有置顶帖在前面，并按最后更新时间降序排列)<br>
	 * 1 表示只按最后更新时间排序<br>
	 * 2 表示只按主题发表时间排序<br>
	 * 3 表示只按回复数量排序
	 */
	private int orderBy = 0;

	/**
	 * true表示升序<br>
	 * false表示降序
	 */
	private boolean asc = false;

	private Forum forum = new Forum();

	@Test
	public void testQueryHelper() {
		QueryHelper queryHelper = new QueryHelper(Topic.class, "t")//
				.addWhereCondition("t.forum=?", forum)//
				.addWhereCondition((viewType == 1), "t.type=?", Topic.TYPE_BEST) // 1 表示只看精华帖
				.addOrderByProperty((orderBy == 1), "t.lastUpdateTime", asc) // 1 表示只按最后更新时间排序
				.addOrderByProperty((orderBy == 2), "t.postTime", asc) // 表示只按主题发表时间排序
				.addOrderByProperty((orderBy == 3), "t.replyCount", asc) // 表示只按回复数量排序
				.addOrderByProperty((orderBy == 0), "(CASE t.type WHEN 2 THEN 2 ELSE 0 END)", false)//
				.addOrderByProperty((orderBy == 0), "t.lastUpdateTime", false) // 0 表示默认排序(所有置顶帖在前面，并按最后更新时间降序排列)
		;

		String listHql = queryHelper.getQueryListHql();
		String countHql = queryHelper.getQueryCountHql();
		List<Object> parameters = queryHelper.getParameters();

		System.out.println(listHql);
		System.out.println(countHql);
		System.out.println(parameters);
	}

}
