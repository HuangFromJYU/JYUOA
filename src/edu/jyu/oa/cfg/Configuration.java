package edu.jyu.oa.cfg;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import edu.jyu.oa.cfg.Configuration;

/**
 * 管理配置信息的（读取配置文件）
 * 
 * @author hjj
 * 
 */
public class Configuration {

	private static int pageSize = 10;

	static {
		InputStream in = null;
		try {
			
			// 加载default.properties配置文件
			Properties props = new Properties();
			in = Configuration.class.getClassLoader().getResourceAsStream("default.properties");
			props.load(in);

			// 获取配置的值
			pageSize = Integer.parseInt(props.getProperty("pageSize"));
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public static int getPageSize() {
		return pageSize;
	}

	public static void setPageSize(int pageSize) {
		Configuration.pageSize = pageSize;
	}

}
