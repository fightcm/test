package com.xatu.dbconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class MysqlDbManager {

	/**
	 * 自定义数据库连接方法
	 * @return
	 */
	public static Connection getConnection(){
		
		try {
			Class.forName(Driver.class.getName());//加载驱动
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		Connection conn = null;
		String url = "jdbc:mysql://127.0.0.1:3306/data_migration?useSSL=true";
		
		try {
			conn = DriverManager.getConnection(url,"root","raymchen");//建立连接
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
		
	}
	/**
	 * 针对查的关闭
	 * @param conn
	 * @param ps
	 * @param rs
	 */
	public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {

		try {
			rs.close();//关闭rs
			ps.close();//关闭ps
			conn.close();//关闭conn
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	/**
	 * 针对增删改的关闭
	 * @param conn
	 * @param ps
	 */
	public static void close(Connection conn, PreparedStatement ps) {

		try {
			ps.close();//关闭ps
			conn.close();//关闭conn
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}