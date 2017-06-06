package edu.jyu.oa.domain;

import java.util.List;

/**
 * 分页用的一页的信息对象
 * 
 * @author hjj
 * 
 */
public class PageBean {

	// 传递的参数或配置的值
	private int currentPage; // 当前页
	private int pageSize; // 每页显示的记录数

	// 查询数据库
	private int recordCount; // 总记录数
	private List recordList; // 本页的数据列表

	// 计算出来的
	private int pageCount; // 总页数
	private int beginPageIndex; // 页面列表的开始索引
	private int endPageIndex; // 页面列表的结束索引

	/**
	 * 只接受前4个必要的属性的值，会自动的计算出后3个属性的值
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param recordCount
	 * @param recordList
	 */
	public PageBean(int currentPage, int pageSize, int recordCount, List recordList) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.recordCount = recordCount;
		this.recordList = recordList;

		// 计算pageCount
		pageCount = (recordCount + pageSize - 1) / pageSize;

		// 计算begPageIndex和endPageIndex
		// a, 总页数不超过10页，就全部显示
		if (pageCount <= 10) {
			beginPageIndex = 1;
			endPageIndex = pageCount;
		}
		// b, 总页数超过了10页，就显示当前页附近的共10个页码（前4个 + 当前页 + 后5个）
		else {
			// 显示当前页附近的共10个页码（前4个 + 当前页 + 后5个）
			beginPageIndex = currentPage - 4; // 7 - 4 = 3
			endPageIndex = currentPage + 5; // 7 + 5 = 12
			// 当前面不足4个页码时，就显示前10页
			if (beginPageIndex < 1) {
				beginPageIndex = 1;
				endPageIndex = 10;
			}
			// 当后面不足5个页码时，就显示后10页
			else if (endPageIndex > pageCount) {
				endPageIndex = pageCount;
				beginPageIndex = pageCount - 10 + 1; // 注意在显示的时候是包含两个边界的
			}
		}
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public List getRecordList() {
		return recordList;
	}

	public void setRecordList(List recordList) {
		this.recordList = recordList;
	}

	public int getBeginPageIndex() {
		return beginPageIndex;
	}

	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}

	public int getEndPageIndex() {
		return endPageIndex;
	}

	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}

}
