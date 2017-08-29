package com.fxy.util.log;

import org.apache.logging.log4j.Logger;

public class LogUtil {
	
	public static Logger logger=null;
	
	public static void log(String mess){
		
		logger.debug(mess);
		
	}
}
