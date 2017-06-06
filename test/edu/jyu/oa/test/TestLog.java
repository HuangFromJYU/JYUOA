package edu.jyu.oa.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.jyu.oa.test.TestLog;

public class TestLog {

	
	
	private Log log = LogFactory.getLog(TestLog.class);

	// private Logger log = LoggerFactory.getLogger(TestLog.class);

	@Test
	public void test() throws Exception {
		log.debug("这是debug级别"); // 调试
		log.info("这是info级别"); // 信息
		log.warn("这是warn级别"); // 警告
		log.error("这是error级别"); // 错误
		log.fatal("这是fatal级别"); // 严重错误
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

}
