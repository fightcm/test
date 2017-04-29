package com.xatu.util;


import java.io.InputStream; 
import java.util.Iterator;
import java.util.Properties; 

public class CommonProperty {
	
	private static Properties loadProperty() {
		Properties prop = new Properties(); 
		try{
			CommonProperty cp = new CommonProperty();
			InputStream in = cp.getClass().getResourceAsStream("/mysql2oracle.properties"); 
		    prop.load(in);     ///加载属性列表
		    in.close();
		}catch(Exception  e){
			System.out.println(e);
		}
        return prop;
    }
	
	public static void main(String[] args) { 
		Properties prop = new Properties(); 
		prop = loadProperty();
		Iterator<String> it=prop.stringPropertyNames().iterator();
	    while(it.hasNext()){
	    	String key=it.next();
	    	System.out.println(key+":"+prop.getProperty(key));
	    }		 
    } 
}
