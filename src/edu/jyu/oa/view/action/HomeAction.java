package edu.jyu.oa.view.action;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
public class HomeAction extends ActionSupport {

	public String index() throws Exception {
		return "index";
	}

	public String top() throws Exception {
		return "top";
	}

	public String bottom() throws Exception {
		return "bottom";
	}

	public String left() throws Exception {
		return "left";
	}

	public String right() throws Exception {
		return "right";
	}

}
