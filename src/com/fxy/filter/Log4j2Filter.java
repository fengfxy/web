package com.fxy.filter;

import javax.servlet.DispatcherType;
import javax.servlet.annotation.WebFilter;

@WebFilter(
		filterName="F001",
		urlPatterns={"/*"},
		dispatcherTypes={
				DispatcherType.ASYNC,
				DispatcherType.ERROR,
				DispatcherType.FORWARD,
				DispatcherType.INCLUDE,
				DispatcherType.REQUEST,
		}
		
		)
public class Log4j2Filter extends org.apache.logging.log4j.web.Log4jServletFilter{

}
