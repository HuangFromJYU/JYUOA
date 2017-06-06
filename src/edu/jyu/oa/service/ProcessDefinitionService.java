package edu.jyu.oa.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.jbpm.api.ProcessDefinition;

public interface ProcessDefinitionService {

	/**
	 * 查询所有最新版本的流程定义列表
	 * 
	 * @return
	 */
	List<ProcessDefinition> findAllLatestVersionList();

	/**
	 * 删除指定key的所有版本的流程定义
	 * 
	 * @param key
	 */
	void deleteByKey(String key);

	/**
	 * 部署流程定义（使用zip包的方式）
	 * 
	 * @param zipFile
	 */
	void deployByZip(File zipFile);

	/**
	 * 获取指定流程定义中的流程图片文件的输入流
	 * 
	 * @param id
	 * @return
	 */
	InputStream getImageResourceAsStreamByPdId(String id);

}
