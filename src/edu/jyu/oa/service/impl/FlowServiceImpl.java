package edu.jyu.oa.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.jyu.oa.domain.Application;
import edu.jyu.oa.domain.ApproveInfo;
import edu.jyu.oa.domain.TaskView;
import edu.jyu.oa.domain.User;
import edu.jyu.oa.service.FlowService;

@Service
@Transactional
public class FlowServiceImpl implements FlowService {

	@Resource
	private SessionFactory sessionFactory;
	@Resource
	private ProcessEngine processEngine;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public void submit(Application application) {
		// 1，设置属性并保存申请信息
		application.setApplyTime(new Date()); // 申请时间
		application.setStatus(Application.STATUS_RUNNING); // 状态，默认为“正在审批中”
		application.setTitle(application.getTemplate().getName()// 标题格式：{模版名称}_{申请人姓名}_{申请日期}
				+ "_" + application.getApplicant().getName()//
				+ "_" + sdf.format(application.getApplyTime()));
		sessionFactory.getCurrentSession().save(application); // 保存

		// 2，启动流程开始流转
		// >> 启动流程实例，设置流程变量application
		String processKey = application.getTemplate().getProcessKey(); // 流程定义的key
		Map<String, Object> variables = new HashMap<String, Object>(); // 流程变量
		variables.put("application", application);
		ProcessInstance pi = processEngine.getExecutionService().startProcessInstanceByKey(processKey, variables);

		// >> 办理完第一个任务（提交申请）
		Task task = processEngine.getTaskService()//
				.createTaskQuery()// 查询出本流程实例中当前情况下仅有的一个任务
				.processInstanceId(pi.getId())//
				.uniqueResult();
		processEngine.getTaskService().completeTask(task.getId());
	}

	public List<TaskView> findMyTaskViewList(User currentUser) {
		// 查询我的任务列表
		String userId = currentUser.getLoginName(); // 使用loginName作为JBPM中的用户标识符
		List<Task> taskList = processEngine.getTaskService().findPersonalTasks(userId);

		// 获取每个任务对应的申请信息
		List<TaskView> returnList = new ArrayList<TaskView>();
		for (Task task : taskList) {
			Application application = (Application) processEngine.getTaskService().getVariable(task.getId(), "application");
			TaskView tv = new TaskView(task, application);
			returnList.add(tv);
		}

		return returnList;
	}

	public Application getApplicationById(Long applicationId) {
		return (Application) sessionFactory//
				.getCurrentSession()//
				.get(Application.class, applicationId);
	}

	public void approve(ApproveInfo approveInfo, String taskId, String outcome) {
		// 1, 保存审批信息
		sessionFactory.getCurrentSession().save(approveInfo);

		// 2, 完成当前任务
		Task task = processEngine.getTaskService().getTask(taskId); // 获取任务的代码一定要是写到完成任务前，因为任务办理完后就变成了历史任务信息。
		if (outcome == null) {
			processEngine.getTaskService().completeTask(taskId); // 使用默认的Transition
		} else {
			processEngine.getTaskService().completeTask(taskId, outcome); // 使用指定名称的Transition
		}

		// >> 获取任务所属的流程实例（正在执行的表中的信息），如果流程已经结束了，则返回null.
		ProcessInstance pi = processEngine.getExecutionService().findProcessInstanceById(task.getExecutionId());

		// 3, 维护申请的状态
		Application application = approveInfo.getApplication();
		if (!approveInfo.isApproval()) {
			// 如果本环节不同意，则流程直接结束，申请的状态为“未通过”
			// 如果本环节不是最后一个，我们就要手工结束这个流程实例
			if (pi != null) {
				processEngine.getExecutionService().endProcessInstance(pi.getId(), ProcessInstance.STATE_ENDED);
			}
			application.setStatus(Application.STATUS_REJECTED);
		} else {
			if (pi == null) {
				// 如果本环节同意了，并且本环节是最后一个审批，则流程正常结束，申请的状态为“已通过”
				application.setStatus(Application.STATUS_APPROVED);
			}
		}
		sessionFactory.getCurrentSession().update(application);
	}

	public Collection<String> getOutcomesByTaskId(String taskId) {
		return processEngine.getTaskService().getOutcomes(taskId);
	}

	@SuppressWarnings("unchecked")
	public List<ApproveInfo> getApproveInfosByApplicationId(Long applicationId) {
		return sessionFactory.getCurrentSession()//
				.createQuery("FROM ApproveInfo a WHERE a.application.id=? ORDER BY a.approveTime ASC")//
				.setParameter(0, applicationId)//
				.list();
	}
}
