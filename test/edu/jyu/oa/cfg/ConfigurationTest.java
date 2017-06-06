package edu.jyu.oa.cfg;

import org.junit.Test;

import edu.jyu.oa.cfg.Configuration;

public class ConfigurationTest {

	@Test
	public void testGetPageSize() {
		int pageSize = Configuration.getPageSize();
		System.out.println("pageSize = " + pageSize);
	}

}
