package edu.jyu.oa.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessEngine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.jyu.oa.service.ProcessDefinitionService;

@Service
@Transactional
public class ProcessDefinitionServiceImpl implements ProcessDefinitionService {

	@Resource
	private SessionFactory sessionFactory;

	@Resource
	private ProcessEngine processEngine;

	public void deployByZip(File zipFile) {
		ZipInputStream zipInputStream = null;
		try {
			// 准备
			zipInputStream = new ZipInputStream(new FileInputStream(zipFile));
			// 部署
			processEngine.getRepositoryService()//
					.createDeployment()//
					.addResourcesFromZipInputStream(zipInputStream)//
					.deploy();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (zipInputStream != null) {
				try {
					zipInputStream.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public void deleteByKey(String key) {
		// 查询指定key的所有版本的流程定义
		List<ProcessDefinition> list = processEngine.getRepositoryService()//
				.createProcessDefinitionQuery()//
				.processDefinitionKey(key)// 过滤条件
				.list();// 执行查询
		// 循环刪除
		for (ProcessDefinition pd : list) {
			processEngine.getRepositoryService().deleteDeploymentCascade(pd.getDeploymentId());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.jyu.oa.service.ProcessDefinitionService#findAllLatestVersionList()
	 */
	public List<ProcessDefinition> findAllLatestVersionList() {
		// 查询所有的流程定义（包含所有的版本），按版本升序排列。
		List<ProcessDefinition> all = processEngine.getRepositoryService()//
				.createProcessDefinitionQuery()//
				.orderAsc(ProcessDefinitionQuery.PROPERTY_VERSION)//
				.list();// 执行查询

		// 过滤出所有最新的版本的流程定义列表（一个key只有一个最新的版本）
		Map<String, ProcessDefinition> map = new LinkedHashMap<String, ProcessDefinition>();
		for (ProcessDefinition pd : all) {
			map.put(pd.getKey(), pd);
		}

		return new ArrayList<ProcessDefinition>(map.values());
	}

	public InputStream getImageResourceAsStreamByPdId(String pdId) {
		// 获取信息
		ProcessDefinition pd = processEngine.getRepositoryService()//
				.createProcessDefinitionQuery()//
				.processDefinitionId(pdId)//
				.uniqueResult();
		String deploymentId = pd.getDeploymentId();
		String resourceName = pd.getImageResourceName();

		// 得到输入流
		return processEngine.getRepositoryService().getResourceAsStream(deploymentId, resourceName);
	}

}
