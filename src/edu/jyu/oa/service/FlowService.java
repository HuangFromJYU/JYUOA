package edu.jyu.oa.service;

import java.util.Collection;
import java.util.List;

import edu.jyu.oa.domain.Application;
import edu.jyu.oa.domain.ApproveInfo;
import edu.jyu.oa.domain.TaskView;
import edu.jyu.oa.domain.User;

public interface FlowService {

	/**
	 * 提交申请：
	 * 
	 * 1, 保存申请信息.
	 * 
	 * 2, 启动流程开始流转.
	 * 
	 * @param application
	 */
	void submit(Application application);

	/**
	 * 查询我的任务列表
	 * 
	 * @param currentUser
	 * @return
	 */
	List<TaskView> findMyTaskViewList(User currentUser);

	/**
	 * 审批处理：
	 * 
	 * 1, 保存审批信息.
	 * 
	 * 2, 完成当前任务.
	 * 
	 * 3, 维护申请的状态.
	 * 
	 * @param approveInfo
	 * @param taskId
	 * @param outcome
	 *            指定离开本活动要使用的Transition的名称，如果为null，就使用默认的Transition离开本活动
	 */
	void approve(ApproveInfo approveInfo, String taskId, String outcome);

	/**
	 * 获取申请信息
	 * 
	 * @param applicationId
	 * @return
	 */
	Application getApplicationById(Long applicationId);

	/**
	 * 获取指定任务活动中所有指向后面环节的Transition的名称
	 * 
	 * @param taskId
	 * @return
	 */
	Collection<String> getOutcomesByTaskId(String taskId);

	/**
	 * 获取指定的申请中所有的流转记录（审批信息）
	 * 
	 * @param applicationId
	 * @return
	 */
	List<ApproveInfo> getApproveInfosByApplicationId(Long applicationId);

}
