package com.fxy.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;

import com.fxy.util.app.AppUtil;
import com.fxy.util.log.LogUtil;



@WebListener
public class AppLisener implements ServletContextListener,ServletContextAttributeListener{

	@Override
	public void attributeAdded(ServletContextAttributeEvent scab) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent scab) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent scab) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		AppUtil.SC=sce.getServletContext();
		String initParameter = sce.getServletContext().getInitParameter("logTarget");
		LogUtil.logger=LogManager.getLogger(initParameter);
		LogUtil.log("*******************程序已启动*********************");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		LogUtil.log("*******************程序已结束*********************");
		
	}

}
