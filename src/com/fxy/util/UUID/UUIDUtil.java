package com.fxy.util.UUID;

import java.util.UUID;

public class UUIDUtil {
	
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
}
