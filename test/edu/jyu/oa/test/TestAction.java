package edu.jyu.oa.test;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import edu.jyu.oa.test.TestService;

@Controller
public class TestAction extends ActionSupport {

	@Resource
	private TestService testService;

	public String execute() throws Exception {
		System.out.println("-------> TestAction.execute()");
		System.out.println("-------> testService = " + testService);
		testService.saveTwoUsers();
		return "success";
	}

}
