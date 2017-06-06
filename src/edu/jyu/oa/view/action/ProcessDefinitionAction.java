package edu.jyu.oa.view.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.jbpm.api.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import edu.jyu.oa.base.BaseAction;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class ProcessDefinitionAction extends BaseAction {

	private File upload; // 上传的文件
	private InputStream inputStream; // 下载用的输入流
	private String id;
	private String key;

	
	/** 列表，显示的是所有最新版本的流程定义 */
	public String list() throws Exception {
		List<ProcessDefinition> pdList = processDefinitionService.findAllLatestVersionList();
		ActionContext.getContext().put("pdList", pdList);
		return "list";
	}

	/** 删除，删除的是指定key的所有版本的流程定义 */
	public String delete() throws Exception {
		key = new String(key.getBytes("iso8859-1"), "utf-8"); // 解决GET方式传递的中文乱码的问题
		processDefinitionService.deleteByKey(key);
		return "toList";
	}

	/** 添加页面（部署页面） */
	public String addUI() throws Exception {
		return "addUI";
	}

	/** 添加（部署） */
	public String add() throws Exception {
		processDefinitionService.deployByZip(upload);
		return "toList";
	}

	/** 查看流程图（xxx.png） */
	public String showProcessImage() throws Exception {
		// 解决GET方式传递的中文乱码的问题，但是如果tomcat里设置了是UTF-8编码的话就不用了
		// id = new String(id.getBytes("iso8859-1"), "utf-8"); 
		inputStream = processDefinitionService.getImageResourceAsStreamByPdId(id);
		return "showProcessImage";
	}

	// ---

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
