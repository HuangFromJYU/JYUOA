package edu.jyu.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import edu.jyu.oa.base.ModelDrivenBaseAction;
import edu.jyu.oa.domain.Forum;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class ForumManageAction extends ModelDrivenBaseAction<Forum> {

	/** 列表 */
	public String list() throws Exception {
		List<Forum> forumList = forumService.findAll();
		ActionContext.getContext().put("forumList", forumList);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		forumService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		forumService.save(model);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 准备回显的数据
		Forum forum = forumService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(forum);
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1，从数据库中获取原对象
		Forum forum = forumService.getById(model.getId());
		
		// 2，设置要修改的属性
		forum.setName(model.getName());
		forum.setDescription(model.getDescription());

		// 3，更新到数据库
		forumService.update(forum);
		
		return "toList";
	}

	/** 上移 */
	public String moveUp() throws Exception {
		forumService.moveUp(model.getId());
		return "toList";
	}

	/** 下移 */
	public String moveDown() throws Exception {
		forumService.moveDown(model.getId());
		return "toList";
	}
}
