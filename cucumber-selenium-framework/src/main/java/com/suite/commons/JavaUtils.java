package com.suite.commons;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class JavaUtils {
	
	protected static String getClassFilePath(Class<?> cls){
		System.out.println("******** getFilePath for class "+cls.getName());
		String strSourceClassName = cls.getResource(cls.getSimpleName()+".class").getPath();
		System.out.println("*************** resource path is "+strSourceClassName);
		try {
			strSourceClassName = URLDecoder.decode(strSourceClassName,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuffer strFilePath = new StringBuffer();
		strFilePath.append(strSourceClassName.subSequence(1, strSourceClassName.indexOf("com")));
		strFilePath.append(cls.getName().replace(".","/"));
		strFilePath.append(".xlsx");
		System.out.println("Class path is - "+strFilePath);
		//return strFilePath.toString();
		return strSourceClassName.replace(".class", ".xlsx");
	}
}
