package com.xatu.dbconnect;

import java.sql.*;

public class OracleDbManager {
	
	private static String URL = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	private static String USER = "system";
	private static String PASSWORD = "System123";
	
	public static Connection getConnection(){
		try {
			Class.forName(Driver.class.getName());//加载驱动
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);//建立连接
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("success connect to oracle");
		return conn;
	}
	
	public static void close(Connection ct, PreparedStatement ps, ResultSet rs){
		try {
			if (rs != null) {// 先判断一下，如果rs存在，执行以下语句
				rs.close();
				rs = null;   // 将rs对象设置为空，等待gc回收
			}
			if (ps != null) {
				ps.close();
				ps = null;
			}
			if (ct != null) {
				ct.close();
				ct = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection ct, PreparedStatement ps) {

		try {
			if(ps != null){
				ps.close();//关闭ps
				ps = null;
			}
			if(ct != null){
				ct.close();//关闭conn
				ct = null;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
